package TestCases;

import Framework.Dados.Conta;
import Framework.TestBase;
import Framework.Utils.FakersGeneration;
import Framework.Utils.FileOperations;
import Framework.Utils.TakeScreenshot;
import Tasks.CadastroTask;
import Tasks.ExtratoTask;
import Tasks.LoginTask;
import Tasks.TransferenciaTask;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;

@Epic("Teste BugBank")
@Feature("Transferencia entre contas")
public class TransferenciaEntreContasTest extends TestBase {

    private final WebDriver driver = getDriverManager();

    CadastroTask cadastroTask = new CadastroTask(driver);
    LoginTask loginTask = new LoginTask(driver);
    TransferenciaTask transferenciaTask = new TransferenciaTask(driver);
    ExtratoTask extratoTask = new ExtratoTask(driver);

    @Test
    @Story("Usuario transfere valor entre duas contas com saldo")
    @Description("Cadastra duas contas com saldo, transfere da origem para o destino e valida os extratos")
    public void transferirEntreContas() {

        try {

            // ---------- Dados do teste ----------
            // Saldo inicial e valor da transferencia ficam no arquivo transferencia.properties
            BigDecimal saldoInicial = new BigDecimal(
                    FileOperations.getProperties("transferencia").getProperty("saldoInicial"));
            BigDecimal valorTransferencia = new BigDecimal(
                    FileOperations.getProperties("transferencia").getProperty("valorTransferencia"));

            // Nome, e-mail e senha aleatorios (mudam a cada execucao)
            FakersGeneration faker = new FakersGeneration();

            String nomeOrigem = faker.getNomeCompleto();
            Conta origem = new Conta(nomeOrigem, faker.getEmail(nomeOrigem), faker.getSenha(), saldoInicial);

            String nomeDestino = faker.getNomeCompleto();
            Conta destino = new Conta(nomeDestino, faker.getEmail(nomeDestino), faker.getSenha(), saldoInicial);

            // Descricao unica: e por ela que achamos a linha certa no extrato
            String descricao = "Transferencia prova " + System.currentTimeMillis();

            // ---------- Saldos esperados (calculo explicito) ----------
            // Origem: tinha 1000,00 e enviou 250,00  -> 750,00
            // Destino: tinha 1000,00 e recebeu 250,00 -> 1250,00
            BigDecimal saldoEsperadoOrigem = saldoInicial.subtract(valorTransferencia);
            BigDecimal saldoEsperadoDestino = saldoInicial.add(valorTransferencia);

            // ---------- Passo 2: cadastrar as duas contas com saldo (mesma sessao) ----------
            cadastroTask.cadastrarContaComSaldo(origem);
            cadastroTask.cadastrarContaComSaldo(destino);

            // ---------- Passo 3: entrar na origem e transferir ----------
            loginTask.efetuarLogin(origem);                          // tela inicial ainda mostra o saldo inicial
            transferenciaTask.transferir(destino, valorTransferencia, descricao);

            // ---------- Passo 4: extrato da origem (saldo = inicial - valor) ----------
            origem.setSaldo(saldoEsperadoOrigem);
            extratoTask.validarExtrato(origem, descricao, valorTransferencia, "Extrato origem");
            loginTask.sair();

            // ---------- Passo 5: entrar no destino e validar o recebimento ----------
            // A transferencia ja aconteceu, entao a tela inicial do destino ja mostra o saldo novo.
            // Por isso ajustamos o saldo esperado ANTES do login (que confere o saldo da tela).
            destino.setSaldo(saldoEsperadoDestino);
            loginTask.efetuarLogin(destino);
            extratoTask.validarExtrato(destino, descricao, valorTransferencia, "Extrato destino");

        } catch (Exception e) {

            TakeScreenshot.screenshotPage(driver, "ERRO");
            throw e;
        }
    }
}

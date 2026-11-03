package Validacao;

import Framework.Dados.Conta;
import Framework.Utils.Moeda;
import Framework.Utils.TakeScreenshot;
import PageObjects.ExtratoPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;

public class ExtratoValidacao {

    private WebDriver driver;
    private ExtratoPage extratoPage;

    public ExtratoValidacao (WebDriver driver){

        this.driver = driver;
        extratoPage = new ExtratoPage(this.driver);
    }

    // Compara o "Saldo disponivel" do extrato com o saldo que a gente calculou
    public void validaSaldo(Conta conta, String nomeDoPrint){

        try {

            String saldoTela = Moeda.normalizar(extratoPage.getSaldoDisponivelText().getText());
            String saldoEsperado = Moeda.formatar(conta.getSaldo());

            Assertions.assertTrue(saldoTela.contains(saldoEsperado),
                    "Saldo no extrato: " + saldoTela + " | esperado: " + saldoEsperado);
            TakeScreenshot.screenshotPage(driver, nomeDoPrint);

        } catch (Exception e) {

            TakeScreenshot.screenshotPage(driver, nomeDoPrint + " - ERRO");
            throw e;
        }
    }

    // Confirma que a transferencia aparece no extrato com o valor certo
    public void validaTransacao(String descricao, BigDecimal valor, String nomeDoPrint){

        try {

            String valorTela = Moeda.normalizar(extratoPage.getValorDaTransacao(descricao).getText());
            String valorEsperado = Moeda.formatar(valor);

            // contains porque o extrato pode mostrar sinal ("-R$ 250,00" na origem)
            Assertions.assertTrue(valorTela.contains(valorEsperado),
                    "Valor da transacao no extrato: " + valorTela + " | esperado: " + valorEsperado);
            TakeScreenshot.screenshotPage(driver, nomeDoPrint);

        } catch (Exception e) {

            TakeScreenshot.screenshotPage(driver, nomeDoPrint + " - ERRO");
            throw e;
        }
    }
}

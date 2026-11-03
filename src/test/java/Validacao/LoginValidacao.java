package Validacao;

import Framework.Dados.Conta;
import Framework.Utils.Moeda;
import Framework.Utils.TakeScreenshot;
import PageObjects.HomePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class LoginValidacao {

    private WebDriver driver;
    private HomePage homePage;

    public LoginValidacao (WebDriver driver){

        this.driver = driver;
        homePage = new HomePage(this.driver);
    }

    // Depois do login, a tela inicial tem que mostrar o nome da conta e o saldo esperado
    public void validaLogin(Conta conta){

        try {

            String nome = homePage.getNomeText().getText();
            Assertions.assertTrue(nome.contains(conta.getNome()),
                    "Nome na tela inicial: " + nome + " | esperado: " + conta.getNome());

            String saldo = Moeda.normalizar(homePage.getSaldoText().getText());
            String saldoEsperado = Moeda.formatar(conta.getSaldo());
            Assertions.assertTrue(saldo.contains(saldoEsperado),
                    "Saldo na tela inicial: " + saldo + " | esperado: " + saldoEsperado);

            TakeScreenshot.screenshotPage(driver, "Login - " + conta.getNome());

        } catch (Exception e) {

            TakeScreenshot.screenshotPage(driver, "Login - ERRO");
            throw e;
        }
    }
}

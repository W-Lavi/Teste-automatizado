package Tasks;

import Framework.Dados.Conta;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import Validacao.LoginValidacao;
import org.openqa.selenium.WebDriver;

public class LoginTask {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private LoginValidacao loginValidacao;

    public LoginTask (WebDriver driver){

        this.driver = driver;
        loginPage = new LoginPage(this.driver);
        homePage = new HomePage(this.driver);
        loginValidacao = new LoginValidacao(this.driver);
    }

    // Entra na conta e confere nome + saldo na tela inicial
    public void efetuarLogin(Conta conta){

        loginPage.getEmailInput().sendKeys(conta.getEmail());
        loginPage.getPasswordInput().sendKeys(conta.getSenha());
        loginPage.getAcessarButton().click();

        loginValidacao.validaLogin(conta);
    }

    // Sai da conta (volta para a tela de login, sem fechar o navegador)
    public void sair(){

        homePage.getSairButton().click();
        loginPage.getAcessarButton();   // espera a tela de login voltar
    }
}

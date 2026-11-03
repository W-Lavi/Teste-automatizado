package Validacao;

import Framework.Utils.TakeScreenshot;
import PageObjects.GenericPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class CadastroValidacao {

    private WebDriver driver;
    private GenericPage genericPage;

    public CadastroValidacao (WebDriver driver){

        this.driver = driver;
        genericPage = new GenericPage(this.driver);
    }

    // O modal depois do cadastro precisa dizer que a conta foi criada com sucesso
    public void validaContaCriada(){

        try {

            String texto = genericPage.getModalText().getText();
            Assertions.assertTrue(texto.contains("foi criada com sucesso"),
                    "Modal de cadastro inesperado: " + texto);
            TakeScreenshot.screenshotPage(driver, "Cadastro - conta criada");

        } catch (Exception e) {

            TakeScreenshot.screenshotPage(driver, "Cadastro - ERRO");
            throw e;
        }
    }
}

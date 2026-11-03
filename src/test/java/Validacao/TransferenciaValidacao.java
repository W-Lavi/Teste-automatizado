package Validacao;

import Framework.Utils.FakersGeneration;
import Framework.Utils.TakeScreenshot;
import PageObjects.GenericPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class TransferenciaValidacao {

    private WebDriver driver;
    private GenericPage genericPage;

    public TransferenciaValidacao (WebDriver driver){

        this.driver = driver;
        genericPage = new GenericPage(this.driver);
    }

    // O modal depois de "Transferir agora" precisa confirmar a transferencia
    public void validaTransferenciaRealizada(){

        try {

            String texto = genericPage.getModalText().getText();
            // O BugBank escreve "Transferencia" sem acento no modal; tiramos o acento dos dois lados
            // para a comparacao nao depender de como o site escreve
            Assertions.assertTrue(FakersGeneration.removerAcentos(texto).contains("Transferencia realizada com sucesso"),
                    "Modal de transferencia inesperado: " + texto);
            TakeScreenshot.screenshotPage(driver, "Transferencia - realizada");

        } catch (Exception e) {

            TakeScreenshot.screenshotPage(driver, "Transferencia - ERRO");
            throw e;
        }
    }
}

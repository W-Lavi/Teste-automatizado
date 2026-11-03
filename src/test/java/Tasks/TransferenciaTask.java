package Tasks;

import Framework.Browser.Waits;
import Framework.Dados.Conta;
import PageObjects.GenericPage;
import PageObjects.HomePage;
import PageObjects.TransferenciaPage;
import Validacao.TransferenciaValidacao;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;

public class TransferenciaTask {

    private WebDriver driver;
    private HomePage homePage;
    private TransferenciaPage transferenciaPage;
    private GenericPage genericPage;
    private TransferenciaValidacao transferenciaValidacao;
    private Waits waits;

    public TransferenciaTask (WebDriver driver){

        this.driver = driver;
        homePage = new HomePage(this.driver);
        transferenciaPage = new TransferenciaPage(this.driver);
        genericPage = new GenericPage(this.driver);
        transferenciaValidacao = new TransferenciaValidacao(this.driver);
        waits = new Waits(this.driver);
    }

    // Com a conta de origem logada, transfere "valor" para a conta de destino
    public void transferir(Conta destino, BigDecimal valor, String descricao){

        homePage.getTransferenciaButton().click();

        transferenciaPage.getNumeroContaInput().sendKeys(destino.getNumero());
        transferenciaPage.getDigitoInput().sendKeys(destino.getDigito());
        transferenciaPage.getValorInput().sendKeys(valor.toPlainString());
        transferenciaPage.getDescricaoInput().sendKeys(descricao);
        transferenciaPage.getTransferirButton().click();

        transferenciaValidacao.validaTransferenciaRealizada();

        genericPage.getModalCloseButton().click();
        waits.invisibilityOfElement(By.id("modalText"));

        // Volta para a tela inicial
        genericPage.getVoltarButton().click();
    }
}

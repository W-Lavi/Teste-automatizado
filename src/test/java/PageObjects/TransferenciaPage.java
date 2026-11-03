package PageObjects;

import Framework.Browser.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Tela "Transferencia": numero da conta + digito, valor e descricao
public class TransferenciaPage {

    private WebDriver driver;
    private Waits waits;

    public TransferenciaPage (WebDriver driver){

        this.driver = driver;
        waits = new Waits(this.driver);
    }

    public WebElement getNumeroContaInput(){

        return waits.visbilityOfElement(By.name("accountNumber"));
    }

    public WebElement getDigitoInput(){

        return waits.visbilityOfElement(By.name("digit"));
    }

    public WebElement getValorInput(){

        return waits.visbilityOfElement(By.name("transferValue"));
    }

    public WebElement getDescricaoInput(){

        return waits.visbilityOfElement(By.name("description"));
    }

    public WebElement getTransferirButton(){

        return waits.clickableElement(By.xpath("//button[normalize-space()='Transferir agora']"));
    }
}

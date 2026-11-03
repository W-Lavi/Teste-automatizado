package PageObjects;

import Framework.Browser.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Tela logada: saudacao, saldo e menu (Transferencia, Extrato, Sair)
public class HomePage {

    private WebDriver driver;
    private Waits waits;

    public HomePage (WebDriver driver){

        this.driver = driver;
        waits = new Waits(this.driver);
    }

    // "Ola Maria Souza,"
    public WebElement getNomeText(){

        return waits.visbilityOfElement(By.id("textName"));
    }

    // "Saldo em conta R$ 1.000,00"
    public WebElement getSaldoText(){

        return waits.visbilityOfElement(By.id("textBalance"));
    }

    public WebElement getTransferenciaButton(){

        return waits.clickableElement(By.id("btn-TRANSFERÊNCIA"));
    }

    public WebElement getExtratoButton(){

        return waits.clickableElement(By.id("btn-EXTRATO"));
    }

    public WebElement getSairButton(){

        return waits.clickableElement(By.id("btnExit"));
    }
}

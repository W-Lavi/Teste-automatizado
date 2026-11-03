package PageObjects;

import Framework.Browser.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Elementos que aparecem em varias telas: o modal (janelinha de aviso) do BugBank
public class GenericPage {

    private WebDriver driver;
    private Waits waits;

    public GenericPage(WebDriver driver){

        this.driver = driver;
        waits = new Waits(this.driver);
    }

    // Texto do modal, ex: "A conta 123-4 foi criada com sucesso"
    public WebElement getModalText(){

        return waits.visbilityOfElement(By.id("modalText"));
    }

    public WebElement getModalCloseButton(){

        return waits.clickableElement(By.id("btnCloseModal"));
    }

    // Botao "Voltar" das telas de transferencia e extrato
    public WebElement getVoltarButton(){

        return waits.clickableElement(By.id("btnBack"));
    }
}

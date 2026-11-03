package PageObjects;

import Framework.Browser.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Card de cadastro (aparece depois de clicar em "Registrar")
public class CadastroPage {

    private static final String CARD_CADASTRO = ".card__register ";

    private WebDriver driver;
    private Waits waits;

    public CadastroPage (WebDriver driver){

        this.driver = driver;
        waits = new Waits(this.driver);
    }

    public WebElement getEmailInput(){

        return waits.visbilityOfElement(By.cssSelector(CARD_CADASTRO + "input[name='email']"));
    }

    public WebElement getNomeInput(){

        return waits.visbilityOfElement(By.cssSelector(CARD_CADASTRO + "input[name='name']"));
    }

    public WebElement getSenhaInput(){

        return waits.visbilityOfElement(By.cssSelector(CARD_CADASTRO + "input[name='password']"));
    }

    public WebElement getConfirmacaoSenhaInput(){

        return waits.visbilityOfElement(By.cssSelector(CARD_CADASTRO + "input[name='passwordConfirmation']"));
    }

    // Chave "Criar conta com saldo". O checkbox fica escondido atras do desenho da chave,
    // entao devolvemos o elemento sem esperar ficar "visivel" e a Task clica via JavaScript.
    public WebElement getCriarComSaldoToggle(){

        return driver.findElement(By.id("toggleAddBalance"));
    }

    public WebElement getCadastrarButton(){

        return waits.clickableElement(By.xpath("//button[normalize-space()='Cadastrar']"));
    }
}

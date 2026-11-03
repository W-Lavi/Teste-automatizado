package PageObjects;

import Framework.Browser.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Tela inicial do BugBank: card de login (o card de cadastro fica na CadastroPage)
public class LoginPage {

    // A tela tem DOIS formularios com campos "email" e "password" (login e cadastro).
    // Por isso o seletor comeca pela classe do card, para nao pegar o campo errado.
    private static final String CARD_LOGIN = ".card__login ";

    private WebDriver driver;
    private Waits waits;

    public LoginPage (WebDriver driver){

        this.driver = driver;
        waits = new Waits(this.driver);
    }

    public WebElement getEmailInput(){

        return waits.visbilityOfElement(By.cssSelector(CARD_LOGIN + "input[name='email']"));
    }

    public WebElement getPasswordInput(){

        return waits.visbilityOfElement(By.cssSelector(CARD_LOGIN + "input[name='password']"));
    }

    public WebElement getAcessarButton(){

        return waits.clickableElement(By.xpath("//button[normalize-space()='Acessar']"));
    }

    public WebElement getRegistrarButton(){

        return waits.clickableElement(By.xpath("//button[normalize-space()='Registrar']"));
    }
}

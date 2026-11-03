package Framework.Browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Esperas explicitas: em vez de Thread.sleep, o teste espera so o tempo necessario
public class Waits {

    private WebDriver driver;

    public Waits (WebDriver driver){

        this.driver = driver;
    }

    // Espera o elemento aparecer na tela e devolve ele (usado nas classes de PageObjects)
    public WebElement visbilityOfElement(By by){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    // Espera o elemento aparecer E estar clicavel (usado antes de clicar em botoes)
    public WebElement clickableElement(By by){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    // Espera o elemento sumir da tela (usado para esperar o modal fechar)
    public void invisibilityOfElement(By by){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}

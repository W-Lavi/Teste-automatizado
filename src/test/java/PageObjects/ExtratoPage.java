package PageObjects;

import Framework.Browser.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Tela "Extrato": saldo disponivel e lista de movimentacoes
public class ExtratoPage {

    private WebDriver driver;
    private Waits waits;

    public ExtratoPage (WebDriver driver){

        this.driver = driver;
        waits = new Waits(this.driver);
    }

    // "R$ 750,00" (saldo disponivel no topo do extrato)
    public WebElement getSaldoDisponivelText(){

        return waits.visbilityOfElement(By.id("textBalanceAvailable"));
    }

    // Procura a linha do extrato pela DESCRICAO da transferencia (que e unica por execucao)
    // e devolve o elemento do VALOR dessa mesma linha.
    // Lendo xpath: acha o texto da descricao -> sobe ate o bloco da linha -> desce ate o valor.
    public WebElement getValorDaTransacao(String descricao){

        By valor = By.xpath("//*[@id='textDescription'][normalize-space()='" + descricao + "']"
                + "/ancestor::*[.//*[@id='textTransferValue']][1]"
                + "//*[@id='textTransferValue']");
        return waits.visbilityOfElement(valor);
    }
}

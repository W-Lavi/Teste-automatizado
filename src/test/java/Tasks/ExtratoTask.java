package Tasks;

import Framework.Dados.Conta;
import PageObjects.GenericPage;
import PageObjects.HomePage;
import Validacao.ExtratoValidacao;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;

public class ExtratoTask {

    private WebDriver driver;
    private HomePage homePage;
    private GenericPage genericPage;
    private ExtratoValidacao extratoValidacao;

    public ExtratoTask (WebDriver driver){

        this.driver = driver;
        homePage = new HomePage(this.driver);
        genericPage = new GenericPage(this.driver);
        extratoValidacao = new ExtratoValidacao(this.driver);
    }

    // Abre o extrato, valida saldo e a transferencia, e volta para a tela inicial
    public void validarExtrato(Conta conta, String descricao, BigDecimal valorTransferido, String nomeDoPrint){

        homePage.getExtratoButton().click();

        extratoValidacao.validaSaldo(conta, nomeDoPrint + " - saldo");
        extratoValidacao.validaTransacao(descricao, valorTransferido, nomeDoPrint + " - transacao");

        genericPage.getVoltarButton().click();
    }
}

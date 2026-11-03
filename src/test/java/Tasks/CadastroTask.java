package Tasks;

import Framework.Dados.Conta;
import PageObjects.CadastroPage;
import PageObjects.GenericPage;
import PageObjects.LoginPage;
import Validacao.CadastroValidacao;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import Framework.Browser.Waits;
import org.openqa.selenium.By;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroTask {

    private WebDriver driver;
    private LoginPage loginPage;
    private CadastroPage cadastroPage;
    private GenericPage genericPage;
    private CadastroValidacao cadastroValidacao;
    private Waits waits;

    public CadastroTask (WebDriver driver){

        this.driver = driver;
        loginPage = new LoginPage(this.driver);
        cadastroPage = new CadastroPage(this.driver);
        genericPage = new GenericPage(this.driver);
        cadastroValidacao = new CadastroValidacao(this.driver);
        waits = new Waits(this.driver);
    }

    // Cadastra uma conta COM saldo e guarda numero/digito dentro do objeto Conta
    public void cadastrarContaComSaldo(Conta conta){

        // Recarrega a pagina para o formulario comecar VAZIO e com a chave desligada.
        // (Sem isso, o BugBank mantem o que foi digitado no cadastro anterior e o texto da segunda conta
        // era digitado "em cima" do da primeira.) As contas ja criadas continuam salvas no localStorage,
        // que nao e apagado ao recarregar a pagina.
        driver.navigate().refresh();

        loginPage.getRegistrarButton().click();

        cadastroPage.getEmailInput().sendKeys(conta.getEmail());
        cadastroPage.getNomeInput().sendKeys(conta.getNome());
        cadastroPage.getSenhaInput().sendKeys(conta.getSenha());
        cadastroPage.getConfirmacaoSenhaInput().sendKeys(conta.getSenha());

        // Liga "Criar conta com saldo" so se ainda estiver desligada
        // (clique via JavaScript porque o checkbox e escondido)
        if (!cadastroPage.getCriarComSaldoToggle().isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cadastroPage.getCriarComSaldoToggle());
        }

        cadastroPage.getCadastrarButton().click();

        // Valida o modal e captura o numero da conta ("A conta 123-4 foi criada com sucesso")
        cadastroValidacao.validaContaCriada();
        capturarNumeroDaConta(conta, genericPage.getModalText().getText());

        genericPage.getModalCloseButton().click();
        waits.invisibilityOfElement(By.id("modalText"));
    }

    // Pega "123-4" do texto do modal: numero = 123, digito = 4
    private void capturarNumeroDaConta(Conta conta, String textoModal){

        Matcher matcher = Pattern.compile("(\\d+)-(\\d+)").matcher(textoModal);

        if (!matcher.find()) {
            throw new IllegalStateException("Nao encontrei o numero da conta no modal: " + textoModal);
        }

        conta.setNumero(matcher.group(1));
        conta.setDigito(matcher.group(2));
    }
}

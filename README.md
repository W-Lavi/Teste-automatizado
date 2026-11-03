# Automação Web - BugBank

Prova de Automação Web (Profª Francielle Vareira - Escola Profissional Fundatec).
Site testado: https://bugbank.netlify.app/

## O que o teste faz

1. Acessa o BugBank
2. Cadastra **duas contas** com saldo (nome, e-mail e senha gerados aleatoriamente)
3. Entra na conta de origem e transfere um valor para a conta de destino
4. Abre o extrato da origem e valida o saldo (saldo inicial - valor transferido)
5. Entra na conta de destino e valida no extrato que o valor foi recebido (saldo inicial + valor transferido)

## Tecnologias

Java, Selenium WebDriver, JUnit 5, Maven, Datafaker (dados aleatórios), WebDriverManager (baixa o driver do Chrome) e Allure (relatório com prints).
É a mesma base do projeto feito em aula.

## Pré-requisitos

- JDK instalado (o `pom.xml` usa a mesma versão do projeto da professora: Java 27. Se o seu for outro, troque em `maven.compiler.source`, `maven.compiler.target` e no `maven-compiler-plugin`)
- Maven instalado (`mvn -v` deve funcionar)
- Google Chrome instalado

## Como executar

Na pasta do projeto (onde está o `pom.xml`):

```bash
mvn clean test
```

O Chrome abre, o teste roda sozinho e fecha no final.

### Relatório (opcional)

```bash
mvn allure:serve
```

Abre o relatório do Allure com os prints de cada etapa.

## Estrutura

```
src/main/java/Framework
    Browser/      DriverManager, TypeBrowser (abre o navegador) e Waits (esperas)
    Dados/        Conta (guarda nome, e-mail, senha, número, dígito e saldo esperado)
    Utils/        FakersGeneration (dados aleatórios), Moeda (formata "R$ 1.000,00"),
                  FileOperations (lê .properties) e TakeScreenshot (print no relatório)
    TestBase      abre o site antes de cada teste e fecha o navegador depois
src/main/resources/Properties
    url.properties            endereço do site
    transferencia.properties  saldo inicial e valor da transferência
src/test/java
    PageObjects/  onde estão os elementos de cada tela (só localizam, não decidem nada)
    Tasks/        os passos do usuário (cadastrar, logar, transferir, abrir extrato)
    Validacao/    as conferências (asserts)
    TestCases/    o teste, que junta tudo na ordem do enunciado
```

## Pontos importantes

- O BugBank guarda as contas no **localStorage** do navegador. Por isso o teste usa um único navegador do começo ao fim: as duas contas são criadas e usadas na mesma sessão.
- O número da conta (ex.: `123-4`) só aparece no modal após o cadastro. O teste captura esse número e usa na transferência.
- Os saldos esperados são calculados no próprio teste (`saldoInicial - valor` e `saldoInicial + valor`), sem número "chumbado".
- Para mudar o valor da transferência, edite `src/main/resources/Properties/transferencia.properties`.

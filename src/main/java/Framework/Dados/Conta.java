package Framework.Dados;

import java.math.BigDecimal;

// Guarda os dados de uma conta durante o teste.
// Nome, e-mail e senha sao gerados antes do cadastro.
// Numero e digito so existem depois do cadastro: o BugBank mostra no modal
// e a gente captura para usar na transferencia.
public class Conta {

    private String nome;
    private String email;
    private String senha;
    private String numero;
    private String digito;
    private BigDecimal saldo;   // saldo que a gente ESPERA que a conta tenha

    public Conta(String nome, String email, String senha, BigDecimal saldo){

        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.saldo = saldo;
    }

    public String getNome()  { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public String getNumero(){ return numero; }
    public String getDigito(){ return digito; }
    public BigDecimal getSaldo(){ return saldo; }

    public void setNumero(String numero){ this.numero = numero; }
    public void setDigito(String digito){ this.digito = digito; }
    public void setSaldo(BigDecimal saldo){ this.saldo = saldo; }
}

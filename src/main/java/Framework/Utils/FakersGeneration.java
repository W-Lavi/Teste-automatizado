package Framework.Utils;

import net.datafaker.Faker;

import java.text.Normalizer;
import java.util.Locale;

// Gera dados aleatorios: a cada execucao o teste usa pessoas diferentes
public class FakersGeneration {

    private Faker faker;

    public FakersGeneration (){

        faker = new Faker(Locale.of("pt", "BR"));
    }

    // Tira os acentos ("Joao" em vez de "João"): evita problema em e-mail e campos
    public static String removerAcentos(String texto) {
        return Normalizer
                .normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }

    public String getFisrtName(){

        return removerAcentos(faker.name().firstName());
    }

    public String getLastName(){

        return removerAcentos(faker.name().lastName());
    }

    // Nome completo sem acento, ex: "Maria Souza"
    public String getNomeCompleto(){

        return getFisrtName() + " " + getLastName();
    }

    // E-mail unico por conta, ex: "maria.souza4821@teste.com"
    // O numero aleatorio no final evita e-mail repetido entre as duas contas
    public String getEmail(String nome){

        String base = nome.toLowerCase().replace(" ", ".");
        return base + faker.number().numberBetween(1000, 9999) + "@teste.com";
    }

    // Senha aleatoria, ex: "Senha@5731"
    public String getSenha(){

        return "Senha@" + faker.number().numberBetween(1000, 9999);
    }
}

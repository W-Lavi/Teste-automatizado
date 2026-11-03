package Framework.Utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

// Converte um valor numerico no texto que o BugBank mostra na tela
public class Moeda {

    // 1000 -> "R$ 1.000,00" | 750 -> "R$ 750,00"
    public static String formatar(BigDecimal valor){

        DecimalFormat formato = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.of("pt", "BR")));
        return "R$ " + formato.format(valor);
    }

    // Troca o espaco "invisivel" (nao quebravel) por espaco normal, senao a comparacao falha
    public static String normalizar(String texto){

        return texto.replace('\u00A0', ' ').trim();
    }
}

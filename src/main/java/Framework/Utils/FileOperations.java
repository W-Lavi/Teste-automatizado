package Framework.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class FileOperations {

    private static final String DIR_PATH_PROPERTIES =
            System.getProperty("user.dir") + File.separator + "src" + File.separator +
                    "main" + File.separator + "resources" + File.separator +
                    "Properties" + File.separator;

    // Le um arquivo .properties da pasta src/main/resources/Properties
    // Ex: getProperties("url").getProperty("index")
    public static Properties getProperties(String fileName){

        Properties prop = new Properties();

        try (InputStream input = new FileInputStream(new File(DIR_PATH_PROPERTIES + fileName + ".properties"))) {

            prop.load(input);

        } catch (Exception e) {

            System.out.println("Nao foi possivel carregar o arquivo de propriedade: " + fileName);
        }

        return prop;
    }
}

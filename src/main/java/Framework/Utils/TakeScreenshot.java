package Framework.Utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

// Tira print da tela e anexa no relatorio do Allure
public class TakeScreenshot {

    public static void screenshotPage(WebDriver webDriver, String name){

        byte[] screenshotBytes = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        Allure.attachment(name, new ByteArrayInputStream(screenshotBytes));
    }
}

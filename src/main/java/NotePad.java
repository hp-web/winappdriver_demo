import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class NotePad {
    private static WindowsDriver CalculatorSession = null;
    private static WebElement CalculatorResult = null;
    @BeforeClass
    public static void setup() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", "C:\\Windows\\System32\\notepad.exe");
            CalculatorSession = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
            CalculatorSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            CalculatorResult = CalculatorSession.findElementByAccessibilityId("CalculatorResults");
            Assert.assertNotNull(CalculatorResult);

        }catch(Exception e){
            e.printStackTrace();
        } finally {
        }
    }
    @Test
    public void notePadTest() throws InterruptedException {
        WebElement element = CalculatorSession.findElementByClassName("Edit");
        element.sendKeys("nice to meet you!!");
        element.sendKeys(Keys.ENTER);
        element.sendKeys("Time is");
        element.sendKeys(Keys.ENTER);
        element.sendKeys(Keys.F5);
        element.sendKeys(Keys.LEFT_CONTROL+"s");
        Thread.sleep(2000);
        fileName().clear();
        fileName().sendKeys("test.txt");
        save().click();
    }

    public WebElement fileName(){
        //WebElement xpath = CalculatorSession.findElementByXPath("/Pane[@ClassName=\"#32769\"][@Name=\"Desktop 1\"]/Window[@ClassName=\"Notepad\"][@Name=\"*Untitled - Notepad\"]/Window[@ClassName=\"#32770\"][@Name=\"Save As\"]/Pane[@ClassName=\"DUIViewWndClassName\"]/ComboBox[@Name=\"File name:\"][@AutomationId=\"FileNameControlHost\"]/Edit[@ClassName=\"Edit\"][@Name=\"File name:\"]");
        //WebElement xpath1 = CalculatorSession.findElementByClassName("Edit");
        WebElement name = CalculatorSession.findElementByAccessibilityId("1001");
        return name;
    }

    public WebElement save(){
        WebElement save = CalculatorSession.findElementByAccessibilityId("1");

        return save;
    }

    @AfterTest
    public void quitDriver(){


    }
}

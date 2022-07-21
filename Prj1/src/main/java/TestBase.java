import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Links;

import java.time.Duration;

public class TestBase {
    private static WebDriver webDriver;
    protected static Page homePage;

   @Before
    public void launchApp(){
        setWebDriver();
        webDriver = new ChromeDriver();
        webDriver.get(Links.APP_PAGE);
        homePage = new Page(webDriver);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(homePage.appElement()));

    }

    @After
    public void closeBrowser(){
        webDriver.quit();
    }

    public static WebDriver getWebDriver(){
        return webDriver;
    }

    private static void setWebDriver(){
        if(System.getProperty("os.name").contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        }
        else{
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        }
    }
}

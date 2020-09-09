package manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Collections;


public class DriverFactor {

    public static String scenarioName;
    private WebDriver driver;
    private String browser;


    public DriverFactor() {
        browser = FileReaderManager.getInstance().getConfigReader().getBrowserName();
    }

    public WebDriver getDriver() {
        if (driver == null)
            driver = startDriver();
        return driver;
    }

    private WebDriver startDriver() {
        if ("Chrome".equalsIgnoreCase(browser)) {
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(false);
            options.setExperimentalOption("useAutomationExtension", false);
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        } else if ("FireFox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
        return driver;

    }

    public void closeDriver() {
        driver.quit();
    }


}

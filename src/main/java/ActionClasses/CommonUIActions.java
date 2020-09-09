package ActionClasses;

import manager.FileReaderManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utility.SeleniumUtility;

public class CommonUIActions extends SeleniumUtility {
    public CommonUIActions(WebDriver driver) {
        super(driver);
    }

    @FindBy  (xpath= "//button[text()='Accept All']")
    WebElement acceptCookies;

    public void openMainURL(String url) {
        openUrl(url);
    }

    public void setAcceptCookies() {
        for(int i=0;i<10;i++)
        try {
            clickOnElement(acceptCookies,10);
            return;
        }catch (Exception e){
            if(i==9) {
                throw new RuntimeException("Unable to locate Ok button for accepting cookies");
            }
        }

    }
}

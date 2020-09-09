package ActionClasses;

import org.openqa.selenium.WebDriver;
import utility.SeleniumUtility;

public class CommonUIActions extends SeleniumUtility {
    public CommonUIActions(WebDriver driver) {
        super(driver);
    }

    public void openMainURL(String url) {
        openUrl(url);
    }

}

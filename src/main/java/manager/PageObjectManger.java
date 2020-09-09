package manager;


import ActionClasses.CommonUIActions;
import ActionClasses.DateSelectorPageActions;
import org.openqa.selenium.WebDriver;
public class PageObjectManger {

	public WebDriver driver;
	public CommonUIActions commonUIActions;
	public DateSelectorPageActions dateSelectorPageActions;


	public PageObjectManger(WebDriver driver) {
		this.driver = driver;
	}

	public CommonUIActions getCommonActionsScreen() {
		return (commonUIActions == null) ? commonUIActions = new CommonUIActions(driver) : commonUIActions;
	}

	public DateSelectorPageActions getDateSelectorPageActionsScreen() {
		return (dateSelectorPageActions == null) ? dateSelectorPageActions = new DateSelectorPageActions(driver) : dateSelectorPageActions;
	}
	

}



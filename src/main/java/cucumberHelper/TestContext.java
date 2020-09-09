package cucumberHelper;
import manager.DriverFactor;
import manager.PageObjectManger;

public class TestContext {
	private PageObjectManger pageObjectManger;
	private DriverFactor driverFactory;
	public ScenarioContext scenarioContext;
	public TestContext(){
		driverFactory = new DriverFactor();
		pageObjectManger = new PageObjectManger(driverFactory.getDriver());
		scenarioContext = new ScenarioContext();
	}

	public DriverFactor getDriverFactory() {
		return driverFactory;
	}

	public PageObjectManger getPageObjectManger() {
		return pageObjectManger;
	}

	public ScenarioContext getScenarioContext() {
		 return scenarioContext;
		 }
}

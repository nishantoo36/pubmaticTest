package StepFiles;
import com.cucumber.listener.Reporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumberHelper.TestContext;
import org.testng.annotations.BeforeClass;
import runner.TestRunner;
import utility.SeleniumUtility;
import java.io.File;
import java.io.IOException;

import static utility.ConstantVars.*;


public class Hooks extends TestRunner {
	public static Scenario scenario;
	TestContext testContext;

	public Hooks(TestContext context) {
		testContext = context;
	}


	@Before
	public void Logging(Scenario scenario) {
		this.scenario = scenario;
	}

	@Before
	public void beforeScenario(Scenario scenario) {
		featureName = scenario.getId().split(";")[0];
		System.out.println("-----------------Start of Scenario-----------------");
		System.out.println(scenario.getName());
	}

	@After(order = 0)
	public void afterScenario() {
		System.out.println("-----------------End of Scenario-----------------");
	    testContext.getDriverFactory().closeDriver();
	}

	@After(order = 1)
	public void afterScenario(Scenario scenario) {

		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			try {
				String path = screenshotPath + screenshotName + ".png";
				new SeleniumUtility(testContext.getDriverFactory().getDriver()).takeScreenShot(screenshotName,screenshotPath);
				File destinationPath = new File(path);
				Reporter.addScreenCaptureFromPath(destinationPath.toString());
			} catch (IOException e) {
			}
		}		
	}
	
	@BeforeClass
	public static void testbeforeclass() {
		System.out.println("-----------------Before Class-----------------");
	}

	
	
}

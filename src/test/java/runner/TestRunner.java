package runner;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import manager.DriverFactor;
import manager.FileReaderManager;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;

import java.io.File;


@RunWith(Cucumber.class)
@CucumberOptions(
 features = "Features"
 ,glue={"StepFiles"},
 monochrome = true,
 plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/AutomationReport.html","pretty", "json:target/cucumber-reports/AutomationReport.json",
		 "junit:target/cucumber-reports/AutomationReport.xml","rerun:target/rerun.txt"}
 )

public class TestRunner  {
	public static String stepLog;
	 public static void writeExtentReport() {
	 Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath()));
	}
}


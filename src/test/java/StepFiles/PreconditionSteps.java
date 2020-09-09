package StepFiles;
import ActionClasses.CommonUIActions;
import cucumber.api.java.en.When;
import cucumberHelper.TestContext;



public class PreconditionSteps extends Logging {
    TestContext testContext;
    CommonUIActions commonUIActions;

    public PreconditionSteps(TestContext context) {
        testContext = context;
        commonUIActions = testContext.getPageObjectManger().getCommonActionsScreen();
    }

    @When("^user open \"([^\"]*)\"$")
    public void userOpen(String url)  {
        commonUIActions.openMainURL(url);
    }
}

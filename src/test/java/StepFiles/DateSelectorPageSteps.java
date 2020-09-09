package StepFiles;

import ActionClasses.DateSelectorPageActions;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberHelper.TestContext;
import static ActionClasses.DateSelectorPageActions.dates;
import static ActionClasses.DateSelectorPageActions.minimumRate;

public class DateSelectorPageSteps extends Logging {
    TestContext testContext;
    DateSelectorPageActions dateSelectorPageActions;

    public DateSelectorPageSteps(TestContext context) {
        testContext = context;
        dateSelectorPageActions = testContext.getPageObjectManger().getDateSelectorPageActionsScreen();
    }

    @When("^user First Select Location from default values$")
    public void userFirstSelectLocationFromDefaultValues() {
        log("Select location from default list");
        dateSelectorPageActions.selectFirstDefaultCity();
        log("Location selected successfully from default list");
    }

    @When("^user select date as \"([^\"]*)\"$")
    public void userSelectDateAs(String arg0) throws Throwable {
        log("User want to select date as "+arg0);
        dateSelectorPageActions.selectDepartureDateField();
        dates = dateSelectorPageActions.lowestRateDateSuggestion(arg0);

    }

    @Then("^It should suggest the date which is lowest in list and nearest to the given date$")
    public void itShouldSuggestTheDateWhichIsLowestInListAndNearestToTheGivenDate() {
        log("\n Suggest the nearest minimum date as below : \n");
        for (String val : dates) {
            log("Nearest minimum rate date for months is " + val + " and rate is " + minimumRate);
        }
    }
}

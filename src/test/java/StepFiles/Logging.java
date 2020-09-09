package StepFiles;

import com.cucumber.listener.Reporter;
import runner.TestRunner;

import static StepFiles.Hooks.scenario;

public class Logging extends TestRunner {

    public static void log(String args) {
        stepLog=args;
        scenario.write(args + "\n");
        Reporter.addStepLog(args+"\n");
    }


}

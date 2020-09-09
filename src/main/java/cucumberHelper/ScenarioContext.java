package cucumberHelper;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
	
	private  Map<String, Object> scenarioContext;
	 
    public ScenarioContext(){
        scenarioContext = new HashMap<String, Object>();
    }
}

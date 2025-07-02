package hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.*;
import utilities.ExtentManager;
import utilities.ExtentTestManager;

public class Hooks extends ExtentManager{

    @Before
    public void getTstScneario(Scenario scenario){
        ExtentManager.getInstance();
        String scenarioName = scenario.getName();
        ExtentTest test = getExtent().createTest(scenarioName, "Executing " + scenarioName);
        ExtentTestManager.setTest(test);
    }


    @AfterAll
    public static void closeReport(){
        getExtent().flush();
    }

}

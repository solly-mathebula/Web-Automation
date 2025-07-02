package hooks;


import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.*;
import utilities.CommunicationChannels;
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

        try{
            String emailBody = "Good day,<br><br>I trust that you are well. Please find attached web automation report.<br><br>" +
                    "<b>Kind regards,<br>Automation Team</b>";
            String reportPath = "Report/Automation-Report.html";
            CommunicationChannels.sendReportViaMail("smathebula@getabyte.co.za","Takealot Web Automation Report",emailBody,reportPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

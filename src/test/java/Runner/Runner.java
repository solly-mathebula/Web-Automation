package Runner;

import utilities.TestListener;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;


@CucumberOptions(
        features = "src/test/resources/Features",
        glue = {"StepDefinitions", "utilities", "hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-html-report",
                "json:target/cucumber.json",
                "utilities.CommonFunctions"
        },
        monochrome = true
)

@Listeners(TestListener.class)
public class Runner extends AbstractTestNGCucumberTests {

}

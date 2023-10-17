package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = {"src/test/resources/features"}, glue = {"stepDefinitions"},tags="@Google", monochrome = true, dryRun = false
,plugin = {"pretty",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
})
public class MainRunner extends AbstractTestNGCucumberTests {
   // in parallel test (memory consumable)
     @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

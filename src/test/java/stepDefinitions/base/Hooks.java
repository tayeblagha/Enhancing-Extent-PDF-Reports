package stepDefinitions.base;

import static driver.DriverFactory.cleanupDriver;
import static driver.DriverFactory.getDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.sql.Timestamp;

public class Hooks  {

    public  WebDriver driver;

    @Before
    public void setup() throws IOException {
      getDriver();
    }
    @AfterStep
    public void captureExceptionImage(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String timeMilliseconds = Long.toBinaryString(timestamp.getTime());
            byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png",timeMilliseconds);

        }
    }


    @After
    public void tearDown() {

        cleanupDriver();
    }
}

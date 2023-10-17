package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    public static ThreadLocal<WebDriver> webdriver = new ThreadLocal<>();
    public static WebDriver getDriver() throws IOException {
        if (webdriver.get() == null) {
            webdriver.set(createDriver());
        }
        return webdriver.get();
    }
    private static WebDriver createDriver() throws IOException {
        WebDriver driver = null;
        String browserTYpe = getBrowserType();
        switch (browserTYpe) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--no-sandbox");
//                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                break;
            }

            case "firefox" -> {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/java/driver/drivers/geckodriver");
                System.setProperty("webdriver.firefox.bin", "/snap/bin/firefox");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
               // firefoxOptions.addArguments("--remote-allow-origins=*");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            }
            case "chromeci" -> {
                //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/driver/drivers/chromedriver");
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");

                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                break;
            }
        }
       // driver.manage().window().maximize();
        return driver;

    }

    public static void cleanupDriver() {
        webdriver.get().quit();
        webdriver.remove();
    }
    private static String getBrowserType() {
        String browserType = null;
        String browserTypeRemoteValue = System.getProperty("browserType");

        try {
            if (browserTypeRemoteValue == null || browserTypeRemoteValue.isEmpty()) {
                Properties properties = new Properties();
                FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/driver/properities/config.properties");
                properties.load(file);
                browserType = properties.getProperty("browser").toLowerCase().trim();
            } else {
                browserType = browserTypeRemoteValue;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return browserType;
    }



}

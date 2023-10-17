package pageObjects;

import driver.DriverFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Global_vars;

import java.io.IOException;
import java.time.Duration;

public class Base_PO {
    public Base_PO() throws IOException {
        PageFactory.initElements(getDriver(), this);
    }

    public WebDriver getDriver() throws IOException {
        return DriverFactory.getDriver();
    }

    public void navigateTo_URL(String url) throws IOException {
        getDriver().get(url);
    }

    public String generateRandomNumber(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    public String generateRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public void sendKeys(By by, String textToType) throws IOException {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_vars.Explicit_Timeout));
        wait.until(ExpectedConditions.elementToBeClickable(by)).sendKeys(textToType);
    }

    public void sendKeys(WebElement element, String textToType) throws IOException {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_vars.Explicit_Timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(textToType);
    }
    public void sendKeys(WebElement element,Keys key) throws IOException {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_vars.Explicit_Timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(key);

    }

    public void waitFor(By by) throws IOException {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_vars.Explicit_Timeout));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public void waitFor(WebElement element) throws IOException {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_vars.Explicit_Timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForWebElementAndClick(By by) throws IOException {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_vars.Explicit_Timeout));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public void waitForWebElementAndClick(WebElement element) throws IOException {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_vars.Explicit_Timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void waitForAlert_And_ValidateText(String text) throws IOException {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_vars.Explicit_Timeout));
        wait.until(ExpectedConditions.alertIsPresent());
        String alert_Message_Text = getDriver().switchTo().alert().getText();
        Assert.assertEquals(alert_Message_Text, text);
    }
}

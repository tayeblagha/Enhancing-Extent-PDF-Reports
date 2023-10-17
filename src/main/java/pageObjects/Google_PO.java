package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utils.Global_vars;

import java.io.IOException;

public class Google_PO extends Base_PO {

    private @FindBy(name = "q")
    WebElement searchInput;

    private @FindBy(name = "btnK")
    WebElement searchButton;




    public Google_PO() throws IOException {
        super();
    }

    public void navigateToGoogle() throws IOException {
        navigateTo_URL("https://www.google.com");
    }

    public void performSearch(String query) throws IOException {
        enterSearchQuery(query);
        clickSearchButton();
    }

    public void enterSearchQuery(String query) throws IOException {
        sendKeys(searchInput, query);
    }

    public void clickSearchButton() throws IOException {
        waitForWebElementAndClick(searchButton);
    }












}

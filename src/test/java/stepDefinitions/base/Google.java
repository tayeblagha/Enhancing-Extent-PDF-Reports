package stepDefinitions.base;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.Base_PO;
import pageObjects.Google_PO;

import java.io.IOException;

public class Google extends Base_PO {
    private Google_PO login_po;

    public Google(Google_PO login_po) throws IOException {
        super();
        this.login_po = login_po;
    }
    @Given("I Connect to google")
    public void i_connect_to_google() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        login_po.navigateToGoogle();
    }
    @When("I go to google")
    public void i_go_to_google() throws IOException {

    }
    @When("I put my search")
    public void i_put_my_search() throws IOException {
        login_po.performSearch("I love DevOps");

    }
    @Then("I should be presented with the successful result")
    public void i_should_be_presented_with_the_successful_result() throws IOException {
    }




}

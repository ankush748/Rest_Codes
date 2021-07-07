package StepDefinition;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class Login {

	@Given("^user navigates to the website javatpoint\\.com$")
	public void user_navigates_to_the_website_javatpoint_com() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("abcd");
	}

	@And("^there user logs in through Login Window by using Username as \"([^\"]*)\" and Password as \"([^\"]*)\"$")
	public void there_user_logs_in_through_Login_Window_by_using_Username_as_and_Password_as(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("Given abc");
	}

	@Then("^login must be successful\\.$")
	public void login_must_be_successful() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions

	}

}
package StepDefinition;

import cucumber.api.java.en.Given;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import serialize.Place;

import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;

public class stepDefinition_Add_place extends Utils {
	
	
		@Given("^Add Place Payload with \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
	public void add_Place_Payload(String name,String accuracy, String phone) throws Throwable {
		
	    /// Write code here that turns the phrase above into concrete actions
		place1=(Place) TestDataBuild.DataBuilder(place1, name, accuracy, phone);
		
		
		
	    
	}

	@When("^user calls \"([^\"]*)\" with Post HTTP request$")
	public void user_calls_with_Post_HTTP_request(String resource) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		RequestSpecification request_spec=Utils.requestspecbuilder();
		ResponseSpecification response_spec=Utils.responsespecbuilder();
		
		//APIResources resource1=APIResources.valueOf(resource);
		
		response= given().spec(request_spec).body(place1).when().post().then().spec(response_spec).extract().response();
	    
	}

	@Then("^the API call returns success with status code (\\d+) and scope value is \"([^\"]*)\"$")
	public void the_API_call_returns_success_with_status_code(int arg1,String scope) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if(response.getStatusCode()==arg1) {
			System.out.println("Pass");
		}
		
		String response_string=response.asString();
		JsonPath jp1=new JsonPath(response_string);
		String scope_actual=jp1.getString("scope");
		if(scope.equals(scope_actual)) {
			System.out.println("Scope is App");
		}
		
	    
	}
	

}

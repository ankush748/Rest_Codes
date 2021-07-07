package StepDefinition;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.hamcrest.Matchers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import serialize.Place;

public class Utils {
	
	
	String request;
	Response response;
	Place place1=new Place();
	public static RequestSpecification requestspecbuilder() throws FileNotFoundException {
		FileOutputStream fos=new FileOutputStream("logging.txt");
		RequestSpecification request_builder=new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(new PrintStream(fos))).
				addFilter(ResponseLoggingFilter.logResponseTo(new PrintStream(fos))).build();
		return request_builder;
		}
		
		public static ResponseSpecification responsespecbuilder() {
			
			ResponseSpecification response_builder=new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200)
			.expectBody("status", Matchers.equalToIgnoringCase("OK")).build();
			return response_builder;
			}

}

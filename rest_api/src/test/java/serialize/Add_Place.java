package serialize;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class Add_Place {
	
	@Test
	void Add_place() {
		//Place object
		List<String> types=new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		Place place=new Place(new Location("-38.383494","33.4234"), "50", "Frontline house", "(+91) 983 893 3937", "30, abc road khar",types 
				,"http://google.com", "French-IT");
		//Place(Location location, String accuracy, String name, String phone_number, String address, <String> types,String website, String language)
		
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().log().all().header("Content-Type","application/json").queryParam("key", "qaclick123")
		.body(place).when().post("/maps/api/place/add/json").then().log().all().extract().response().asString();
	}

}

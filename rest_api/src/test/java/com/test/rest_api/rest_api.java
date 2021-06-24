package com.test.rest_api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import files.Post_Place;
import files.Update_place;

public class rest_api {

	public static void main(String[] args) throws FileNotFoundException {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		File file=new File("C:\\Users\\Dell\\Downloads\\input_file.txt");
		InputStream is=new FileInputStream(file);
		
//		given().log().all().header("Content-Type","application/json").body(Post_Place.body()).when().post("maps/api/place/add/json").then().log().all().statusCode(200);
		
//	given().log().all().header("Content-Type","application/json").body(file).when().post("maps/api/place/add/json")
//	.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"));
//		
	String post_response=given().log().all().header("Content-Type","application/json").queryParams("key", "qaclick123")
	.body(Post_Place.body()).when().post("maps/api/place/add/json").
		then().extract().response().asString();
		
	JsonPath jp=new JsonPath(post_response);
	String response_status=jp.getString("scope");
//		System.out.println(response_status);
		String updated_address="Siliguri, Maha";
		given().log().all().header("Content-Type","application/json").baseUri("https://rahulshettyacademy.com")
		.queryParam("key", "qaclick123")
		.body(Update_place.update(jp.getString("place_id"), updated_address))
		.when().put("maps/api/place/update/json").then().log().all().extract().response().asString();
		
		
		
		Map<String,String> query_params=new HashMap<String, String>();
		query_params.put("key", "qaclick123");
		query_params.put("place_id", jp.getString("place_id"));
		
		given().log().all().header("Content-Type","application/json")
		.queryParams(query_params).when().get("maps/api/place/get/json")
		.then().log().all().extract().response();
		
//		
//		
//		
		
		
		
	
	
		
		
			

	}

}

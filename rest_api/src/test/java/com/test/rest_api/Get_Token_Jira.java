package com.test.rest_api;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class Get_Token_Jira {
	void from_response() {
		RestAssured.baseURI="http://localhost:8080";
		String response=given().log().all().header("Content-Type","application/json")
		.body("{ \"username\": \"krishhnakmr781\", \"password\": \"JiraS@1234\" }").when()
		.post("/rest/auth/1/session").then().log().all().extract().response().asString();
		
		JsonPath jp=new JsonPath(response);
		System.out.println(jp.getString("session.value"));
		}
	@Test
	void session_filter() {
		RestAssured.baseURI="http://localhost:8080";
		SessionFilter session=new SessionFilter();
		
		given().log().all().header("Content-Type","application/json")
		.body("{ \"username\": \"krishhnakmr781\", \"password\": \"JiraS@1234\" }").filter(session)
		.when().post("/rest/auth/1/session").then().log().all().extract().response().asString();
		
		
		//Add Comment To bug
		given().log().all().pathParam("id", "10006").header("Content-Type","application/json").filter(session).body("{\r\n"
				+ "    \"body\": \"Comments are secondary\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").when().post("/rest/api/2/issue/{id}/comment").then().log().all();
		}
	

	
	
	
}

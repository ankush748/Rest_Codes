package com.test.rest_api;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;


public class Parsing_json_Jira {
	
	@Test
	void comments_filter() {
		RestAssured.baseURI="http://localhost:8080";
		SessionFilter session=new SessionFilter();
		
		given().log().all().header("Content-Type","application/json").filter(session)
		.body("{ \"username\": \"krishhnakmr781\", \"password\": \"JiraS@1234\" }").when().post("/rest/auth/1/session")
		.then().log().all().assertThat().statusCode(200);
		
		String comments=given().log().all().header("Content-Type","application/json").filter(session).pathParam("id1", "10006")
		.when().get("/rest/api/2/issue/{id1}/comment").then().log().all().extract().response().asString();
		
		JsonPath jpath=new JsonPath(comments);
		int no_comments=jpath.getInt("comments.size()");
		
		for(int i=0;i<no_comments;i++) {
			String comment=jpath.getString("comments["+i+"].body");
			String id=jpath.getString("comments["+i+"].id");
			String key_update=jpath.getString("comments["+i+"].updateAuthor.email");
			System.out.println(key_update);
	//abc
			if(id.equals("10001")) {
				System.out.println(comment);
				break;
			}
		}
		
		
	}

}

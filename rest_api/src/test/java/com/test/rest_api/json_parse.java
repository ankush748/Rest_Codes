package com.test.rest_api;

import org.testng.annotations.Test;

import files.json_payload;
import io.restassured.path.json.JsonPath;

public class json_parse {
	
	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
		test5();
		test6();
	}
	
	public static void test1() {
		JsonPath json_path=new JsonPath(json_payload.payload());
		//1. Print No of courses returned by API
		System.out.println(json_path.getInt("courses.size()"));
	}
	
	public static void test2() {
		JsonPath json_path=new JsonPath(json_payload.payload());
		//2.Print Purchase Amount
		System.out.println(json_path.getString("dashboard.purchaseAmount"));
	}
	
	public static void test3() {
		JsonPath json_path=new JsonPath(json_payload.payload());
		//3. Print Title of the first course
		System.out.println(json_path.getString("courses[0].title"));
	}
	
	public static void test4() {
		JsonPath json_path=new JsonPath(json_payload.payload());
		//4.Print All course titles and their respective Prices
		int size=json_path.getInt("courses.size()");
		for(int i=0;i<size;i++) {
			System.out.println(json_path.getString("courses["+i+"].title"));
			System.out.println(json_path.getString("courses["+i+"].price"));
		}
	}
	
	public static void test5() {
		JsonPath json_path=new JsonPath(json_payload.payload());
		//5. Print no of copies sold by RPA Course
		int size=json_path.getInt("courses.size()");
		for(int i=0;i<size;i++) {
			if (json_path.getString("courses["+i+"].title").equals("RPA")) {
				System.out.println(json_path.getString("courses["+i+"].price"));
				break;
				
			}
			
		}
	}

	public static void test6() {
		JsonPath json_path=new JsonPath(json_payload.payload());
		//6. Verify if Sum of all Course prices matches with Purchase Amount
		int size=json_path.getInt("courses.size()");
		int sum=0;
		for(int i=0;i<size;i++) {
			sum+=Integer.valueOf(json_path.getString("courses["+i+"].price"))*
					Integer.valueOf(json_path.getString("courses["+i+"].copies"));
			
		}
		if(sum==json_path.getInt("dashboard.purchaseAmount")) {
			System.out.println("Verified");
		}
	}
}

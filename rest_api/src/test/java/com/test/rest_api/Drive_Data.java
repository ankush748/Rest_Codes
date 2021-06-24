package com.test.rest_api;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.post_dynamic_payload;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Drive_Data {
	
	@Test(dataProvider="drive_data")
	public void excel_drive_data(String phone,String lang) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().header("Content-Type","application/json").body(post_dynamic_payload.payload(lang, phone))
		.queryParam("key", "qaclick123").when().post("/maps/api/place/add/json").then().log().all().statusCode(200)
		.extract().response().asString();
		
		JsonPath jp1=new JsonPath(response);
		String place_id=jp1.getString("place_id");
		System.out.println(place_id);
		
	}
	
	
	@DataProvider(name="drive_data")
	public Object[][] getData() throws IOException{
		String excel_path="C:\\Users\\Dell\\Downloads\\test_sheet.xlsx";
		File file=new File(excel_path);
		FileInputStream fis=new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet=wb.getSheetAt(0);
		int rows=sheet.getPhysicalNumberOfRows();
		Row row1=sheet.getRow(0);
		int columns=row1.getPhysicalNumberOfCells();
		Object[][] obj = new Object[rows-1][columns];
		 for(int i=0;i<rows-1;i++) {
			 for(int j=0;j<columns;j++) {
				 System.out.printf("%d %d",i,j);
				 System.out.println();
				 obj[i][j]=sheet.getRow(i+1).getCell(j).toString();
				 System.out.println(obj[i][j]);
			 }
		 }
		
		return obj;
	}

}

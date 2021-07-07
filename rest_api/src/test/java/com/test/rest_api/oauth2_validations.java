package com.test.rest_api;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

public class oauth2_validations {
	WebDriver driver;
	
	@Test
	void bookmyshow() {
		
		String chrome_location=(System.getProperty("user.dir")+"\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver",chrome_location);
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		WebDriverWait wait=new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@type,'email')]")));
		driver.findElement(By.xpath("//input[contains(@type,'email')]")).sendKeys("loginuserqa@gmail.com");
		driver.findElement(By.xpath("//input[contains(@type,'email')]")).sendKeys(Keys.ENTER);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@type,'password')]")));
		driver.findElement(By.xpath("//input[contains(@type,'password')]")).sendKeys("UserQA123");
		driver.findElement(By.xpath("//input[contains(@type,'password')]")).sendKeys(Keys.ENTER);
		
		wait.until(ExpectedConditions.urlContains("https://rahulshettyacademy.com/getCourse.php?code="));
		String url=driver.getCurrentUrl();
		
		driver.quit();
		String code_split=url.split("code=")[1];
		String code=code_split.split("&")[0];
		System.out.println(code);
		
		Map<String,String> query_map=new HashMap<String,String>();
		query_map.put("code", code);
		query_map.put("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com");
		query_map.put("client_secret", "erZOWM9g3UtwNRj340YYaK_W");
		query_map.put("grant_type", "authorization_code");
		query_map.put("redirect_uri", "https://rahulshettyacademy.com/getCourse.php");
		
		//if any query parameter has special characters encoding should be false
		String response1=given().urlEncodingEnabled(false).log().all().queryParams(query_map).when().post("https://www.googleapis.com/oauth2/v4/token").
				then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath jp=new JsonPath(response1);
		String access_code=jp.getString("access_token");
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("access_token", access_code).when().get("/getCourse.php").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath jp1=new JsonPath(response);
		//String access_code=jp.getString("access_token");
		
	}

}
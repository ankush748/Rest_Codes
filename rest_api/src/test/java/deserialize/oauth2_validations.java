package deserialize;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

public class oauth2_validations {
	WebDriver driver;
	
	@Test(priority=1)
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
		
	//	String code="4%2F0AX4XfWjhA6hI5onMRGTb9h7-LPJeBRlK5IpX47ctbqbHyxf4NiXPBwq6Uc7tw1RyuZzP5Q";
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
		Response_pojo rp=given().queryParam("access_token", access_code).expect().defaultParser(Parser.JSON).when().get("/getCourse.php").
		as(Response_pojo.class);
		
		System.out.println(rp.getCourses().getWebAutomation().get(1).getCourseTitle());
		int price=0;
		for(int i=0;i<rp.getCourses().getMobile().size();i++) {
			price+=Integer.valueOf(rp.getCourses().getMobile().get(i).getPrice());
		}
		for(int i=0;i<rp.getCourses().getApi().size();i++) {
			price+=Integer.valueOf(rp.getCourses().getApi().get(i).getPrice());
		}
		for(int i=0;i<rp.getCourses().getWebAutomation().size();i++) {
			price+=Integer.valueOf(rp.getCourses().getWebAutomation().get(i).getPrice());
		}
		
		System.out.println(price);
		
		//String access_code=jp.getString("access_token");
		
	}

}
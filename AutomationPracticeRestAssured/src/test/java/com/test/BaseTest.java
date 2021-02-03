package com.test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.utils.ExtentReportListner;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

@Listeners(ExtentReportListner.class)
public class BaseTest extends ExtentReportListner{
	
	SoftAssert asserts = new SoftAssert();
	@BeforeTest
	public void baseTest() {
		RestAssured.baseURI = "http://api.openweathermap.org/data";
	}

	
/**
 *   Demo for the Assignment
 */
	@Test(priority = 1, enabled = true)
	public void testCase1() {
		Response response = RestAssured
		.given()
			.queryParam("q", "London,uk")
		.when()
		.get()
		
		;
	  ResponseBody respBody = response.getBody(); 
	  String strBody = respBody.asString(); 
	  Assert.assertTrue(strBody.contains("Invalid API key. Please see http://openweathermap.org/faq#error401 for more info."
	  ));
	}

	@Test(priority = 2)
	public void testCase2() {
		RestAssured.baseURI="http://api.openweathermap.org/data/3.0/stations";
		
		String requestBody1 = 	"{\r\n" + 
				"  \"external_id\": \"DEMO_TEST001\",\r\n" + 
				"  \"name\": \"Interview Station <Random Number>\",\r\n" + 
				"  \"latitude\": 33.33,\r\n" + 
				"  \"longitude\": -111.43,\r\n" + 
				"  \"altitude\": 444\r\n" + 
				"}";
		String requestBody2 = "{\r\n" + 
				"  \"external_id\": \"Interview1 \",\r\n" + 
				"  \"name\": \"Interview Station <Random Number>\",\r\n" + 
				"  \"latitude\": 33.44,\r\n" + 
				"  \"longitude\": -12.44,\r\n" + 
				"  \"altitude\": 444\r\n" + 
				"}";
		
		createNewStation(requestBody1);
		System.out.println("============================================");
		createNewStation(requestBody2);
		
	}

	//http://api.openweathermap.org/data/3.0/stations?APPID=49368760dcba5076522b1337c9f72b9a
	
	@Test(priority = 3, enabled = true)
	public void testCase3() {
		RestAssured.baseURI="http://api.openweathermap.org/data/3.0/stations";
		Response response = RestAssured
				.given()
				.queryParam("APPID", "49368760dcba5076522b1337c9f72b9a")
				.when()
				.get()
				
				;
			  ResponseBody respBody = response.getBody(); 
			  String strBody = respBody.asString(); 
			  Assert.assertTrue(strBody.contains("DEMO_TEST001"));
			  Assert.assertTrue(strBody.contains("Interview1"));
	}

	@Test(priority = 4, enabled = true)
	public void testCase4() {

		RestAssured.baseURI="";
		RestAssured
			.given()
			.baseUri("http://api.openweathermap.org/data/3.0/stations")
			.cookie("APPID", "49368760dcba5076522b1337c9f72b9a")
		.when()
			.delete()
		// THEN
		.then()
			.assertThat()
			.statusCode(201);

		// Verifying booking is deleted
		// Given
		RestAssured
			.given()
			.baseUri("http://api.openweathermap.org/data/3.0/stations")
			// When
		.when()
			.get()
		// Then
		.then()
			.statusCode(404);

	}
	
	@Test(priority = 5, enabled = true)
	public void testCase5() {

		RestAssured
			.given()
			.baseUri("http://api.openweathermap.org/data/3.0/stations")
			.cookie("APPID", "49368760dcba5076522b1337c9f72b9a")
		.when()
			.get()
		// Then
		.then()
			.statusCode(401);

	}
	
	
	
	
	
	public void createNewStation(String requestBody) {
		RestAssured.given()
		.queryParam("APPID", "49368760dcba5076522b1337c9f72b9a")
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(201)
                .statusLine("HTTP/1.1 201 Created")
                .log()
                .all()
                ;

	}

	@AfterTest
	public void tearDown() {
		
	}
}

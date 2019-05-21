package task.one.SWAPI.Planets;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class StarWarsAPI {
	
	String api="https://swapi.co/api/planets/";

	@Test
	public void verifyResponseCode() {
		int code = get(api).getStatusCode();
		System.out.println("Status Code is : "+code);
		Assert.assertEquals(code, 200);
	}
	
	
	@Test
	public void getResponseTime() {
		Long time =get(api).getTime();
		System.out.println("Response Time is: "+time +" seconds");
	}
	
	@Test
	public void getAllPlanets() {
		Response resp = get(api);
		resp.body();
		ResponseBody body = resp.getBody();
		System.out.println("List of all the planets: " +body.asString());
		JsonPath eval = resp.jsonPath();
		String count = eval.getString("count");
		Assert.assertEquals(count, "61", "Correct Count - Test Passed");
	}
		
						
	@Test
	public void getPlanetByID_Positive() {
		RestAssured.baseURI="https://swapi.co/api/planets";
		RequestSpecification httpRequest = RestAssured.given();
		Response respID = httpRequest.get("/2");
		JsonPath eval = respID.jsonPath();
		String planet = eval.getString("name");
		System.out.println("Actual Planet by ID 2 is : "+planet +" Expected: Alderaan");
		Assert.assertEquals(planet, "Alderaan", "Correct Planet - Test Passed");
	}
	
	@Test
	public void getPlanetByID_Negative() {
		RestAssured.baseURI="https://swapi.co/api/planets";
		RequestSpecification httpRequest = RestAssured.given();
		Response respID = httpRequest.get("/3");
		JsonPath eval = respID.jsonPath();
		String planet = eval.getString("name");
		System.out.println("Actual Planet by ID 3 is : "+planet);
		Assert.assertEquals(planet, "Alderaan", "Wrong Planet - Test Passed");
	}

}

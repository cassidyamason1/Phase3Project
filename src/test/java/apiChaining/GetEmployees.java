package apiChaining;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetEmployees {
	
	Response response;
	String BaseURI = "http://18.206.229.7:8088/employees";
	
	@Test
	public void test1() {
		
		response = GetAllMethod();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	
	public Response GetAllMethod() {
		RestAssured.baseURI = "http://18.206.229.7:8088/employees";
		RequestSpecification request = RestAssured.given();
		Response response = request.get();
		
		return response;
		
	}

}

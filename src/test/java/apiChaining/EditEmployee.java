package apiChaining;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EditEmployee {
	
	Response response;
	String BaseURI = "http://18.206.229.7:8088/employees";
	
	@Test
	public void test1() {
		
		response = GetAllMethod();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		response = PostMethod("Sam", "Smith", "2000", "sam@gmail.com");
		Assert.assertEquals(response.getStatusCode(), 201);
		JsonPath jpath = response.jsonPath();
		int id = jpath.get("id");
		System.out.println("id=" +id);
		
		response = PutMethod(id, "Cheese", "Gouda", "10000", "cheese@gmail.com");
		Assert.assertEquals(response.getStatusCode(), 200);
		jpath = response.jsonPath();
		Assert.assertEquals(jpath.get("firstName"), "Cheese");

}


	public Response GetAllMethod() {
		RestAssured.baseURI = "http://18.206.229.7:8088/employees";
		RequestSpecification request = RestAssured.given();
		Response response = request.get();
		
		return response;
		
}

	public Response PostMethod(String firstName, String lastName, String salary, String email) {
		
		RestAssured.baseURI = BaseURI;
		
		JSONObject jobj = new JSONObject();
		jobj.put("firstName", firstName);
		jobj.put("lastName", lastName);
		jobj.put("salary", salary);
		jobj.put("email", email);
		
		RequestSpecification request = RestAssured.given();
		
		Response response =	request.contentType(ContentType.JSON)
									.accept(ContentType.JSON)
									.body(jobj.toString())
									.post();
		
		return response;
	
}
	
	public Response PutMethod(int id, String firstName, String lastName, String salary, String email) {
		
		RestAssured.baseURI = BaseURI;
		
		JSONObject jobj = new JSONObject();
		jobj.put("firstName", firstName);
		jobj.put("lastName", lastName);
		jobj.put("salary", salary);
		jobj.put("email", email);
		
		RequestSpecification request = RestAssured.given();
		
		Response response =	request.contentType(ContentType.JSON)
									.accept(ContentType.JSON)
									.body(jobj.toString())
									.put("/" + id);
		
		return response;
		
	}

}

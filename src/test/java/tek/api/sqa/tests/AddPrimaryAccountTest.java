package tek.api.sqa.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tek.api.sqa.base.APITestConfig;
import tek.api.utility.EndPoints;

public class AddPrimaryAccountTest extends APITestConfig {

	@Test
	public void addPrimaryAccount() {
		String token = getValidToken();

		Map<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("email", "afgboyisr77@yahoo.com");
		requestBody.put("firstName", "Israr");
		requestBody.put("lastName", "Afghan");
		requestBody.put("title", "Mr.");
		requestBody.put("gender", "MALE");
		requestBody.put("maritalStatus", "SINGLE");
		requestBody.put("employmentStatus", "Student");
		requestBody.put("dateOfBirth", "2002-07-09");
		RequestSpecification req = RestAssured.given();
		req.contentType(ContentType.JSON);
		req.body(requestBody);
		req.header("Authorization", "Bearer " + token);
		Response response = req.when().post(EndPoints.ADD_PRIMARY_ACCOUNT.getValue());
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 201);
		String email = response.jsonPath().getString("email");
		Assert.assertEquals(email, "afgboyisr77@yahoo.com");
	}
}

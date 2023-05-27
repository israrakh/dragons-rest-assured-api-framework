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

public class SecurityTest extends APITestConfig {

	@Test
	public void testGeneratedTokenWithValidUsername() {

		Map<String, String> tokenRequestBody = new HashMap<>();
		tokenRequestBody.put("username", "supervisor");
		tokenRequestBody.put("password", "tek_supervisor");
		// Given prepare request
		RequestSpecification request = RestAssured.given().body(tokenRequestBody);
		// Set content type
		request.contentType(ContentType.JSON);
		// when sending request to end point
		Response response = request.when().post(EndPoints.TOKEN_GENERATION.getValue());
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 200);

		String generatedToken = response.jsonPath().getString("token");
		Assert.assertNotNull(generatedToken);
	}

	@Test
	public void testGeneratedTokenWithInvalidUsername() {
		Map<String, String> tokenRequestBody = new HashMap<>();
		tokenRequestBody.put("username", "invalid");
		tokenRequestBody.put("password", "tek_supervisor");

		RequestSpecification request = RestAssured.given().body(tokenRequestBody);
		request.contentType(ContentType.JSON);
		Response response = request.when().post(EndPoints.TOKEN_GENERATION.getValue());
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 400);
		String errorMessage = response.jsonPath().getString("errorMessage");
		Assert.assertEquals(errorMessage, "User not found");
	}
}

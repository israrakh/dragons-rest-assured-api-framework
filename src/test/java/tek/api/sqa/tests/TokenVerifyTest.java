package tek.api.sqa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tek.api.sqa.base.APITestConfig;
import tek.api.utility.EndPoints;

public class TokenVerifyTest extends APITestConfig {

	@Test
	public void tokenVerifyTest() {

		String token = getValidToken();
		RequestSpecification req = RestAssured.given();
		req.queryParam("token", token);
		req.queryParam("username", "supervisor");
		Response response = req.when().get(EndPoints.TOKEN_VERIFY.getValue());
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 200);

	}
	
	@Test
	public void verifyTestWithInvalidToken() {
		
		RequestSpecification req = RestAssured.given();
		req.queryParam("token", "invalid");
		req.queryParam("username", "supervisor");
		Response response = req.when().get(EndPoints.TOKEN_VERIFY.getValue());
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 400);
		String errorMessage = response.jsonPath().get("errorMessage");
		Assert.assertEquals(errorMessage, "Token Expired or Invalid Token");
		
	}

}

package tek.api.sqa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tek.api.sqa.base.APITestConfig;
import tek.api.utility.EndPoints;

public class UserProfileTest extends APITestConfig {

	@Test
	public void userProfileTest() {
		String token = getValidToken();
		RequestSpecification req = RestAssured.given();
		req.queryParam("Authorization", "Bearer " + token);
		Response response = req.when().get(EndPoints.User_Profile.getValue());
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 200);

	}

}

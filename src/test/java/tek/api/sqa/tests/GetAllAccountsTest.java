package tek.api.sqa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tek.api.sqa.base.APITestConfig;
import tek.api.utility.EndPoints;

public class GetAllAccountsTest extends APITestConfig {

	@Test
	public void getAllAccounts() {

		String token = getValidToken();
		RequestSpecification req = RestAssured.given();
		req.header("Authorization", "Bearer " + token);
		Response response = req.when().get(EndPoints.GET_ALL_ACCOUNTS.getValue());
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
}

package tek.api.sqa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tek.api.sqa.base.APITestConfig;
import tek.api.utility.EndPoints;

public class GetAccountTest extends APITestConfig {

	@Test
	public void getAccountWithAnID() {

		String token = getValidToken();
		RequestSpecification req = RestAssured.given();
		req.header("Authorization", "Bearer " + token);
		req.queryParam("primaryPersonId", 7112);
		Response response = req.when().get(EndPoints.Get_Account.getValue());
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 200);
		int primaryPersonId = response.jsonPath().get("primaryPerson.id");
		Assert.assertEquals(primaryPersonId, 7112);
		
	}
}

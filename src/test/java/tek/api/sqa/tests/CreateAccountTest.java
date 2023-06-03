package tek.api.sqa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tek.api.model.PrimaryAccount;
import tek.api.sqa.base.APITestConfig;
import tek.api.utility.DataGenerator;
import tek.api.utility.EndPoints;

public class CreateAccountTest extends APITestConfig {
	
	private DataGenerator data = new DataGenerator();

	@Test
	public void createAccountValidTest() {
		String validToken = getValidToken();
		RequestSpecification req = RestAssured.given();
		req.header("Authorization", "Bearer " + validToken);
		req.contentType(ContentType.JSON);

		PrimaryAccount requestBody = new PrimaryAccount();
		String firstName = data.getFirstName();
		String lastName = data.getLastName();
		requestBody.setEmail(data.getEmail(firstName, lastName, "@yahoo.com"));
		requestBody.setFirstName(firstName);
		requestBody.setLastName(lastName);
		requestBody.setTitle("Mr.");
		requestBody.setGender("MALE");
		requestBody.setMaritalStatus("SINGLE");
		requestBody.setEmploymentStatus(data.getJobTitle());
		requestBody.setDateOfBirth(data.getDateOfBirth());
		req.body(requestBody);
		Response response = req.when().post(EndPoints.ADD_PRIMARY_ACCOUNT.getValue());
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 201);

//		String actualEmail = response.jsonPath().getString("email");
		PrimaryAccount responseBody = response.as(PrimaryAccount.class);
		Assert.assertEquals(responseBody.getEmail(), requestBody.getEmail());
	}
}

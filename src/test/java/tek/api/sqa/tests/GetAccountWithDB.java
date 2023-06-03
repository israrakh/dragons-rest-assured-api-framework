package tek.api.sqa.tests;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tek.api.model.PrimaryAccount;
import tek.api.sqa.base.DatabaseConfig;
import tek.api.utility.EndPoints;

public class GetAccountWithDB extends DatabaseConfig {
	
	@Test
	public void getAccountTestWithDBQuery() throws SQLException {
		
		String query = "select id from primary_person order by id desc limit 1";
		ResultSet resultSet = runQuery(query);
		long queryResultId = 0;
		while(resultSet.next()) {
			queryResultId = resultSet.getLong("id");
		}
		
		String validToken = getValidToken();
		RequestSpecification req = RestAssured.given();
		req.header("Authorization", "Bearer " + validToken);
		req.queryParam("primaryPersonId", queryResultId);
		Response response = req.when().get(EndPoints.GET_ACCOUNT.getValue());
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 200);
//		int actualPrimaryPersonID= response.jsonPath().get("primaryPerson.id");
//		Assert.assertEquals(actualPrimaryPersonID, queryResultId);
		
		String secondQuery = "select * from primary_person where id = " + queryResultId;
		ResultSet secondQueryResult = runQuery(secondQuery);
		
		// get response from json path as an Object
		PrimaryAccount responseBody = response.jsonPath().getObject("primaryPerson", PrimaryAccount.class);
		if (secondQueryResult.next()) {
			String expectedEmail = secondQueryResult.getString("email");
			String expectedFirstName = secondQueryResult.getString("first_name");
			Assert.assertEquals(responseBody.getEmail(), expectedEmail);
			Assert.assertEquals(responseBody.getFirstName(), expectedFirstName);
		}else {
			Assert.fail("Test failed, query did not return result for id " + queryResultId);
		}
	}
}

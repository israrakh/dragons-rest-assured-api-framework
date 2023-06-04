package tek.api.sqa.tests;

import static org.testng.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.service.ExtentTestManager;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tek.api.model.PrimaryAccount;
import tek.api.sqa.base.DatabaseConfig;
import static tek.api.utility.DateUtility.toDateString;
import tek.api.utility.EndPoints;

public class CreateAccountWithDBVerification extends DatabaseConfig {

	@Test
	public void createAccountTestDb() {

		RequestSpecification request = getRequestWithValidToken();
		PrimaryAccount requestBody = createAccountData();
		request.contentType(ContentType.JSON);
		request.body(requestBody);
		Response response = request.when().post(EndPoints.ADD_PRIMARY_ACCOUNT.getValue());
		response.prettyPrint();
		ExtentTestManager.getTest().info(response.asPrettyString());
		asserter.assertResponseStatus(response, 201);
		PrimaryAccount responseBody = response.as(PrimaryAccount.class);
		// Validating with database:
		StringBuilder stb = new StringBuilder();
		stb.append("select * from primary_person where id = ");
		stb.append(responseBody.getId());
		ResultSet createdAccountResultSet = runQuery(stb.toString());
		assertCreatedAccount(responseBody, createdAccountResultSet);
	}

	public void assertCreatedAccount(PrimaryAccount responseBody, ResultSet queryResult) {
		try {
			if (queryResult.next()) {
				asserter.isEqual((int) responseBody.getId(), queryResult.getInt("id"));
				asserter.isEqual(responseBody.getEmail(), queryResult.getString("email"));
				asserter.isEqual(responseBody.getFirstName(), queryResult.getString("first_name"));
				asserter.isEqual(responseBody.getLastName(), queryResult.getString("last_name"));
				asserter.isEqual(responseBody.getEmploymentStatus(), queryResult.getString("employment_status"));
				asserter.isEqual(responseBody.getMaritalStatus(), queryResult.getString("marital_status"));
				asserter.isEqual(responseBody.getGender(), queryResult.getString("gender"));
				asserter.isEqual(responseBody.getTitle(), queryResult.getString("title"));
				String actualDOB = toDateString(responseBody.getDateOfBirth());
				String expectedDOB = toDateString(queryResult.getDate("date_of_birth"));
				System.out.println(actualDOB);
				System.out.println(expectedDOB);
				asserter.isEqual(actualDOB, expectedDOB);
			} else {
				asserter.fail("Result is empty");
			}
		} catch (SQLException e) {
			asserter.fail("Failed due to SQL Excpetion " + e.getMessage());
		}
	}
}

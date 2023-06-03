package tek.api.sqa.base;


import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tek.api.model.PrimaryAccount;
import tek.api.utility.DataGenerator;
import tek.api.utility.EndPoints;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;


@Listeners({ ExtentITestListenerAdapter.class })
public class APITestConfig extends BaseConfig {
	
	private DataGenerator dataGenerator = new DataGenerator();
	
	@BeforeMethod
	public void setupApiTest() {
		System.out.println("Setting up Test");
		RestAssured.baseURI = getBaseUrl();
	}

	public String getValidToken() {
		Map<String, String> tokenRequestBody = new HashMap<>();
		tokenRequestBody.put("username", "supervisor");
		tokenRequestBody.put("password", "tek_supervisor");
		RequestSpecification request = RestAssured.given().body(tokenRequestBody);
		request.contentType(ContentType.JSON);
		Response response = request.when().post(EndPoints.TOKEN_GENERATION.getValue());
		String token = response.jsonPath().get("token");
		return token;
	}
	
	public PrimaryAccount createAccountData() {
		PrimaryAccount account = new PrimaryAccount();
		String firstName = dataGenerator.getFirstName();
		String lastName = dataGenerator.getLastName();
		account.setEmail(dataGenerator.getEmail(firstName, lastName, "@tekschool.us"));
		account.setFirstName(firstName);
		account.setLastName(lastName);
		account.setTitle("Mr.");
		account.setMaritalStatus("SINGLE");
		account.setEmploymentStatus(dataGenerator.getJobTitle());
		account.setDateOfBirth(dataGenerator.getDateOfBirth());
		return account;
		
	}
}

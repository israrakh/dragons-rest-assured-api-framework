package tek.api.sqa.base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tek.api.utility.EndPoints;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;

@Listeners({ ExtentITestListenerAdapter.class })
public class APITestConfig extends BaseConfig {

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
}

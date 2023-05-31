package tek.api.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.javafaker.Faker;

public class DataGenerator {

	private Faker faker;

	public DataGenerator() {
		this.faker = new Faker();
	}

	public String getFirstName() {
		return faker.name().firstName();
	}

	public String getLastName() {
		return faker.name().lastName();
	}

	public String getEmail() {
		String firstName = getFirstName();
		String lastName = getLastName();
		double randomNum = (int) (Math.random() * 10);
		return firstName + "." + lastName + randomNum + "@gmail.com";
	}

	public String getEmail(String firstName, String lastName, String provider) {
		double randomNumber = (int) (Math.random() * 10);
		return firstName + "." + lastName + randomNumber + provider;
	}

	public String getJobTitle() {
		return faker.job().position();
	}

	public String getDateOfBirth() {
		Date date = faker.date().birthday();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		return formatter.format(date);
	}
}

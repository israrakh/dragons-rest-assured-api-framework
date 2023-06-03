package tek.api.utility;

import java.text.DateFormat;
import java.text.ParseException;
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
		int randomNum = (int) (Math.random() * 100);
		return firstName + "." + lastName + randomNum + "@gmail.com";
	}

	public String getEmail(String firstName, String lastName, String provider) {
		int randomNumber = (int) (Math.random() * 100);
		return firstName + "." + lastName + randomNumber + provider;
	}

	public String getJobTitle() {
		return faker.job().position();
	}

	public Date getDateOfBirth() {
		Date date = faker.date().birthday();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = formatter.format(date);
		try {
			return formatter.parse(formattedDate);
		} catch (ParseException e) {
			throw new RuntimeException("Date parse exception");
		}
	}
}

package tek.api.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestingDatabaseConnection {

	public static void main(String[] args) {

		String url = "jdbc:mysql://tek-database-server.mysql.database.azure.com:3306/tek_insurance_app?useSSL=true&requireSSL=false";
//		String port = "3306";
//		String databaseName = "tek_insurance_app";
		String username = "tek_student_user";
		String password = "MAY_2022";

		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("select * from primary_person_car order by primary_person_id desc limit 20;");

			while (resultSet.next()) {
				long primaryPersonId = resultSet.getLong("id");
				String make = resultSet.getString("make");
				String model = resultSet.getString("model");

				System.out.println(primaryPersonId);
				System.out.println(make);
				System.out.println(model);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

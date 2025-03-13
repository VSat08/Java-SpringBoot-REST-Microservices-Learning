package library_mangement_system;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
	private Connection connection;

	public DBConnector() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/library";
			String uname = "<USERNAME>";
			String pwd = "<PASSWORD>";
			connection = DriverManager.getConnection(url, uname, pwd);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void closeConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

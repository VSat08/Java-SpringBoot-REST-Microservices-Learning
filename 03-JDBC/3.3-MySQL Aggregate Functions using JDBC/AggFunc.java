
/*
This code is a template for simple demonstration of Aggregate Functions  using JDBC.

- !!IMPORTANT Make sure to  configure JDBC SQL Driver Connector before  running  this code. 


- Copy this code to your actual created project in your IDE.
- Also before running, make sure you are using correct credentials for mysql connection .
- Before running, check that mysql server is running

*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AggFunc {

	public static void main(String[] args) throws Exception {
		// ------------- Step 1: Loading Drivers -------------
		Class.forName("com.mysql.cj.jdbc.Driver");

		// ------------- Step 2: Establishing Connections-------------
		String url = "jdbc:mysql://localhost:3306/<DATABASE_NAME>";
		String uname = "<USERNAME>";
		String pwd = "<PASSWORD>";

		Connection con = DriverManager.getConnection(url, uname, pwd);

		// ------------- Step 3: Checking Connections-------------
		if (con != null) {
			System.out.println("------------- Connection Established -------------");
		} else {
			System.out.println("------------- Connection Failure -------------");
		}

		// ------------- Step 4:Creating Statements -------------
		Statement st = con.createStatement();

		// ------------- Step 5:Aggregate Functions-------------
		// Note:Table must be created first and only once and table should be available
		// prior to CRUD Operations.

		String query = "SELECT COUNT(*), MIN(CGPA), MAX(CGPA), AVG(CGPA), SUM(CGPA) FROM STUDENT";
		System.out.println("Student'sCount\tMIN(CGPA)\tMAX(CGPA)\tAVG(CGPA)\tSUM(CGPA)");
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			System.out.println(rs.getInt(1) + "\t\t" + rs.getDouble(2) + "\t\t" + rs.getDouble(3) + "\t\t"
					+ rs.getDouble(4) + "\t" + rs.getDouble(5));
		}

		System.out.println("------------- Connection Closed -------------");
		con.close();
	}

}

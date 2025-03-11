
/*
This code is a template for simple demonstration of CRUD Operations using JDBC.

- !!IMPORTANT Make sure to  configure JDBC SQL Driver Connector before  running  this code. 


- Copy this code to your actual created project in your IDE.
- Also before running, make sure you are using correct credentials for mysql connection .
- Before running, check that mysql server is running

*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CRUDOps {
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

		// ------------- Step 5:Creating Table(only be run once) -------------
		String cq = "CREATE TABLE STUDENT(sID INT PRIMARY KEY, sName VARCHAR(20), CGPA FLOAT)";
		int c = st.executeUpdate(cq);
		if (c >= 0) {
			System.out.println("Table Created!");
		} else {
			System.out.println("Table Creation Failure");
		}

		// ------------- Step 6:CURD Operations -------------
		// Note:Table must be created first and only once and table should be available
		// prior to CRUD Operations.

		// ------------- INSERT -------------
		String iq = "INSERT INTO STUDENT VALUES(123, 'Sara', 9.5), (212, 'Bob', 7.8), (111, 'Steve', 6.5), (101, 'Kris', 8.7), (321, 'Samuel', 7.4),(411, 'Eon', 5)";
		int x = st.executeUpdate(iq);
		if (x > 0) {
			System.out.println(x + " Record(s) Inserted");
		}

		// ------------- UPDATE -------------
		String uq = "UPDATE STUDENT SET sName = 'Jane' WHERE sID = 212";
		int y = st.executeUpdate(uq);

		if (y > 0) {
			System.out.println(y + " Record(s) Updated");
		}

		// ------------- DELETE -------------
		String dq = "DELETE FROM STUDENT WHERE CGPA<7";
		int z = st.executeUpdate(dq);

		if (z > 0) {
			System.out.println(z + " Record(s) Deleted");
		}

		// ------------- READ -------------
		ResultSet rs = st.executeQuery("SELECT * FROM STUDENT");
		System.out.println("-------------------------------- Student Records --------------------------------");
		System.out.println("ID" + "\t" + "Name" + "\t" + "CGPA");

		while (rs.next()) {
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getFloat(3));
		}
		con.close();
	}
}

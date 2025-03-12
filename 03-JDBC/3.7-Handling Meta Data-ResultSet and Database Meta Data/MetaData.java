import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class MetaData {

	public static void main(String[] args) throws Exception {
		// ------------- Step 1: Loading Drivers -------------
		Class.forName("com.mysql.cj.jdbc.Driver");

		// ------------- Step 2: Establishing Connections-------------
		String url = "jdbc:mysql://localhost:3306/<DATABASE_NAME>";
		String uname = "<USERNAME>";
		String pwd = "<PASSWORD>";
		Connection cn = DriverManager.getConnection(url, uname, pwd);

		// ------------- Step 3: Checking Connections-------------
		if (cn != null) {
			System.out.println("#Connection Established");
		} else {
			System.out.println("#Connection Failure");
		}

		// ------------- Database MetaData -------------
		System.out.println("------------- Database MetaData -------------");
		DatabaseMetaData dbmd = cn.getMetaData();
		System.out.println("Driver Name: " + dbmd.getDriverName());
		System.out.println("Connection Details: " + dbmd.getConnection());
		System.out.println("Database: " + dbmd.getDatabaseProductName());
		System.out.println("Version: " + dbmd.getDatabaseProductVersion());
		System.out.println("User: " + dbmd.getUserName());
		System.out.println("Tables in " + "<DATABASE_NAME>" + ":");
		String[] tables = { "TABLE" };
		ResultSet rsl = dbmd.getTables(null, "<DATABASE_NAME>", null, tables); // Schema filter
		while (rsl.next()) {
			System.out.println(rsl.getString(3));
		}

		// ------------- ResultSet MetaData -------------
		System.out.println("------------- ResultSet MetaData -------------");
		Statement st = cn.createStatement();
		ResultSet rSet = st.executeQuery("SELECT * FROM STUDENT");
		ResultSetMetaData rsmd = rSet.getMetaData();
		System.out.println("No. of Columns: " + rsmd.getColumnCount());
		System.out.println("Column Details:");
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			System.out.print(rsmd.getColumnName(i) + " (" + rsmd.getColumnTypeName(i) + ")\t");
		}
		System.out.println("\nData:");
		while (rSet.next()) {
			System.out.println(rSet.getInt(1) + "\t" + rSet.getString(2) + "\t" + rSet.getDouble(3));
		}

		// ------------- Closing Connection -------------
		cn.close();
	}
}
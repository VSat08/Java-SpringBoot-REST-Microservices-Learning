
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class CallableStmDemo {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String urlString = "jdbc:mysql://localhost:3306/<DATABASE_NAME>";
		String uname = "<USERNAME>";
		String pwd = "<PASSWORD>";
		Connection cn = DriverManager.getConnection(urlString, uname, pwd);
		if (cn != null) {
			System.out.println("--------------- Connection Successfull ---------------");
		} else {
			System.out.println("--------------- Connection Failure---------------");
		}

		// Calling Stored Procedure (commented out example)
		// String procQuery = "{call first_proc(?,?)}";
		// CallableStatement cst = cn.prepareCall(procQuery);
		// cst.setInt(1, 25);
		// cst.registerOutParameter(2, Types.INTEGER);
		// cst.execute();
		// int result = cst.getInt(2);
		// System.out.println("Square of 25 is: " + result);

		// Calling Stored Function
		String funcQuery = "{? = call add_ab(?,?)}";
		CallableStatement cst = cn.prepareCall(funcQuery);
		cst.setInt(2, 123);
		cst.setInt(3, 321);
		cst.registerOutParameter(1, Types.INTEGER);
		cst.execute();
		int result = cst.getInt(1);
		System.out.println("Sum: " + result);

		cn.close();

	}
}
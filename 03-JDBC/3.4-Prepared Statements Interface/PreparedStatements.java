import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class PreparedStatements {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/<DATABASE_NAME>";
		String uname = "<USERNAME>";
		String pwd = "<PASSWORD>";

		Connection cn = DriverManager.getConnection(url, uname, pwd);
		if (cn != null) {
			System.out.println("--------------- Connection Successfull ---------------");
		} else {
			System.out.println("--------------- Connection Failure---------------");
		}

		// ---------------INSERT USING Prepared Statements ---------------

		String query = "INSERT INTO STUDENT VALUES (?,?,?)";
		PreparedStatement pst = cn.prepareStatement(query);
		// assuming data and hard filling
		// pst.setInt(1, 200);
		// pst.setString(2, "Simon");
		// pst.setDouble(3, 8.8);

		System.out.println("## Enter Student Details");
		System.out.print("\nID: ");
		int sId = in.nextInt();
		System.out.print("\nName:");
		String sName = in.next();
		System.out.print("\nCGPA: ");
		double cgpa = in.nextDouble();

		// User entered data
		pst.setInt(1, sId);
		pst.setString(2, sName);
		pst.setDouble(3, cgpa);

		int x = pst.executeUpdate();
		if (x > 0) {
			System.out.println(x + " Record(s) Inserted.");
		}

		// ---------------UPDATE USING Prepared Statements ---------------

		pst = cn.prepareStatement("UPDATE STUDENT SET sName = ?  WHERE sId = ?");
		pst.setString(1, "Carl");
		pst.setInt(2, 101);
		int y = pst.executeUpdate();
		if (y > 0) {
			System.out.println(y + " Record(s) Updated.");
		}

		// ---------------READ USING Prepared Statements ---------------
		ResultSet rs = pst.executeQuery("SELECT * FROM STUDENT");
		while (rs.next()) {
			System.out.println(rs.getInt("sID") + "\t" + rs.getString("sName") + "\t" + rs.getDouble(3));
		}

		cn.close();
		in.close();
	}

}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class TransactionManagement {

	public static void main(String[] args) throws Exception {

		Scanner in = new Scanner(System.in);

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
		// ------------- Step 4: Setting AUTOCOMMIT = FALSE-------------
		cn.setAutoCommit(false);
		System.out.println("Enter Source Account: ");
		int src = in.nextInt();
		System.out.println("Enter Destination Account: ");
		int dst = in.nextInt();
		System.out.println("Enter Transfer Amount: ");
		double amount = in.nextDouble();

		// ------------- Step 5: Atomic TCL Transfer -------------
		Statement st = cn.createStatement();
		String withdraw = "UPDATE ACCOUNT SET balance = balance-" + amount + " WHERE account_number = " + src;
		// batch processing
		st.addBatch(withdraw);
		String deposite = "UPDATE ACCOUNT SET balance = balance+" + amount + " WHERE account_number = " + dst;
		st.addBatch(deposite);

		// execute the batch
		int[] res = st.executeBatch();

		// logic for commit and Rollback
		boolean flag = false;

		for (int i = 0; i < res.length; i++) {
			if (res[i] == 0) {
				// query failed and need to ROLLBACK
				flag = true;
				break;
			}

		}

		if (flag == true) {
			cn.rollback();
			System.out.println("#Transaction Failed --Rolling Back");
		} else {
			cn.commit();
			System.out.println("#Transaction Successfull --Commitng Changes");
		}

		// ------------- Step 6: Displaying Final Table-------------
		ResultSet rs = st.executeQuery("SELECT * FROM ACCOUNT");
		while (rs.next()) {
			System.out.println(rs.getInt(1) + "\t" + rs.getDouble(2) + "\t" + rs.getString(3));
		}

		// ------------- Final Step: Closing Connections-------------
		cn.close();
		in.close();

	}

}

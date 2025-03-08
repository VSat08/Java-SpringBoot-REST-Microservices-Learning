import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest {
    public static void main(String[] args) throws Exception {
        //Step 1: Loading Drivers
        Class.forName("com.mysql.cj.jdbc.Driver");

        //Step 2: Establishing Connection
        String url = "jdbc:mysql://localhost:3306/DATABASENAME";
        String uname = "USERNAME";
        String pwd = "PASSWORD";
        Connection con = DriverManager.getConnection(url,uname,pwd);

        //Step 3: Checking Connection
        if(con!=null){
            System.out.println("Connection Established!");
        }
        else{
            System.out.println("Connection Failed");
        }

        //Step 4: Creating Statements
        Statement st = con.createStatement();

        //Execute Queries
        String query = "select * from TABLENAME";
        ResultSet rs = st.executeQuery(query);

        //Step 5: Process Results
        while(rs.next()){
            System.out.println(rs.getInt(1) + " " + rs.getDouble(2)+ " " + rs.getString(3)); //as per the table structure
        }

        //Step 6: Closing Connection
        con.close();
    }
}

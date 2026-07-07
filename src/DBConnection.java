import java.sql.Connection;    //represents database connection
import java.sql.DriverManager;   //establishes connection

public class DBConnection {

    public static Connection connect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");    //loads jdbc driver into the memory

            Connection con = DriverManager.getConnection(     //establishing connection
                    "jdbc:oracle:thin:@localhost:1521:XE", //connection url ; 1521 port number
                    "apurv1234",   // user
                    "apurv1234"  // pass
            );

            return con;      //connection used in other files

        } catch (Exception e) {       //error if connection fails
            System.out.println("Error: " + e);
            return null;
        }
    }
}
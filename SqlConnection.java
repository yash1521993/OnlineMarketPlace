// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

//importing required packages to perform database operations
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Properties;
import java.sql.Connection;

/*
	SqlConnection class contains code realted to mysql connection details
*/
public class SqlConnection {
	//variable for Connection
	private Connection conn;
	//static variables initialized to respective url and connection details
	private static final String dbDriver = "com.mysql.jdbc.Driver";
    private static final String connString = "jdbc:mysql://localhost:3306/yashkuru_db";
    private static final String connId = "yashkuru";
    private static final String connPwd = "yashkuru";

    //connectMySql() returns a connection instance
    public Connection connectMySql() {
    	//create a new connection only if there isn't any existing one
        if (conn == null) {
        	//exception handling block
            try {
            	//lookup for sql driver
                Class.forName(dbDriver);
                conn = DriverManager.getConnection(connString, connId, connPwd);
                System.out.println("Connecting to Market App Database........");
            } 
            catch (ClassNotFoundException | SQLException e) {
                System.out.println("Online Market App SQL Exception: " +e.getMessage());
            }
        }
        return conn;
    }

    //closeConnection() method closes the current sql connection
    public void closeConnection() {
    	//if there is an existing connection, close this
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } 
            catch (SQLException e) {
                System.out.println("Online Market App SQL Exception: " +e.getMessage());
            }
        }
    }

}
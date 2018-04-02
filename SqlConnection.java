// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Properties;
import java.sql.Connection;

public class SqlConnection {

	private Properties property;
	private Connection conn;

	private static final String dbDriver = "com.mysql.jdbc.Driver";
    private static final String connString = "jdbc:mysql://localhost:3306/yashkuru_db";
    private static final String connId = "";
    private static final String connPwd = "";

    private Properties setProperties() {
        if (property == null) {
            property = new Properties();
            property.setProperty("yashkuru", connId);
            property.setProperty("yashkuru", connPwd);
        }
        return property;
    }

    //
    public Connection connectMySql() {
        if (conn == null) {
            try {
                Class.forName(dbDriver);
                conn = DriverManager.getConnection(connString, setProperties());
            } 
            catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    //
    public void closeConnection() {
        if (conn != null) {
        	
            try {
                conn.close();
                conn = null;
            } 
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
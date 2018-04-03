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
    private static final String connId = "yashkuru";
    private static final String connPwd = "yashkuru";

    //
    public Connection connectMySql() {
        if (conn == null) {
            try {
                Class.forName(dbDriver);
                conn = DriverManager.getConnection(connString, connId, connPwd);
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
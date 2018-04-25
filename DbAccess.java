// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.rmi.RemoteException;

public class DbAccess{

	//creating  a new instance for mysql connection
	private SqlConnection connectSql=new SqlConnection();
	private Connection remoteConn=connectSql.connectMySql();
	private PreparedStatement prepStat;
	private Statement statement;
	private ResultSet rsltSet,rsltSet1;
	private int custId=0;
	public static String userId="";

	//this method checks for a valid customer or user
	public ResultSet validateLogin(String inputId,String inputPwd,String loginType) throws RemoteException{
		
		//admin validation
		if(loginType.equalsIgnoreCase("Admin")){
			try{
				prepStat=remoteConn.prepareStatement("Select * from tbl_admin where username=? and password=?");

				prepStat.setString(1,inputId);
				prepStat.setString(2,inputPwd);
				rsltSet=prepStat.executeQuery();
			}
			catch (SQLException e) {
				System.out.println("Online Market App - Login Exception: " +e.getMessage());
			}

		}
		
		//customer validation
		if(loginType.equalsIgnoreCase("Customer")){
			try{
				prepStat=remoteConn.prepareStatement("Select * from tbl_customers where username=? and password=?");

				prepStat.setString(1,inputId);
				prepStat.setString(2,inputPwd);
				rsltSet=prepStat.executeQuery();
			}
			catch (SQLException e) {
				System.out.println("Online Market App - Login Exception: " +e.getMessage());
			}
		}
		return rsltSet;
	}

	
	//browseItems allows a customer to browse over the app
	@SuppressWarnings("unchecked")
	public ResultSet browseItems(){
		System.out.println("======Your can Browse Market App to shop======");
		//exception handling block
		try{
			//retrieves all the items from db
			prepStat = remoteConn.prepareStatement("Select * from tbl_items");
			//browsedItems stores the above executed query result
			ResultSet browsedItems=prepStat.executeQuery(); 
			
		}
		catch (SQLException e) {
			System.out.println("Online Market App Exception- Browse Items: " +e.getMessage());
		}

		return browsedItems;
	}



}
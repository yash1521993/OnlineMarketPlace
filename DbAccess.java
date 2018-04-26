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
import java.util.ArrayList;

public class DbAccess{

	//creating  a new instance for mysql connection
	private SqlConnection connectSql=new SqlConnection();
	private Connection remoteConn=connectSql.connectMySql();
	private PreparedStatement prepStat;
	private Statement statement;
	private ResultSet browsedItems,cartItems,rsltSet,rsltSet1;
	private int custId=0;
	public static String userId="";
	private String browsedItemData="",cartData="",retrievedId="",retrievedUId="",removeCustStatus="";
	private String registerStatus="",creationStatus="",updateStatus="",removeItemStatus="",customerData="";
	private ArrayList browsedList = new ArrayList();
	private ArrayList cartItemsData = new ArrayList();
	private ArrayList customerDataList = new ArrayList();

	//this method checks for a valid customer or user
	public ResultSet validateLogin(String inputId,String inputPwd,String loginType) throws RemoteException{
		
		//admin validation
		if(loginType.equalsIgnoreCase("Admin")){
			try{
				userId=inputId;
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
				userId=inputId;
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
			browsedItems=prepStat.executeQuery(); 
			
		}
		catch (SQLException e) {
			System.out.println("Online Market App Exception- Browse Items: " +e.getMessage());
		}

		return browsedItems;
	}


	//view cart allows a customer to browse over the app
	@SuppressWarnings("unchecked")
	public ResultSet viewCart(){
		
		System.out.println("======Accessed cart view method======");
		//exception handling block
		try{
			//retrieves all the items from db
			prepStat = remoteConn.prepareStatement("Select * from tbl_itemcart join tbl_cart on tbl_cart.cart_id=tbl_itemcart.cart_id join tbl_customers on tbl_customers.customer_id=tbl_cart.customer_id where tbl_customers.username=?");
			prepStat.setString(1,userId);
			//cartItems stores the above executed query result
			cartItems=prepStat.executeQuery(); 
			
		}
		catch (SQLException e) {
			System.out.println("Online Market App Exception- Browse Items: " +e.getMessage());
		}

		return cartItems;
	}

}
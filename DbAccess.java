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
	private ResultSet customerList;
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
	public ResultSet viewCart(){
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

	public ResultSet getCartId(){
		//exception handling block
		try{
			//retrieves all the items from db
			prepStat=remoteConn.prepareStatement("select cart_id from tbl_cart join tbl_customers on tbl_customers.customer_id=tbl_cart.customer_id where username=?");
			prepStat.setString(1,userId);
			rsltSet=prepStat.executeQuery();
			
		}
		catch (SQLException e) {
			System.out.println("Online Market App Exception- Get Cart Id: " +e.getMessage());
		}

		return rsltSet;
	}

	//view customers allows a admin to browse over the app
	public ResultSet viewCustomers(){

		//exception handling block
		try{
			//retrieves all the customers from db
			prepStat = remoteConn.prepareStatement("Select customer_id,username from tbl_customers");

			//browsedItems stores the above executed query result
			customerList=prepStat.executeQuery(); 
		}
		catch (SQLException e) {
			System.out.println("Online Market App -Customer List Exception: " +e.getMessage());
		}
		
		return customerList;
	}

	//getCustomer method retrieves a unique customer from database
	public ResultSet getCustomer(int customerId){
		
		try{
			//retrieve admin input customer id if exists
			statement = remoteConn.createStatement();
			rsltSet = statement.executeQuery("Select * from tbl_customers where customer_id="+customerId);

		}
		catch (SQLException e) {
			System.out.println("Online Market App - Get Customer Exception: " +e.getMessage());
		}
		return rsltSet;
	}

	//getCustomer method retrieves a unique customer from database
	public ResultSet getCustomerByUserName(String userName){
		
		try{
			//retrieve admin input username if exists
			prepStat=remoteConn.prepareStatement("Select * from tbl_customers where username=?");
			prepStat.setString(1,userName);
			rsltSet=prepStat.executeQuery();

		}
		catch (SQLException e) {
			System.out.println("Online Market App - Get Customer By Username Exception: " +e.getMessage());
		}
		return rsltSet;
	}

	public ResultSet getAdminByUserName(String userName){
		
		try{
			//retrieve admin input username if exists
			prepStat=remoteConn.prepareStatement("Select username from tbl_admin where username=?");
			prepStat.setString(1,userName);
			rsltSet=prepStat.executeQuery();

		}
		catch (SQLException e) {
			System.out.println("Online Market App - Get Admin By Username Exception: " +e.getMessage());
		}
		return rsltSet;
	}

	public void insertAdmin(String firstName,String lastName, String userName, String password){
		
		try{
			prepStat = remoteConn.prepareStatement("Insert into tbl_admin(first_name,last_name,username,password) values(?,?,?,?)");
			//set positional params
			prepStat.setString(1,firstName);
			prepStat.setString(2,lastName);
			prepStat.setString(3,userName);
			prepStat.setString(4,password);

			//executes the insert statement with above params
			prepStat.executeUpdate();

		}
		catch (SQLException e) {
			System.out.println("Online Market App - Add Admin Exception: " +e.getMessage());
		}
	}

	public void insertCustomer(String firstName,String lastName, String userName, String password){
		
		try{
			prepStat = remoteConn.prepareStatement("Insert into tbl_customers(first_name,last_name,username,password) values(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			//set positional params
			prepStat.setString(1,firstName);
			prepStat.setString(2,lastName);
			prepStat.setString(3,userName);
			prepStat.setString(4,password);

			//executes the insert statement with above params
			prepStat.executeUpdate();

			//retrieves last inserted customer id
			rsltSet=prepStat.getGeneratedKeys();
			if(rsltSet.next()){
				custId=rsltSet.getInt(1);
			}

			//creates cart for each newly registered customer
			prepStat=remoteConn.prepareStatement("Insert into tbl_cart(customer_id) values(?)");
			prepStat.setInt(1,custId);
			prepStat.executeUpdate();

		}
		catch (SQLException e) {
			System.out.println("Online Market App - Add Customer Exception: " +e.getMessage());
		}
	}


	public void deleteCustomer(int customerId){
		
		try{
			prepStat=remoteConn.prepareStatement("Delete from tbl_customers where customer_id="+customerId);
			prepStat.executeUpdate();	

		}
		catch (SQLException e) {
			System.out.println("Online Market App - Remove Customer Exception: " +e.getMessage());
		}
		
	}

	public ResultSet getItem(int itemId){
		
		try{
			//retrieve admin input item id if exists
			statement = remoteConn.createStatement();
			rsltSet = statement.executeQuery("Select * from tbl_items where item_id="+itemId);

		}
		catch (SQLException e) {
			System.out.println("Online Market App - Get Item Exception: " +e.getMessage());
		}
		return rsltSet;
	}

	public void removeItem(int itemId){
		
		try{
			prepStat=remoteConn.prepareStatement("Delete from tbl_items where item_id="+itemId);
			prepStat.executeUpdate();	

		}
		catch (SQLException e) {
			System.out.println("Online Market App - Remove Item Exception: " +e.getMessage());
		}
		
	}

	public void updateItemPrice(int itemId,String attributeValue){
		try{
			prepStat=remoteConn.prepareStatement("Update tbl_items set price=? where item_id=?");
			prepStat.setString(1,attributeValue);
			prepStat.setInt(2,itemId);
			prepStat.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("Online Market App - Update Item Exception: " +e.getMessage());
		}
	}

	public void updateItemQuantity(int itemId,String attributeValue){
		try{
			prepStat=remoteConn.prepareStatement("Update tbl_items set quantity=? where item_id=?");
			prepStat.setInt(1,Integer.parseInt(attributeValue));
			prepStat.setInt(2,itemId);
			prepStat.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("Online Market App - Update Item Exception: " +e.getMessage());
		}
	}

	public void updateItemDesc(int itemId,String attributeValue){
		try{
			prepStat=remoteConn.prepareStatement("Update tbl_items set item_type=? where item_id=?");
			prepStat.setString(1,attributeValue);
			prepStat.setInt(2,itemId);
			prepStat.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("Online Market App - Update Item Exception: " +e.getMessage());
		}
	}

	//admin can add items to the inventory
	public void insertItems(int itemId,String itemName,String itemType,String itemPrice, int itemQuantity){
		//exception handling block
		try{
			//insert admin input items into dataase
			PreparedStatement insertItem = remoteConn.prepareStatement("Insert into tbl_items values(?,?,?,?,?)");
			//set positional params
			insertItem.setInt(1,itemId);
			insertItem.setString(2,itemName);
			insertItem.setString(3,itemType);
			insertItem.setString(4,itemPrice);
			insertItem.setInt(5,itemQuantity);
			//executes the insert statement with above params
			insertItem.executeUpdate();

		}
		catch (SQLException e) {
			System.out.println("Online Market App - Insert Item Exception: " +e.getMessage());
		}
		
	}

	public void insertItemsToCart(int retrievedCId,int itemId,int itemQuantity){
		//exception handling block
		try{
			//insert admin input items into dataase
			prepStat=remoteConn.prepareStatement("Insert into tbl_itemcart values(?,?,?)");
			prepStat.setInt(1,retrievedCId);
			prepStat.setInt(2,itemId);
			prepStat.setInt(3,itemQuantity);

			prepStat.executeUpdate();

		}
		catch (SQLException e) {
			System.out.println("Online Market App - Insert Items to Cart Exception: " +e.getMessage());
		}
		
	}

}
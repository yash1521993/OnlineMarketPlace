// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.rmi.RemoteException;
// Ryan: Do you need everything in these packages?

// FIXED: imported only required package
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

// Ryan: Here you are violating separation of concerns by mixing
// Model and framework related code. Instead you should keep these
// isolated by maintaining high cohesion.  

//FIXED:seperated rmi part and model part by moving the server code 
//to another source file

/**
 * OnlineMarketModel acts as a server in RMI communication and as a model
 *	for MVC design pattern
 */
public class OnlineMarketModel {
	
	private ArrayList browsedList = new ArrayList();
	private ArrayList cartItemsData = new ArrayList();
	private ArrayList customerDataList = new ArrayList();
	
	private int rowNum=0;
	private String browsedItemData="",cartData="",retrievedId="",retrievedUId="",removeCustStatus="";
	private String registerStatus="",creationStatus="",updateStatus="",removeItemStatus="",customerData="";
	//creating  a new instance for mysql connection
	private SqlConnection connectSql=new SqlConnection();
	private Connection remoteConn=connectSql.connectMySql();
	private PreparedStatement prepStat;
	private Statement statement;
	private ResultSet cartItems,browsedItems,customerList,rsltSet,rsltSet1;

	private int custId=0;
	public static String userId="";
	private DbAccess dbAccess=new DbAccess();

	//registering a customer
	public String registerCustomer(String firstName,String lastName, String userName, String password) throws RemoteException{
		//customer insertion
		try{
			//checks if a customer already exists with same user name
			prepStat=remoteConn.prepareStatement("Select * from tbl_customers where username=?");
			prepStat.setString(1,userName);
			rsltSet=prepStat.executeQuery();
			while(rsltSet.next()){  
				//System.out.println("while");
				retrievedId=rsltSet.getString("username");
			}
			synchronized(this){
				//returns reg failed msg if username already exists
				if(retrievedId.equalsIgnoreCase(userName)){
					registerStatus= "New Customer Registration failed-User name already exists";
				}

				//if no match then insert a record to customers table and a cart table
				else{
					//insert customer registration details into dataase
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
					registerStatus="You are now successfully Registered";
				}
			}
		}
		catch (SQLException e) {
			System.out.println("Online Market App Exception-Registration: " +e.getMessage());
		}
		return registerStatus;
	}

	//registering a customer
	public String addUsers(String regType,String firstName,String lastName,String userName,String password) throws RemoteException{
		//customer insertion
		if(regType.equalsIgnoreCase("Customer")){
			try{
				//checks if a customer already exists with same user name
				prepStat=remoteConn.prepareStatement("Select * from tbl_customers where username=?");
				prepStat.setString(1,userName);
				rsltSet=prepStat.executeQuery();
				while(rsltSet.next()){  
					//System.out.println("while");
					retrievedUId=rsltSet.getString("username");
				}

				//returns reg failed msg if username already exists
				if(retrievedUId.equalsIgnoreCase(userName)){
					creationStatus= "New Customer Creation failed-User name already exists";
				}

				//if no match then insert a record to customers table and a cart table
				else{
					//insert customer registration details into dataase
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
					creationStatus="You have now successfully created a Customer account";
				}
			}	//end try
			catch (SQLException e) {
				System.out.println("Online Market App Exception-Registration: " +e.getMessage());
			}
			
		}//end parent customer-if


		else if(regType.equalsIgnoreCase("Admin")){
			//System.out.println("admin");
			try{
				//checks if a customer already exists with same user name
				prepStat=remoteConn.prepareStatement("Select username from tbl_admin where username=?");
				prepStat.setString(1,userName);
				rsltSet=prepStat.executeQuery();
				while(rsltSet.next()){  
					retrievedUId=rsltSet.getString("username");
				}

				//returns reg failed msg if username already exists
				if(retrievedUId.equalsIgnoreCase(userName)){
					//System.out.println("admin if");
					creationStatus= "New Admin Creation failed-User name already exists";
				}

				//if no match then insert a record to customers table and a cart table
				else{
					//insert customer registration details into dataase
					prepStat = remoteConn.prepareStatement("Insert into tbl_admin(first_name,last_name,username,password) values(?,?,?,?)");
					//set positional params
					prepStat.setString(1,firstName);
					prepStat.setString(2,lastName);
					prepStat.setString(3,userName);
					prepStat.setString(4,password);

					//executes the insert statement with above params
					prepStat.executeUpdate();

					creationStatus="You have now successfully created a Admin account";
				}
			}	//end try
			catch (SQLException e) {
				System.out.println("Online Market App Exception-Registration: " +e.getMessage());
			}
			
		}//end parent admin- else if
		else{
			creationStatus="Invalid account type input";
		}
		return creationStatus;
		
	}
	
	//this method checks for a valid customer or user
	public boolean validateLogin(String inputId,String inputPwd,String loginType) throws RemoteException{
		
		boolean loginCheck=false;
		//admin validation
		if(loginType.equalsIgnoreCase("Admin")){
			try{
				ResultSet rs= dbAccess.validateLogin(inputId,inputPwd,loginType);
				if(rs.next()){
					
					System.out.println("Admin Login Success");
					loginCheck=true;
				}
				else{
					System.out.println("Admin Login Denied");
					loginCheck=false;
				}
			}
			catch (SQLException e) {
				System.out.println("Online Market App Exception: " +e.getMessage());
			}

		}
		
		//customer validation
		if(loginType.equalsIgnoreCase("Customer")){
			try{
				ResultSet rs= dbAccess.validateLogin(inputId,inputPwd,loginType);
				if(rs.next()){
					userId=inputId;
					System.out.println("Customer Login Success");
					loginCheck=true;
				}
				else{
					System.out.println("Customer Login Denied");
					loginCheck=false;
				}
			}
			catch (SQLException e) {
				System.out.println("Online Market App Exception: " +e.getMessage());
			}
		}
		return loginCheck;
	}


	//browseItems allows a customer to browse over the app
	@SuppressWarnings("unchecked")
	public ArrayList browseItems(){
		System.out.println("======Your can Browse Market App to shop======");
		//exception handling block
		try{
			browsedItems=dbAccess.browseItems();
			//add each column data to browsed List
			while(browsedItems.next()){  
				browsedItemData=browsedItems.getInt(1)+"-----"+browsedItems.getString("item_name")+"-----"+browsedItems.getString("item_type")+"-----"+browsedItems.getString("price")+"-----"+browsedItems.getInt("quantity");
				browsedList.add(rowNum,browsedItemData);rowNum++;
			}
		}
		catch (SQLException e) {
			System.out.println("Online Market App Exception- Browse Items: " +e.getMessage());
		}

		return browsedList;
	}

	//view cart allows a customer to browse over the app
	@SuppressWarnings("unchecked")
	public ArrayList viewCart(){
		System.out.println("======Accessed cart view method======");
		//exception handling block
		try{
			cartItems=dbAccess.viewCart();
			//add each column data to browsed List
			while(cartItems.next()){  
				cartData=cartItems.getInt(1)+"-----"+cartItems.getInt(2)+"-----"+cartItems.getInt(3);
				cartItemsData.add(rowNum,cartData);rowNum++;
			}
		}
		catch (SQLException e) {
			System.out.println("Online Market App Exception- Browse Items: " +e.getMessage());
		}

		return cartItemsData;
	}
		
	//customer can add items to cart browsed apps
	public String addItemsToCart(int itemId, int itemQuantity){
		int currentStock=0,retrievedCId=0;
		String itemName="";
		//exception handling block
		try{
			System.out.println("======Accessed Customer Add Items to Cart Method======");
			//setup to execture a sql statement
			statement = remoteConn.createStatement();
			//retrieves all items with given itemId
			ResultSet selectedItem=statement.executeQuery("Select * from tbl_items where item_id="+itemId);
			while(selectedItem.next()){  
				currentStock=selectedItem.getInt("quantity");
				itemName=selectedItem.getString("item_name");
			}
			//condition check for item out of stock
			if(itemQuantity<=currentStock){
				
				//retrieves cart_id of the logged in customer
				prepStat=remoteConn.prepareStatement("select cart_id from tbl_cart join tbl_customers on tbl_customers.customer_id=tbl_cart.customer_id where username=?");
				prepStat.setString(1,userId);
				ResultSet rsltSet1=prepStat.executeQuery();
				while(rsltSet1.next()){
					retrievedCId=rsltSet1.getInt(1);
				}

				//updates cart table with the customer purchased item id
				prepStat=remoteConn.prepareStatement("Insert into tbl_itemcart values(?,?,?)");
				prepStat.setInt(1,retrievedCId);
				prepStat.setInt(2,itemId);
				prepStat.setInt(3,itemQuantity);

				prepStat.executeUpdate();
				return "Your item "+itemName+" has been added to cart successfully"	;			
			}
			else{
				return "----Requested quantity is more than current stock----";
			}
		}
		catch (SQLException e) {
			System.out.println("Online Market App Exception: " +e.getMessage());
		}
		
		return "Your item "+itemName+" not found in item list";
	}

	//customer can purchase browsed apps
	public String purchaseItems(){
		int currentStock=0,itemId=0,cartId=0,itemQuantity=0;
		ArrayList<Integer> itemList = new ArrayList<Integer>();
		ArrayList<Integer> cartStock = new ArrayList<Integer>();
		int i =0;
		String returnStatement = "";
		//exception handling block
		try{
			System.out.println("======Accessed Customer Check Out Method======");
			
			//setup to execture a sql statement
			statement = remoteConn.createStatement();
			//retrieves all items with given itemId
			prepStat = remoteConn.prepareStatement("Select * from tbl_itemcart join tbl_cart on tbl_cart.cart_id=tbl_itemcart.cart_id join tbl_customers on tbl_customers.customer_id=tbl_cart.customer_id where tbl_customers.username=?");
			prepStat.setString(1,userId);
			rsltSet=prepStat.executeQuery(); 
			while(rsltSet.next()){  
				cartStock.add(i,rsltSet.getInt("quantity"));
				itemList.add(i,rsltSet.getInt("item_id"));
				cartId=rsltSet.getInt("cart_id");
				i++;
			}
			
			//loop through all the cart items
			for(i = 0; i< cartStock.size();i++){
					//retrieve items original stock
					rsltSet1 = statement.executeQuery("Select quantity from tbl_items where item_id="+itemList.get(i));
					while(rsltSet1.next()){
						itemQuantity=rsltSet1.getInt("quantity");
					}

					//System.out.println("values"+cartStock.get(i)+"---"+itemQuantity);
					//condition check for item out of stock
					if(itemQuantity >= cartStock.get(i)){
						//System.out.println("ifff");
						//updates items table quantity
						prepStat=remoteConn.prepareStatement("Update tbl_items set quantity=? where item_id=?");
						
						prepStat.setInt(1,itemQuantity-cartStock.get(i));
						prepStat.setInt(2,itemList.get(i));
						prepStat.executeUpdate();
						returnStatement = returnStatement+" <<<<< "+itemList.get(i)+ " Purchase Successful >>>>\n";
								
					}
					else{
						/*System.out.println(itemQuantity);
						System.out.println(cartStock.get(i));
						System.out.println(itemQuantity >= cartStock.get(i));*/
						 returnStatement = returnStatement+" "+ itemList.get(i)+ "<<<<< Out Of Stock >>>>\n";
					}
			}
			//clears cart of respective customer
			prepStat=remoteConn.prepareStatement("Delete from tbl_itemcart where cart_id="+cartId);
			prepStat.executeUpdate();
			return 	returnStatement;
		}
		catch (SQLException e) {
			System.out.println("Online Market App Exception-Checkout: " +e.getMessage());
		}
		
		return "Error in check out";
	}

	//admin can add items to the inventory
	public String addItems(int itemId,String itemName,String itemType,String itemPrice, int itemQuantity){
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
			System.out.println("Online Market App Exception: " +e.getMessage());
		}
		System.out.println("======Accessed Admin add method======");
		return "+++++++++++Above item has been added to database+++++++++++\n";		
	}

	//admin can update items in the inventory
	public String updateItems(int itemId,String itemAttribute,String attributeValue){
		String itemType="";
		int itemPrice=0,itemQuantity=0;
		//exception handling block
		try{
			//retrieve admin input item id if exists
			statement = remoteConn.createStatement();
			rsltSet = statement.executeQuery("Select * from tbl_items where item_id="+itemId);

			while(rsltSet.next()){
				//System.out.println("while2");
				itemQuantity=rsltSet.getInt("quantity");
				itemPrice=rsltSet.getInt("price");
				itemType=rsltSet.getString("item_type");
			}
			synchronized(this){
				//if there is matched item Id then update user entered attribute
				if(itemPrice!=0){
					//update item price
					if(itemAttribute.equalsIgnoreCase("price")){
						prepStat=remoteConn.prepareStatement("Update tbl_items set price=? where item_id=?");
						prepStat.setString(1,attributeValue);
						prepStat.setInt(2,itemId);
						prepStat.executeUpdate();
						updateStatus="+++++++++++Above item has been updated to database+++++++++++\n";
					}
					//update item quantity
					else if(itemAttribute.equalsIgnoreCase("quantity")){
						prepStat=remoteConn.prepareStatement("Update tbl_items set quantity=? where item_id=?");
						prepStat.setInt(1,Integer.parseInt(attributeValue));
						prepStat.setInt(2,itemId);
						prepStat.executeUpdate();
						updateStatus="+++++++++++Above item has been updated to database+++++++++++\n";
					}
					//update item description or type
					else if(itemAttribute.equalsIgnoreCase("desc")){
						prepStat=remoteConn.prepareStatement("Update tbl_items set item_type=? where item_id=?");
						prepStat.setString(1,attributeValue);
						prepStat.setInt(2,itemId);
						prepStat.executeUpdate();
						updateStatus="+++++++++++Above item has been updated to database+++++++++++\n";
					}
					//nothing matches
					else{
						updateStatus="Update failed. Invalid Item Attibute";
					}
					
				}
				//if item id doesn't match from db
				else{
					updateStatus="Update failed. Invalid item Id";
				}
			}

		}
		catch (SQLException e) {
			System.out.println("Online Market App - Update Items Exception: " +e.getMessage());
		}
		System.out.println("======Accessed Admin Update method======");
		return updateStatus;		
	}


	//removeItem helps admin to remove a item from db
	public String removeItem(int itemId){
		int retItemId=0;
		try{
			//retrieve admin input item id if exists
			statement = remoteConn.createStatement();
			rsltSet = statement.executeQuery("Select * from tbl_items where item_id="+itemId);

			while(rsltSet.next()){
				retItemId=rsltSet.getInt("item_id");
			}
			//remove item entry from tbl_items if exists
			synchronized(this){
				if(retItemId!=0){
					prepStat=remoteConn.prepareStatement("Delete from tbl_items where item_id="+itemId);
					prepStat.executeUpdate();	
					removeItemStatus="Success-->Deleted requested Item.";
				}
				//if item id doesn't match from db
				else{
					removeItemStatus="Delete failed. Invalid item Id";
				}
			}
		}
		catch (SQLException e) {
			System.out.println("Online Market App - Remove Items Exception: " +e.getMessage());
		}
		return removeItemStatus;
	}

	//view customers allows a admin to browse over the app
	@SuppressWarnings("unchecked")
	public ArrayList viewCustomers(){
		System.out.println("======Accessed view all customers method======");
		//exception handling block
		try{
			customerList=dbAccess.viewCustomers();
			//add each column data to browsed List
			while(customerList.next()){  
				customerData=customerList.getInt(1)+"---------"+customerList.getString(2);
				customerDataList.add(rowNum,customerData);rowNum++;
			}
		}
		catch (SQLException e) {
			System.out.println("Online Market App Exception- Customer List: " +e.getMessage());
		}
		
		return customerDataList;
	}

	//removeCustomer helps admin to remove a customer from db
	public String removeCustomer(int customerId){
		int retCustomerId=0;
		try{
			//retrieve admin input customer id if exists
			statement = remoteConn.createStatement();
			rsltSet = statement.executeQuery("Select * from tbl_customers where customer_id="+customerId);

			while(rsltSet.next()){
				retCustomerId=rsltSet.getInt("customer_id");
			}
			//remove customer entry from tbl_customers, if exists
			synchronized(this){
				if(retCustomerId!=0){
					prepStat=remoteConn.prepareStatement("Delete from tbl_customers where customer_id="+customerId);
					prepStat.executeUpdate();	
					removeCustStatus="Success-->Deleted requested customer entry.";
				}
				//if customer id doesn't match from db, return this error msg
				else{
					removeCustStatus="Delete failed. Invalid Customer Id";
				}
			}
		}
		catch (SQLException e) {
			System.out.println("Online Market App - Remove Customer Exception: " +e.getMessage());
		}
		return removeCustStatus;
	}
}
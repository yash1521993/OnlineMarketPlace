// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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
	
	private Session session;
	private ArrayList browsedList = new ArrayList();
	private int rowNum=0;
	private String browsedItemData="",retrievedId="",retrievedUId="",registerStatus="",creationStatus="";
	//creating  a new instance for mysql connection
	private SqlConnection connectSql=new SqlConnection();
	private Connection remoteConn=connectSql.connectMySql();
	private PreparedStatement prepStat;
	private Statement statement;
	private ResultSet rsltSet;
	private int custId=0;
	public static String userId="";
	
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
				//System.out.println("admin while"+userName+"riddd:"+retrievedUId);
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
					//System.out.println("admin else");
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
		//works for id: admin and password: test
		if(loginType.equalsIgnoreCase("Admin")){
			try{
				prepStat=remoteConn.prepareStatement("Select * from tbl_admin where username=? and password=?");

				prepStat.setString(1,inputId);
				prepStat.setString(2,inputPwd);
				ResultSet rs=prepStat.executeQuery();
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
		//works for id: customer and password: test
		if(loginType.equalsIgnoreCase("Customer")){
			try{
				prepStat=remoteConn.prepareStatement("Select * from tbl_customers where username=? and password=?");

				prepStat.setString(1,inputId);
				prepStat.setString(2,inputPwd);
				ResultSet rs=prepStat.executeQuery();
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
		//exception handling block
		try{
			//retrieves all the items from db
			prepStat = remoteConn.prepareStatement("Select * from tbl_items");
			//browsedItems stores the above executed query result
			ResultSet browsedItems=prepStat.executeQuery(); 
			//add each column data to browsed List
			while(browsedItems.next()){  
				browsedItemData=browsedItems.getInt(1)+"-----"+browsedItems.getString("item_name")+"-----"+browsedItems.getString("item_type")+"-----"+browsedItems.getString("price")+"-----"+browsedItems.getInt("quantity");
				browsedList.add(rowNum,browsedItemData);rowNum++;
				//System.out.println("statement"+browsedList);
			}
		}
		catch (SQLException e) {
			System.out.println("Online Market App Exception- Browse Items: " +e.getMessage());
		}
		
		System.out.println("======Your can Browse Market App to shop======");
		return browsedList;
	}
		
	//customer can add items to cart browsed apps
	public String addItemsToCart(int itemId, int itemQuantity){
		int currentStock=0,retrievedCId=0;
		String itemName="";
		//exception handling block
		try{
			System.out.println("======Accessed Customer Purchase Method======");
			//setup to execture a sql statement
			statement = remoteConn.createStatement();
			//retrieves all items with given itemId
			ResultSet selectedItem=statement.executeQuery("Select * from tbl_items where item_id="+itemId);
			while(selectedItem.next()){  
				//System.out.println("itemId");
				//System.out.println(selectedItem.getInt(1)+" "+selectedItem.getString("ItemName")+" "+selectedItem.getString("ItemPrice")+" "+selectedItem.getInt("IQuantity"));
				currentStock=selectedItem.getInt("quantity");
				itemName=selectedItem.getString("item_name");
			}
			//condition check for item out of stock
			if(itemQuantity<=currentStock){
				//updates items table quantity
				prepStat=remoteConn.prepareStatement("Update tbl_items set quantity=? where item_id=?");
				//System.out.println("asgdgsdgadgasd"+(currentStock-itemQuantity));
				prepStat.setInt(1,currentStock-itemQuantity);
				prepStat.setInt(2,itemId);
				prepStat.executeUpdate();
				
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
			}
			else{
				return "----Requested quantity is more than current stock----";
			}
		}
		catch (SQLException e) {
			System.out.println("Online Market App Exception: " +e.getMessage());
		}
		
		return "Your item "+itemName+" has been purchased successfully";
	}

	//customer can purchase browsed apps
	public String purchaseItems(int itemId, int itemQuantity){
		int currentStock=0,retrievedCId=0;
		String itemName="";
		//exception handling block
		try{
			System.out.println("======Accessed Customer Purchase Method======");
			//setup to execture a sql statement
			statement = remoteConn.createStatement();
			//retrieves all items with given itemId
			ResultSet selectedItem=statement.executeQuery("Select * from tbl_items where item_id="+itemId);
			while(selectedItem.next()){  
				//System.out.println("itemId");
				//System.out.println(selectedItem.getInt(1)+" "+selectedItem.getString("ItemName")+" "+selectedItem.getString("ItemPrice")+" "+selectedItem.getInt("IQuantity"));
				currentStock=selectedItem.getInt("quantity");
				itemName=selectedItem.getString("item_name");
			}
			//condition check for item out of stock
			if(itemQuantity<=currentStock){
				//updates items table quantity
				prepStat=remoteConn.prepareStatement("Update tbl_items set quantity=? where item_id=?");
				//System.out.println("asgdgsdgadgasd"+(currentStock-itemQuantity));
				prepStat.setInt(1,currentStock-itemQuantity);
				prepStat.setInt(2,itemId);
				prepStat.executeUpdate();
				
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
			}
			else{
				return "----Requested quantity is more than current stock----";
			}
		}
		catch (SQLException e) {
			System.out.println("Online Market App Exception: " +e.getMessage());
		}
		
		return "Your item "+itemName+" has been purchased successfully";
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
}
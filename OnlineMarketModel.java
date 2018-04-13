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
import java.sql.*;
import java.util.*;

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
	//
	private Session session;
	private ArrayList browsedList = new ArrayList();
	private int rowNum=0;
	private String browsedItemData;
	//creating  a new instance for mysql connection
	private SqlConnection connectSql=new SqlConnection();
	private Connection remoteConn=connectSql.connectMySql();
	private PreparedStatement statement;

	//registering a customer
	public String registerCustomer() throws RemoteException{
		//yet to implement
		System.out.println("Registration page. Register here");
		return "Registered";
	}
	
	//this method checks for a valid customer or user
	public boolean validateLogin(String inputId,String inputPwd,String loginType) throws RemoteException{
		
		boolean loginCheck=false;
		//admin validation
		//works for id: admin and password: test
		if(loginType.equalsIgnoreCase("Admin")){
			if(inputId.equals("admin") && inputPwd.equals("test")){
				System.out.println("Success");
				loginCheck=true;
			}
			else{
				System.out.println("Denied");
				loginCheck=false;
			}
		}
		
		//customer validation
		//works for id: customer and password: test
		if(loginType.equalsIgnoreCase("Customer")){
			if(inputId.equals("customer") && inputPwd.equals("test")){
				System.out.println("Success");
				loginCheck=true;
			}
			else{
				System.out.println("Denied");
				loginCheck=false;
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
			statement = remoteConn.prepareStatement("Select * from Items");
			//browsedItems stores the above executed query result
			ResultSet browsedItems=statement.executeQuery(); 
			//add each column data to browsed List
			while(browsedItems.next()){  
				browsedItemData=browsedItems.getInt(1)+" "+browsedItems.getString("ItemName")+" "+browsedItems.getString("ItemPrice")+" "+browsedItems.getInt("IQuantity");
				browsedList.add(rowNum,browsedItemData);rowNum++;
				//System.out.println("statement"+browsedList);
			}
		}
		catch (SQLException e) {
			System.out.println("Online Market App Exception: " +e.getMessage());
		}
		
		System.out.println("======Your can Browse Market App to shop======");
		return browsedList;
	}
		
	//customer can purchase browsed apps
	public String purchaseItems(int itemId, int itemQuantity){
		int currentStock=0;
		String itemName="";
		//exception handling block
		try{
			System.out.println("======Accessed Customer Purchase Method======");
			//setup to execture a sql statement
			Statement st = remoteConn.createStatement();
			//retrieves all items with given itemId
			ResultSet selectedItem=st.executeQuery("Select * from Items where ItemId="+itemId);
			while(selectedItem.next()){  
				//System.out.println("itemId");
				System.out.println(selectedItem.getInt(1)+" "+selectedItem.getString("ItemName")+" "+selectedItem.getString("ItemPrice")+" "+selectedItem.getInt("IQuantity"));
				currentStock=selectedItem.getInt("IQuantity");
				itemName=selectedItem.getString("ItemName");
			}
			//condition check for item out of stock
			if(itemQuantity<=currentStock){
				statement=connectSql.connectMySql().prepareStatement("Update Items set IQuantity=? where ItemId=?");
				//System.out.println("asgdgsdgadgasd"+(currentStock-itemQuantity));
				statement.setInt(1,currentStock-itemQuantity);
				statement.setInt(2,itemId);
				
				statement.executeUpdate();
				
			}
			else{
				return "----Requested quantity is more than current stock----";
			}
		}
		catch (SQLException e) {
			System.out.println("Online Market App Exception: " +e.getMessage());
		}
		
		/*return "Purchase your browsed items here. Below is your wish list\n"+
				"--------------------Empty list--------------------------";*/
		return "Your item "+itemName+" has been purchased successfully";
	}

	//admin can add items to the inventory
	public String addItems(int itemId,String itemName,String itemPrice, int itemQuantity){
		//exception handling block
		try{

			//insert admin input items into dataase
			PreparedStatement insertItem = remoteConn.prepareStatement("Insert into Items values(?,?,?,?)");
			//set positional params
			insertItem.setInt(1,itemId);
			insertItem.setString(2,itemName);
			insertItem.setString(3,itemPrice);
			insertItem.setInt(4,itemQuantity);
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
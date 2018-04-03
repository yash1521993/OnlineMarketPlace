// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

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
	//creating  a new instance for mysql connection
	private SqlConnection connectSql=new SqlConnection();

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
	public String browseItems(){
		
		String browseQuery = "Select * from Items";
		PreparedStatement statement = connectSql.connectMySql().prepareStatement(browseQuery);
		System.out.println(statement);
		System.out.println("======Your can Browse Market App to shop======");
		//System.out.println("<-Your shopping items list here->");
		return "<---+++---Your shopping items list here----+++--->";
	}
		
	//customer can purchase browsed apps
	public String purchaseItems(){
		System.out.println("======Accessed Customer Purchase Method======");
		return "Purchase your browsed items here. Below is your wish list\n"+
				"--------------------Empty list--------------------------";
	}

	//admin can add items to the inventory
	public String addItems(){
		System.out.println("======Accessed Admin add method======");
		return "+++++++++++Add items here+++++++++++\n";		
	}
}
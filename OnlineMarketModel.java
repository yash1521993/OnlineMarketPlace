// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * OnlineMarketModel acts as a server in RMI communication and as a model
 *	for MVC design pattern
 */
public class OnlineMarketModel {
	
	public String registerCustomer() throws RemoteException{
		//yet to implement
		System.out.println("Registration page. Register here");
		return "Registered";
	}
	
	public static void main(String args[]) {
		// Set the RMI Security Manager...
		System.setSecurityManager(new SecurityManager());
		//try and catch block for exception handling
		try {
			System.out.println("You are now entering Online Market Place");
			
			// Connection string to Online Market Server
			String name = "//tesla.cs.iupui.edu:5555/home/yashkuru/OOAD/Assignment1/onlineMarketServer";
			
			// Create a new instance of a Online market server.
			OnlineMarketController marketCntrlr = new OnlineMarketController(name);
			System.out.println("Reaching server:" + name);
			
			// rebind binds the server and RMI Service
			Naming.rebind(name, marketCntrlr);
			
			System.out.println("Interface is Ready!You can register, login and shop");
		} 
		catch (Exception e){
			System.out.println("Online Market Server Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
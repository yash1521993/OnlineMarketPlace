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
 * BankServer - Must implement any and all methods found in the Bank
 * interface. The variable 'name' must include the location where the
 * BankServer is going to be registered with RMI to run.
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
		
		try {
			System.out.println("You are now entering Online Market Place");
			
			// Location of BankServer
			String name = "//tesla.cs.iupui.edu:5555/home/yashkuru/OOAD/Assignment1/onlineMarketServer";
			
			// Create a new instance of a BankServer.
			OnlineMarketController bank = new OnlineMarketController(name);
			
			System.out.println("Reaching server:" + name);
			
			// Binds the BankServer to the RMI Service.
			Naming.rebind(name, bank);
			
			System.out.println("Interface is Ready!You can register, login and shop");
		} 
		catch (Exception e){
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
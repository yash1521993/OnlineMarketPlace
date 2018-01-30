// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.rmi.Naming;

/*
 *	OnlineMarketView - This acts as a client for rmi communication and 
 *	view to present the model's data
 */
public class OnlineMarketView {
	public static void main(String args[]){
		// creates a security manager for RMI
		System.setSecurityManager(new SecurityManager( ));
		//variable declaration
		int regId;
		String register;
		//try and catch block for exception handling
		try{
			// Locate Online Market Server
			String name = "//tesla.cs.iupui.edu:5555/home/yashkuru/OOAD/Assignment1/onlineMarketServer";
			OnlineMarket marketApp = (OnlineMarket) Naming.lookup(name);
			//calling interface implemented methods
			regId = marketApp.regId();
			register=marketApp.registerCustomer();
			System.out.println("Registration ID: " + regId);	
			System.out.println("Registration Status: "+register);
		} 
		catch(Exception e){
			System.out.println("Online Market App Exception: " +e.getMessage());
			e.printStackTrace();
		}
		
		System.exit(0);
	}
}

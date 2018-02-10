// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.rmi.Naming;

public class MarketViewController{

	public static void main(String args[]){
			// creates a security manager for RMI
			System.setSecurityManager(new SecurityManager( ));
			//variable declaration
			int regId;
			String register;
			//try and catch block for exception handling
			try{
				// Locate Online Market Server
				String name = "//tesla.cs.iupui.edu:5432/onlineMarketServer";
				OnlineMarket marketApp = (OnlineMarket) Naming.lookup(name);
				//calling interface implemented methods
				regId = marketApp.regId();
				register=marketApp.registerCustomer();
				System.out.println("Registration ID: " + regId);	
				System.out.println("Registration Status: "+register);
				
				

				// Create new instance of a Front Controller...
				MarketFrontController frontController = new MarketFrontController();
				
				// Dispatch request for respective views...
			    frontController.dispatchRequest("Admin");
			    frontController.dispatchRequest("Customer");			    

			    //login
				
			} 
			catch(Exception e){
				System.out.println("Online Market App Exception: " +e.getMessage());
				e.printStackTrace();
			}
			
			System.exit(0);
	}
}
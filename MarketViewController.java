// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.rmi.Naming;
import java.util.Scanner;

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
	
				
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter 'Admin' for Administrator login without quotes");
				System.out.println("Enter 'Customer' for Customer login without quotes");
				System.out.println("--------Enter one from above---------");
				
				String loginType = scanner.nextLine();
				
				// instantiating frontController class			
				MarketFrontController frontController = new MarketFrontController();
				
				// calling respective views either admin or customer
				if(loginType=="Admin"){
					frontController.dispatchRequest(loginType);
				}
				else{
					frontController.dispatchRequest(loginType);			    
				}
			    
				scanner.close();
			} 
			catch(Exception e){
				System.out.println("Online Market App Exception: " +e.getMessage());
				e.printStackTrace();
			}
			
			System.exit(0);
	}
}
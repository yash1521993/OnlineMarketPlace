// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.rmi.Naming;
import java.util.Scanner;

/*
	MarketViewController acts as a client controller and provides communication
	between client and server side controller
*/
public class MarketViewController{
	static OnlineMarket marketApp;
	boolean loginStatus;
	Session session;
	String browseItems,purchaseItems,addItems;

	//validateUserLogin method calls interface which further 
	//communicates with Server side controller and model
	public boolean validateUserLogin(String loginType){
		//Object creation for generic view
		MarketCommonView marketView=new MarketCommonView();
		String inputId=marketView.getInputLoginId();
		String inputPwd=marketView.getInputLoginPwd();
		
		try{
			if(loginType.equalsIgnoreCase("admin")) {
				loginStatus=marketApp.validateAdminLogin(inputId,inputPwd,loginType);
				System.out.println("Login Status:" + loginStatus);
				//return loginStatus;
			}
			else if(loginType.equalsIgnoreCase("customer")){
				loginStatus=marketApp.validateCustomerLogin(inputId,inputPwd,loginType);
				System.out.println("Login Status:" + loginStatus);
				//return loginStatus;
			}
			else
				System.out.println(">>>>>Input should be only from either of the above roles<<<<<<");
		}
		catch(Exception e){
				System.out.println("Online Market App Exception: " +e.getMessage());
				//e.printStackTrace();
		}
		
		return loginStatus;
			
	}

	//this method calls createSession() via RMI interface
	public Session createSession(String userType){
		try{
			session= marketApp.createSession(userType);
		}	
		catch(Exception e){
				System.out.println("Online Market App- Session Exception: " +e.getMessage());
				//e.printStackTrace();
		}
		return session;
	}


	//browseItems method which calls browseItems from interface
	public String browseItems(Session session){
		
		try{
				browseItems= marketApp.browseItems(session);
			}
			catch(Exception e){
				System.out.println("Online Market App-Browse Items Exception: " +e.getMessage());
				//e.printStackTrace();
			}

		return browseItems;
	}

	//purchaseItems method which calls purchaseItems from interface
	public String purchaseItems(Session session){
		
		try{
				//System.out.println("p fn");
				purchaseItems= marketApp.purchaseItems(session);
			}
			catch(Exception e){
				System.out.println("Online Market App-Purchase Items Exception: " +e.getMessage());
				//e.printStackTrace();
			}

		return purchaseItems;
	}

	//purchaseItems method which calls purchaseItems from interface
	public String addItems(Session session){
		
		try{
				//System.out.println("p fn");
				addItems= marketApp.addItems(session);
			}
			catch(Exception e){
				System.out.println("Online Market App-Purchase Items Exception: " +e.getMessage());
				//e.printStackTrace();
			}

		return addItems;
	}


	//main method
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
				marketApp = (OnlineMarket) Naming.lookup(name);
				//calling interface implemented methods
				regId = marketApp.regId();
				register=marketApp.registerCustomer();
				System.out.println("Registration ID: " + regId);	
				System.out.println("Registration Status: "+register);

				MarketCommonView marketView=new MarketCommonView();

				// instantiating frontController class			
				MarketFrontController frontController = new MarketFrontController();
				
				// calling respective views either admin or customer
				frontController.dispatchRequest(marketView.getLoginType());	
			} 
			catch(Exception e){
				System.out.println("Online Market App Exception: " +e.getMessage());
				e.printStackTrace();
			}
			
			System.exit(0);
	}
}
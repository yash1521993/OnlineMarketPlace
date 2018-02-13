// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.rmi.Naming;
import java.util.Scanner;

public class MarketViewController{
	static OnlineMarket marketApp;
	//OnlineMarketController serverController = new OnlineMarketController();
	boolean loginStatus=false;
	
	public void validateUserLogin(){
		
		MarketCommonView marketView=new MarketCommonView();
		String loginType=marketView.getLoginType();
		String inputId=marketView.getInputLoginId();
		String inputPwd=marketView.getInputLoginPwd();
		
		//System.out.println("Registration ID: " + inputId+inputPwd);
		try{
			loginStatus=marketApp.validateLogin(inputId,inputPwd,loginType);
			//System.out.println("Login Status" + inputId);
		}
		catch(Exception e){
				System.out.println("Online Market App Exception: " +e.getMessage());
				e.printStackTrace();
		}
		
		if(loginStatus){
			System.out.println("Authorization Success for user:" + inputId);
			// instantiating frontController class			
			MarketFrontController frontController = new MarketFrontController();
			
			// calling respective views either admin or customer
			frontController.dispatchRequest(loginType);
		}
		else{
			System.out.println("Authorization Denied for user:" + inputId);
		}
	}

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
	
				MarketViewController marketController=new MarketViewController();
				marketController.validateUserLogin();
			} 
			catch(Exception e){
				System.out.println("Online Market App Exception: " +e.getMessage());
				e.printStackTrace();
			}
			
			System.exit(0);
	}
}
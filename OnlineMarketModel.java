// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// Ryan: Here you are violating separation of concerns by mixing
// Model and framework related code. Instead you should keep these
// isolated by maintaining high cohesion.  

//Fixed:seperated rmi part and model part by moving the server code 
//to another source file

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
	
	public boolean validateLogin(String inputId,String inputPwd,String loginType) throws RemoteException{
		
		boolean loginCheck=false;
		
		if(loginType.equalsIgnoreCase("admin")){
			if(inputId.equals("admin") && inputPwd.equals("test")){
				System.out.println("Success");
				loginCheck=true;
			}
			else{
				System.out.println("Denied");
				loginCheck=false;
			}
		}
		
		if(loginType.equalsIgnoreCase("customer")){
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
	
	
}
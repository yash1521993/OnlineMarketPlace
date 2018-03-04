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

//FIXED:seperated rmi part and model part by moving the server code 
//to another source file

/**
 * OnlineMarketModel acts as a server in RMI communication and as a model
 *	for MVC design pattern
 */
public class OnlineMarketModel {

	Session session;
	//registering a customer
	public String registerCustomer() throws RemoteException{
		//yet to implement
		System.out.println("Registration page. Register here");
		return "Registered";
	}
	
	//this method checks for a valid customer or user
	public Session validateLogin(String inputId,String inputPwd,String loginType) throws RemoteException{
		
		boolean loginCheck=false;
		
		//admin validation
		//works for id: admin and password: test
		if(loginType.equalsIgnoreCase("admin")){
			if(inputId.equals("admin") && inputPwd.equals("test")){
				System.out.println("Success");
				loginCheck=true;

				session = new Session(loginType,loginCheck);
				//return session;

			}
			else{
				System.out.println("Denied");
				loginCheck=false;

				session = new Session(loginType,loginCheck);
				//return session;
			}
		}
		
		//customer validation
		//works for id: customer and password: test
		if(loginType.equalsIgnoreCase("customer")){
			if(inputId.equals("customer") && inputPwd.equals("test")){
				System.out.println("Success");
				loginCheck=true;

				session = new Session(loginType,loginCheck);
				//return session;
			}
			else{
				System.out.println("Denied");
				loginCheck=false;

				session = new Session(loginType,loginCheck);
				//return session;
			}
		}
		return session;
	}
		
}
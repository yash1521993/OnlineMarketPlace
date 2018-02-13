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
	
	// String loginId="test";
		// String loginPwd="testing";
		
		
		// if(loginId.equals(inputLoginId) && loginPwd.equals(inputLoginPwd)){
			// System.out.println("Success");
		// }
		// else{
			// System.out.println("Denied");
		// }
	
}
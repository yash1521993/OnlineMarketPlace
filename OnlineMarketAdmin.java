// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.rmi.Naming;

// Ryan: Here you are violating separation of concerns by mixing
// Model and framework related code. Instead you should keep these
// isolated by maintaining high cohesion.  


//Fixed: I have seperated the RMI part by adding it to another source file
//this file contains the view

/*
 *	OnlineMarketView - This acts as a client for rmi communication and 
 *	view to present the model's data
 */
public class OnlineMarketAdmin {
	
	public void validateLogin(){
		System.out.println("Verifying Login details");
	}

	public void showView() {
		System.out.println("Welcome to the Admin Page!");
	}

}
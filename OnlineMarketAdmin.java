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
 *	OnlineMarketAdmin - Creates admin view
 */
public class OnlineMarketAdmin implements MarketAdminInterface{
	//overriding
	public void adminInfo(){
		System.out.println("------------------------------------");
		System.out.println("---Welcome to the Admin Home Page---");
		System.out.println("------------------------------------");
		System.out.println("You can now Browse, Add, Delete, Update");
	}
	

}
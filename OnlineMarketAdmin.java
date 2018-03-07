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
	MarketViewController mvc=new MarketViewController();
	String samp;
	//overriding
	@Override
	public void adminInfo(){
		System.out.println("------------------------------------");
		System.out.println("---Welcome to the Admin Home Page---");
		System.out.println("------------------------------------");
		System.out.println("You can now Browse, Add, Delete, Update");
	}
	
	//member method- addItems helps a admin to add items to market app
	@Override
	public void addItems(Session session){
		try{
			//System.out.println("add items fn");
			samp=mvc.addItems(session);
			System.out.println(samp);
		}
		catch(Exception e){
			System.out.println("Online Market App- Add Items Exception: " +e.getMessage());
			e.printStackTrace();
		}
	}

}
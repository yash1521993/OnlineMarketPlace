// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.rmi.Naming;
// Ryan: Do you need everything in this package?
import java.util.*;
// Ryan: Here you are violating separation of concerns by mixing
// Model and framework related code. Instead you should keep these
// isolated by maintaining high cohesion.  


//Fixed: I have separated the RMI part by adding it to another source file
//this file contains the view

/*
 *	OnlineMarketAdmin - Creates admin view
 */
public class OnlineMarketAdmin implements MarketAdminInterface{
	private MarketViewController mvc=new MarketViewController();
	private String samp;
	private ArrayList browseItem;
	//overriding
	@Override
	public void adminInfo(){
		//prints this user info on screen
		System.out.println("------------------------------------");
		System.out.println("---Welcome to the Admin Home Page---");
		System.out.println("------------------------------------");
		System.out.println("You can now Browse, Add, Delete, Update");
	}
	
	//member method- addItems helps a admin to add items to market app
	@Override
	public void addItems(Session session){
		try{
			System.out.print("+++++++++++Add items here+++++++++++\n");
			//scanner class allows the admin to enter his/her input
			Scanner scanner = new Scanner(System.in);
			//read input item id, name, price and quantity
			System.out.print("Enter Item Id: ");
			int itemId = scanner.nextInt();

			System.out.print("Enter Item Name: ");
			String itemName = scanner.next();

			System.out.print("Enter Item Price: ");
			String itemPrice = scanner.next();

			System.out.print("Enter Item Quantity: ");
			int itemQuantity = scanner.nextInt();
			//pass these input items to client controller
			samp=mvc.addItems(session,itemId,itemName,itemPrice,itemQuantity);
			System.out.println(samp);
		}
		catch(Exception e){
			System.out.println("Online Market App- Add Items Exception: " +e.getMessage());
			//e.printStackTrace();
		}
	}


	//member method- browseItems helps a customer to browse items
	@Override
	public void browseItems(Session session){
		//exception handling block
		try{
			browseItem=mvc.browseItems(session);
			//displaying items from database
			System.out.println("<---+++---Your shopping items list here----+++--->");
			System.out.println("ItemId"+"  "+"ItemName"+"  "+"ItemPrice"+"  "+"ItemQuantity");
			for(int i = 0; i < browseItem.size(); i++) {
	            System.out.println(browseItem.get(i));
	        }
		}
		catch(Exception e){
			System.out.println("Online Market App Exception: " +e.getMessage());
			//e.printStackTrace();
		}
	}

}
// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.rmi.Naming;
// Ryan: Do you need everything in this package?

// FIXED: imported only required package
import java.util.ArrayList;
import java.util.Scanner;
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
	private ArrayList browseItem,viewCustomers;
	//overriding
	@Override
	public void adminInfo(){
		//prints this user info on screen
		System.out.println("------------------------------------");
		System.out.println("---Welcome to the Admin Home Page---");
		System.out.println("------------------------------------");
		System.out.println("You can now Browse, Add, Delete, Update");
	}

	//method addUsers helps admin to add a new Customers/Admin
	@Override
	public void addUsers(Session session){
		try{
			//scanner class allows the admin to enter his/her input
			Scanner scanner = new Scanner(System.in);

			System.out.print("+++++++++++Add Customer/Admin here+++++++++++\n");

			System.out.println("Enter 'Admin' to add a new admin to database");
			System.out.println("Enter 'Customer' to add a new admin to database");
			System.out.println("--------------Enter one from above------------------");
			String accType = scanner.nextLine();
			
			System.out.println("Enter First Name:");
			String firstName = scanner.nextLine();

			System.out.println("Enter Last Name:");
			String lastName = scanner.nextLine();

			System.out.println("Enter UserId:");
			String inputLoginId = scanner.nextLine();

			System.out.println("Enter Password:");
			String inputLoginPwd = scanner.nextLine();

			//pass these input fields to client controller
			samp=mvc.addUsers(session,accType,firstName,lastName,inputLoginId,inputLoginPwd);
			System.out.println(samp);
		}
		catch(Exception e){
			System.out.println("Online Market App- Add Users Exception: " +e.getMessage());
		}
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

			System.out.print("Enter Item Name without space: ");
			String itemName = scanner.next();

			System.out.print("Enter Item Type without space: ");
			String itemType = scanner.next();

			System.out.print("Enter Item Price: ");
			String itemPrice = scanner.next();

			System.out.print("Enter Item Quantity: ");
			int itemQuantity = scanner.nextInt();
			//pass these input items to client controller
			samp=mvc.addItems(session,itemId,itemName,itemType,itemPrice,itemQuantity);
			System.out.println(samp);
		}
		catch(Exception e){
			System.out.println("Online Market App- Add Items Exception: " +e.getMessage());
		}
	}

	//member method- updateItems helps a admin to update items attributes
	@Override
	public void updateItems(Session session){
		try{
			System.out.print("++++------++++Update items here++++------++++\n");
			//scanner class allows the admin to enter his/her input
			Scanner scanner = new Scanner(System.in);
			//read input item id, (name, price or quantity)-whichever field is to be updated
			System.out.print("Enter Item Id to update: ");
			int itemId = scanner.nextInt();

			System.out.println("----Enter Item attribute to be updated----");
			System.out.println("Any of the attributes either: price or quantity or desc::");
			String itemAttribute = scanner.next();

			System.out.print("Enter Item attribute Value: ");
			String attributeValue = scanner.next();

			//pass these input items to client controller
			samp=mvc.updateItems(session,itemId,itemAttribute,attributeValue);
			System.out.println(samp);
		}
		catch(Exception e){
			System.out.println("Online Market App- Add Items Exception: " +e.getMessage());
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
			System.out.println("ItemId"+"  "+"ItemName"+"  "+"ItemType"+"  "+"ItemPrice"+"  "+"ItemQuantity");
			for(int i = 0; i < browseItem.size(); i++) {
	            System.out.println(browseItem.get(i)+"\t"+"\t");
	        }
	        System.out.println("<------------------------------------------------>");
		}
		catch(Exception e){
			System.out.println("Online Market App Exception- Browse Items: " +e.getMessage());
		}
	}


	//member method- removeItem helps a admin to remove items from db
	@Override
	public void removeItem(Session session){
		try{
			System.out.print("*--------------Remove Items here--------------*\n");
			//scanner class allows the admin to enter his/her input
			Scanner scanner = new Scanner(System.in);
			//read input item id
			System.out.print("Enter Item Id to delete the item: ");
			int itemId = scanner.nextInt();

			//pass these input items to client controller
			samp=mvc.removeItem(session,itemId);
			System.out.println(samp);
		}
		catch(Exception e){
			System.out.println("Online Market App- Remove Items Exception: " +e.getMessage());
		}
	}

	//member method- removeCustomer helps a admin to remove customers from db
	@Override
	public void removeCustomer(Session session){
		try{
			System.out.print("*--------------Remove Customers here--------------*\n");
			//scanner class allows the admin to enter his/her input
			Scanner scanner = new Scanner(System.in);
			//read input customer id
			System.out.print("Enter Customer UserId to delete the customer: ");
			int customerId = scanner.nextInt();

			//pass these input items to client controller
			samp=mvc.removeCustomer(session,customerId);
			System.out.println(samp);
		}
		catch(Exception e){
			System.out.println("Online Market App- Remove Customer Exception: " +e.getMessage());
		}
	}

	//member method- viewCustomers helps a admin to view all customers from db
	@Override
	public void viewCustomers(Session session){
		try{
			System.out.print("*----*****-------List of Customers-------*****----*\n");
			
			//pass these input items to client controller
			viewCustomers=mvc.viewCustomers(session);

			System.out.println("CustomerId"+"  "+"UserName");
			for(int i = 0; i < viewCustomers.size(); i++) {
	            System.out.println(viewCustomers.get(i)+"\t"+"\t");
	        }
	        System.out.println("<------------------------------------------------>");
			//System.out.println(viewCustomers);
		}
		catch(Exception e){
			System.out.println("Online Market App- Remove Customer Exception: " +e.getMessage());
		}
	}

}
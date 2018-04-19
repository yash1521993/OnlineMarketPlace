// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.util.Scanner;

/**
 * MarketDispatcher class provides scope for reaching 
 *	views based on the received request
 */

public class MarketDispatcher {
	// Ryan: Should these really be publicly visible?
	
	// FIXED: Made the variable private
	// Concrete Views
	private MarketAbstractFactory adminCreator;
	private MarketAbstractFactory custCreator;
	//AuthenticateUserException errorMsg=new AuthenticateUserException("ssss");
	//Constructor
	public MarketDispatcher() {
		adminCreator = MarketFactoryCreator.getLoginType("AdminFact");
		custCreator = MarketFactoryCreator.getLoginType("CustFact");
	}

	//dispatch renders a view based on received request
	public void dispatch(String request, Session session) {

		// Admin or Customer View
		if(request.equalsIgnoreCase("Admin")) {

			MarketAdminInterface marketAdmin = adminCreator.getAdminInfo(request);
			marketAdmin.adminInfo();
			
			//command pattern implementation
			
			//invoking customer tasks
			AddMarketItems addItems = new AddMarketItems(marketAdmin,session);
			BrowseAdminMarketItems browseItems = new BrowseAdminMarketItems(marketAdmin,session);
			AddUsers addUsers = new AddUsers(marketAdmin,session);

			//creating invoker or broker object
			CommonInvoker invoker = new CommonInvoker();
			//read user input 
			System.out.println("Hi Admin! You have the following commands to perform");
			//while loop to provide continuous user input
			while(true){
				Scanner scanner = new Scanner(System.in);
				System.out.println("---Enter 'Browse' ignoring quotes to shop");
				System.out.println("---Enter 'AddI' ignoring quotes to buy items");
				System.out.println("---Enter 'AddU' ignoring quotes to add Customer/Admin");
				System.out.println(">>To exit these commands, press ctrl+c<<");
				String adminInput = scanner.nextLine();
				//command invocation based on user input
				if(adminInput.equalsIgnoreCase("addi")){
					invoker.tasksListAdmin(addItems);
				}
				else if(adminInput.equalsIgnoreCase("addu")){
					invoker.tasksListAdmin(addUsers);
				}
				else if(adminInput.equalsIgnoreCase("browse")){
					invoker.tasksListAdmin(browseItems);
				}
				else{
					System.out.println("Invalid command input");
				}

			//execute the admin tasks
				invoker.executeAdminTasks();
			}
	    }
	    else{
			//abstract factory invocation
			MarketCustomerInterface marketCustomer = custCreator.getCustomerInfo(request);
			marketCustomer.customerInfo();
			
			//command pattern implementation
			
			//invoking customer tasks
			BrowseMarketItems browseItems = new BrowseMarketItems(marketCustomer,session);
			PurchaseMarketItems buyItems = new PurchaseMarketItems(marketCustomer,session);
			//creating invoker or broker object
			CommonInvoker invoker = new CommonInvoker();
			System.out.println("Hi Customer! You have the following commands to perform");
			//read user input 
			//while loop to provide continuous user input
			while(true){
				Scanner scanner = new Scanner(System.in);
				
				System.out.println("---Enter 'Browse' ignoring quotes to shop");
				System.out.println("---Enter 'Purchase' ignoring quotes to buy items");
				System.out.println(">>To exit these commands, press ctrl+c<<");
				String custInput = scanner.nextLine();
				//command invocation based on user input
				if(custInput.equalsIgnoreCase("purchase")){
					invoker.tasksList(buyItems);
				}
				else if(custInput.equalsIgnoreCase("browse")){
					invoker.tasksList(browseItems);
				}
				else{
					System.out.println("Invalid command input");
				}
				//execute the customer tasks
				invoker.executeCustomerTasks();
			}
	    }

	}

}
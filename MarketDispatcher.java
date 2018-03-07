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
	// Concrete Views
	MarketAbstractFactory adminCreator;
	MarketAbstractFactory custCreator;
	//AuthenticateUserException errorMsg=new AuthenticateUserException("ssss");
	//Constructor
	public MarketDispatcher() {
		adminCreator = MarketFactoryCreator.getLoginType("AdminFact");
		custCreator = MarketFactoryCreator.getLoginType("CustFact");
	}

	//disptach renders a view based on received request
	public void dispatch(String request, Session session) {

		// Admin or Customer View
		if(request.equalsIgnoreCase("Admin")) {

			MarketAdminInterface marketAdmin = adminCreator.getAdminInfo(request);
			marketAdmin.adminInfo();
			
			//command pattern implementation
			
			//invoking customer tasks
			AddMarketItems addItems = new AddMarketItems(marketAdmin,session);
			//PurchaseMarketItems buyItems = new PurchaseMarketItems(marketCustomer,session);

			//creating invoker or broker object
			CommonInvoker invoker = new CommonInvoker();
			//read user input 
			Scanner scanner = new Scanner(System.in);
			System.out.println("Hi Admin! You have the following commands to perform");
			System.out.println("---Other commands coming soon.....----");
			System.out.println("---Enter 'Add' ignoring quotes to buy items");
			String adminInput = scanner.nextLine();
			//command invocation based on user input
			if(adminInput.equalsIgnoreCase("add")){
				invoker.tasksListAdmin(addItems);
			}
			else{
				System.out.println("Invalid command input");
			}
			/*else{
				invoker.tasksList(browseItems);
			}*/
			//execute the admin tasks
			invoker.executeAdminTasks();

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
			//read user input 
			Scanner scanner = new Scanner(System.in);
			System.out.println("Hi Customer! You have the following commands to perform");
			System.out.println("---Enter 'Browse' ignoring quotes to shop");
			System.out.println("---Enter 'Purchase' ignoring quotes to buy items");
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
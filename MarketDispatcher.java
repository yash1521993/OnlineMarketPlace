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
	
	//Constructor
	public MarketDispatcher() {
		adminCreator = MarketFactoryCreator.getLoginType("AdminFact");
		custCreator = MarketFactoryCreator.getLoginType("CustFact");
	}

	//disptach renders a view based on received request
	public void dispatch(String request) {

		// Admin or Customer View
		if(request.equalsIgnoreCase("Admin")) {
			MarketAdminInterface marketAdmin = adminCreator.getAdminInfo(request);
			marketAdmin.adminInfo();
	    }
	    else{
			//abstract factory invocation
			MarketCustomerInterface marketCustomer = custCreator.getCustomerInfo(request);
			marketCustomer.customerInfo();
			
			//command pattern implementation
			OnlineMarketCustomer customerTask = new OnlineMarketCustomer();
			//invoking customer tasks
			BrowseMarketItems browseItems = new BrowseMarketItems(customerTask);
			PurchaseMarketItems buyItems = new PurchaseMarketItems(customerTask);
			//creating invoker or broker object
			CustomerInvoker invoker = new CustomerInvoker();
			//read user input 
			Scanner scanner = new Scanner(System.in);
			System.out.println("Hi! You have the following commands to perform");
			System.out.println("---Enter 'Browse' ignoring quotes to shop");
			System.out.println("---Enter 'Purchase' ignoring quotes to buy items");
			String custInput = scanner.nextLine();
			//command invocation based on user input
			if(custInput.equalsIgnoreCase("purchase")){
				invoker.tasksList(buyItems);
			}
			else{
				invoker.tasksList(browseItems);
			}
			//execute the customer tasks
			invoker.executeCustomerTasks();
	    }

	}

}
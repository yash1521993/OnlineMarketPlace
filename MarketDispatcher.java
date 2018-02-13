// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

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
			
			MarketCustomerInterface marketCustomer = custCreator.getCustomerInfo(request);
			marketCustomer.customerInfo();
			
			//command pattern implementation
			OnlineMarketCustomer customerTask = new OnlineMarketCustomer();

			BrowseMarketItems browseItems = new BrowseMarketItems(customerTask);
			PurchaseMarketItems buyItems = new PurchaseMarketItems(customerTask);

			CustomerInvoker invoker = new CustomerInvoker();
			invoker.tasksList(browseItems);
			invoker.tasksList(buyItems);

			invoker.executeCustomerTasks();
	    }

	}

}
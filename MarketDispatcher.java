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
	
	public MarketDispatcher() {
		adminCreator = MarketFactoryCreator.getLoginType("AdminFact");
		custCreator = MarketFactoryCreator.getLoginType("CustFact");
	}

	
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
			OnlineMarketCustomer abcStock = new OnlineMarketCustomer();

			BrowseMarketItems buyStockOrder = new BrowseMarketItems(abcStock);
			PurchaseMarketItems sellStockOrder = new PurchaseMarketItems(abcStock);

			CustomerInvoker broker = new CustomerInvoker();
			broker.tasksList(buyStockOrder);
			broker.tasksList(sellStockOrder);

			broker.executeCustomerTasks();
	    }

	}

}
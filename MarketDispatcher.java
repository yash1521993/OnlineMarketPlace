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
	// Concrete Views...
	private OnlineMarketAdmin adminView;
	private OnlineMarketCustomer customerView;
	
	public MarketDispatcher() {
		adminView = new OnlineMarketAdmin();
		customerView = new OnlineMarketCustomer();
	}

	
	public void dispatch(String request) {

		// Faculty or Student Views...
		if(request.equalsIgnoreCase("Admin")) {
			adminView.showView();
	    }
	    else{
	    	customerView.showView();
	    }

	}

}
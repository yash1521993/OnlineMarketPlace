/**
 * Dispatcher Class
 */
public class MarketDispatcher {
	// Concrete Views...
	
	
	public Dispatcher() {
		private OnlineMarketAdmin adminView = new OnlineMarketAdmin();
		private OnlineMarketCustomer customerView = new OnlineMarketCustomer();
	   
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
// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru


public class MarketFrontController{

	// Dispatcher instance...
	private MarketDispatcher dispatcher;

	/**
	 * Front Controller Constructor
	 */
	public MarketFrontController() {
		dispatcher = new MarketDispatcher();
	}

	/**
	 * Attempt to authentic user login.
	 * 
	 * @return T/F
	 */
	private boolean isAuthenticUser() {
		System.out.println("User is authenticated successfully.");	    
		return true;
	}

	/**
	 * Responsible for dispatching the request to the Dispatcher.
	 * 
	 * @param request
	 */
	public void dispatchRequest(String request) {
		System.out.println("Page Requested: " + request);   
		
		// If the user has been authenticated - dispatch request...
		if(isAuthenticUser()) {
			dispatcher.dispatch(request);
	    }	
	}

}
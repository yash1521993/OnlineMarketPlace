// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/**
 * Front Controller which dispatches respective view based on request
 */
public class MarketFrontController{

	// Dispatcher instance
	private MarketDispatcher dispatcher;

	//constructor
	public MarketFrontController() {
		dispatcher = new MarketDispatcher();
	}

	
	// authenticates user based on server logic check
	//returns a  boolean value
	private boolean isAuthenticUser(boolean loginStatus) {
		if(loginStatus)
			return true;	
		else
			return false;
	}

	//calls dispatcher if authentication is successful
	public void dispatchRequest(String request, boolean loginStatus) {
		
		// If the user has been authenticated - dispatch request...
		if(isAuthenticUser(loginStatus)) {
			System.out.println("You are now accessing market application as: " + request); 
			dispatcher.dispatch(request);
	    }	
		else{
			System.out.println("Authorization denied for user type: " + request); 
		}
	}

}
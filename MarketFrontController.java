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
	Session session;
	//constructor
	public MarketFrontController() {
		dispatcher = new MarketDispatcher();
	}

	// authenticates user based on server logic check
	//returns a  boolean value
	private boolean isAuthenticUser(String loginType) {
		/*if(loginStatus)
			return true;	
		else
			return false;*/
		MarketViewController marketController = new MarketViewController();
		session = marketController.createSession(loginType);
		return marketController.validateUserLogin(session,loginType);
	}

	//calls dispatcher if authentication is successful
	public void dispatchRequest(String loginType) {
		
		// If the user has been authenticated - dispatch request...
		if(isAuthenticUser(loginType)) {
			System.out.println("You are now accessing market application as: " + loginType); 
			dispatcher.dispatch(loginType,session);
	    }	
		else{
			System.out.println("Authorization denied for user type: " + loginType); 
		}
	}

}
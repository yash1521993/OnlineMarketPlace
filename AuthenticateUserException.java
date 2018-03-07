// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/*
	User defined exception used for distributed exception handling
*/
public class AuthenticateUserException extends RuntimeException {
	//constructor with error message
	public AuthenticateUserException(String property) {
		super("Unauthorized access. "+ property+" access is restricted." );
	}

}

// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

public class AuthenticateUserException extends RuntimeException {

	public AuthenticateUserException(String property) {
		super("Unauthorized access. "+ property+" access is restricted." );
	}

}

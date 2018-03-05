// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

public class AuthenticateUserException extends RuntimeException {

	public AuthenticateUserException(String methodName) {
		super("Invalid Authorization - Access Denined to " + methodName + "() 		function!");
	}
	
}

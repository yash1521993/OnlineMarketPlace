// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

// Ryan: Do you need everything in this package?

// FIXED: imported only required package
import java.io.Serializable;
/*
	Session class creates a new session for each user
*/
public class Session implements java.io.Serializable{
	// Ryan: Should this really be publicly visible?

	// FIXED: Made the variable private
	private String loginType;
	
	public Session(String loginType){
		this.loginType=loginType;
	}

	//returns login type
	public String getLoginType(){
		return loginType;
	}
}
// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru
import java.io.*;

public class Session implements java.io.Serializable{

	String loginType;
	boolean loginStatus;
	public Session(String loginType, boolean loginStatus){
		this.loginType=loginType;
		this.loginStatus=loginStatus;
	}

	public String getLoginType(){
		return loginType;
	}

	public boolean getloginStatus(){
		return loginStatus;
	}
}
// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.io.*;
/*
	Session class creates a new session for each user
*/
public class Session implements java.io.Serializable{
	// Ryan: Should this really be publicly visible?

	// FIXED: Made the variable private
	private String loginType;
	//boolean loginStatus;
	public Session(String loginType){
		this.loginType=loginType;
		//this.loginStatus=loginStatus;
	}

	//returns login type
	public String getLoginType(){
		return loginType;
	}
}
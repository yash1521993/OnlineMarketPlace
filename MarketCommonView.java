// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.util.Scanner;

/*
	MarketCommonView - this provides a common user interface for
	both admin and login. These details are captured .
*/
public class MarketCommonView{
	//captures user type who's trying to access market app
	public String getLoginType(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Enter 'Admin' for Administrator login without quotes");
		System.out.println("Enter 'Customer' for Customer login without quotes");
		System.out.println("------------Enter one from above------------------");
		String loginType = scanner.nextLine();
		return loginType;
	}
	
	//capturing user id
	public String getInputLoginId(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Login ID:");
		String inputLoginId = scanner.nextLine();
		return inputLoginId;
	}
	//capturing password
	public String getInputLoginPwd(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Password:");
		String inputLoginPwd = scanner.nextLine();
		return inputLoginPwd;
	}
}
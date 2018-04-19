// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.util.Scanner;
import java.util.ArrayList;
/*
	MarketCommonView - this provides a common user interface for
	both admin and login. These details are captured .
*/
public class MarketCommonView{

	//allows login or registration
	public String userAction(){
		Scanner scanner = new Scanner(System.in);
		String userAction = "";
		System.out.println("~~~~~~~~~~~~~Login/Registration~~~~~~~~~~~~~~~");
		System.out.println("Enter 'Login' without quotes for Customer/Admin Login");
		System.out.println("Enter 'Register' without quotes for registration a new Customer account");
		System.out.println("--------------Enter one from above------------------");
		userAction = scanner.nextLine();
		return userAction;
	}

	//captures user type who's trying to register with market app
	public String getRegType(){
		Scanner scanner = new Scanner(System.in);
		String regType = "";
		System.out.println("~~~~~~~~~~~~~Registration~~~~~~~~~~~~~~~");
		System.out.println("Enter 'Admin' without quotes for Admin registration");
		System.out.println("Enter 'Customer' without quotes for Customer registration");
		System.out.println("--------------Enter one from above------------------");
		regType = scanner.nextLine();
		return regType;
	}

	//captures user's first name details
	public String getFirstName(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter First Name:");
		String firstName = scanner.nextLine();
		return firstName;
	}

	//captures user's last name details
	public String getLastName(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Last Name:");
		String lastName = scanner.nextLine();
		return lastName;
	}

	//captures user type who's trying to access market app
	public String getLoginType(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("~~~~~~~~~~~~~~~~~~~~Login~~~~~~~~~~~~~~~~~~~~~~~");
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
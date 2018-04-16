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

	//captures user's registration details
	public ArrayList<String> getRegDetails(){
		ArrayList<String> userDetails=new ArrayList<String>();

		Scanner scanner = new Scanner(System.in);
		System.out.println("~~~~~~~~~~~~~Registration~~~~~~~~~~~~~~~");
		System.out.println("Enter 'Admin' without quotes for Admin registration");
		System.out.println("Enter 'Customer' without quotes for Customer registration");
		System.out.println("--------------Enter one from above------------------");
		String regType = scanner.nextLine();
		System.out.println("Enter First Name:");
		String firstName = scanner.nextLine();
		System.out.println("Enter Last Name:");
		String lastName = scanner.nextLine();
		System.out.println("Enter Login ID:");
		String inputLoginId = scanner.nextLine();
		System.out.println("Enter Password:");
		String inputLoginPwd = scanner.nextLine();

		userDetails.add(regType);
		userDetails.add(firstName);
		userDetails.add(lastName);
		userDetails.add(inputLoginId);
		userDetails.add(inputLoginPwd);
		return(userDetails);
		//return loginType;
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
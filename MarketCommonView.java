// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.util.Scanner;

public class MarketCommonView{

	public String getLoginType(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter 'Admin' for Administrator login without quotes");
		System.out.println("Enter 'Customer' for Customer login without quotes");
		System.out.println("--------Enter one from above---------");
		String loginType = scanner.nextLine();
		
		
		return loginType;
	}
	
	public String getInputLoginId(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Login ID:");
		String inputLoginId = scanner.nextLine();
		return inputLoginId;
	}
	
	public String getInputLoginPwd(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Password:");
		String inputLoginPwd = scanner.nextLine();
		return inputLoginPwd;
	}
}
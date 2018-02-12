// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.util.Scanner;

public class MarketCommonView{

	public void validateLogin(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter 'Admin' for Administrator login without quotes");
		System.out.println("Enter 'Customer' for Customer login without quotes");
		System.out.println("--------Enter one from above---------");
		
		String loginType = scanner.nextLine();
		
		// instantiating frontController class			
		MarketFrontController frontController = new MarketFrontController();
		
		// calling respective views either admin or customer
		frontController.dispatchRequest(loginType);
		scanner.close();
	}
}
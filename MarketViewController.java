// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.rmi.Naming;
// Ryan: Do you need everything in this package?

// FIXED: imported only required package
import java.util.ArrayList;

/*
	MarketViewController acts as a client controller and provides communication
	between client and server side controller
*/
public class MarketViewController{
	// Ryan: Should these really be publicly visible?

	// FIXED: Made the variable private
	private static OnlineMarket marketApp;
	private boolean loginStatus;
	private Session session;
	private ArrayList browseItems,viewCart,viewCustomers;
	private String purchaseItems,addItems,userDetails,addUsers,addItemsToCart,updateItems,removeItem,removeCustomer;
	private MarketCommonView marketView=new MarketCommonView();
	private static String firstName,lastName,loginId,password;

	//captures user entered info
	public void getRegInfo(){
		System.out.println("----->> New Customer Registration <<----");
		firstName=marketView.getFirstName();
		lastName=marketView.getLastName();
		loginId=marketView.getInputLoginId();
		password=marketView.getInputLoginPwd();

		//exception handling block
		try{
			userDetails=marketApp.registerCustomer(firstName,lastName,loginId,password);
			System.out.println("----- "+userDetails+" ----");
		}
		catch(Exception e){
			System.out.println("Online Market App Exception: " +e.getMessage());
		}
	}

	//validateUserLogin method calls interface which further 
	//communicates with Server side controller and model
	public boolean validateUserLogin(String loginType){
		//Object creation for generic view
		
		String inputId=marketView.getInputLoginId();
		String inputPwd=marketView.getInputLoginPwd();
		
		try{
			if(loginType.equalsIgnoreCase("admin")) {
				loginStatus=marketApp.validateAdminLogin(inputId,inputPwd,loginType);
				System.out.println("Login Status:" + loginStatus);
			}
			else if(loginType.equalsIgnoreCase("customer")){
				loginStatus=marketApp.validateCustomerLogin(inputId,inputPwd,loginType);
				System.out.println("Login Status:" + loginStatus);
			}
			else
				System.out.println(">>>>>Input should be only from either of the above roles<<<<<<");
		}
		catch(Exception e){
			System.out.println("Online Market App Exception: " +e.getMessage());
		}
		return loginStatus;
			
	}

	//this method calls createSession() via RMI interface
	public Session createSession(String userType){
		try{
			session= marketApp.createSession(userType);
		}	
		catch(Exception e){
			System.out.println("Online Market App- Session Exception: " +e.getMessage());
		}
		return session;
	}


	//browseItems method which calls browseItems from interface
	public ArrayList browseItems(Session session){
		try{
			browseItems= marketApp.browseItems(session);
		}
		catch(Exception e){
			System.out.println("Online Market App-Browse Items Exception: " +e.getMessage());
		}
		return browseItems;
	}

	//addItemsToCart method which calls addItemsToCart from interface
	public String addItemsToCart(Session session,int itemId,int itemQuant){
		try{
			//send user input item id and quantity to query database
			addItemsToCart= marketApp.addItemsToCart(session,itemId,itemQuant);
		}
		catch(Exception e){
			System.out.println("Online Market App-Purchase Items Exception: " +e.getMessage());
		}
		return addItemsToCart;
	}

	//viewCart method which calls viewCart from interface
	public ArrayList viewCart(Session session){
		try{
			viewCart= marketApp.viewCart(session);
		}
		catch(Exception e){
			System.out.println("Online Market App-Browse Items Exception: " +e.getMessage());
		}
		return viewCart;
	}

	//viewCustomers method which calls viewCustomers from interface
	public ArrayList viewCustomers(Session session){
		try{
			viewCustomers= marketApp.viewCustomers(session);
		}
		catch(Exception e){
			System.out.println("Online Market App-Browse Customer Exception: " +e.getMessage());
		}
		return viewCustomers;
	}

	//purchaseItems method which calls purchaseItems from interface
	public String purchaseItems(Session session){
		try{
			//send user input item id and quantity to query database
			purchaseItems= marketApp.purchaseItems(session);
		}
		catch(Exception e){
			System.out.println("Online Market App-Purchase Items Exception: " +e.getMessage());
		}
		return purchaseItems;
	}

	//purchaseItems method which calls purchaseItems from interface
	public String addItems(Session session,int itemId,String itemName,String itemType,String itemPrice, int itemQuant){
		
		try{
			//send user input to store them to database
			addItems= marketApp.addItems(session,itemId,itemName,itemType,itemPrice,itemQuant);
		}
		catch(Exception e){
			System.out.println("Online Market App-Purchase Items Exception: " +e.getMessage());
		}
		return addItems;
	}


	//addUsers method which calls addUsers from interface
	public String addUsers(Session session,String accType,String firstName,String lastName,String inputLoginId,String inputLoginPwd){
		
		try{
			//send user input to store them to database
			addUsers= marketApp.addUsers(session,accType,firstName,lastName,inputLoginId,inputLoginPwd);
		}
		catch(Exception e){
			System.out.println("Online Market App- Add Users Exception: " +e.getMessage());
		}
		return addUsers;
	}

	//updateItems method which calls updateItems from interface
	public String updateItems(Session session,int itemId,String itemAttribute,String attributeValue){
		
		try{
			//send user input to store them to database
			updateItems= marketApp.updateItems(session,itemId,itemAttribute,attributeValue);
		}
		catch(Exception e){
			System.out.println("Online Market App-Update Items Exception: " +e.getMessage());
		}
		return updateItems;
	}

	//removeItem method which calls removeItem from interface
	public String removeItem(Session session,int itemId){
		
		try{
			//send user input to store them to database
			removeItem= marketApp.removeItem(session,itemId);
		}
		catch(Exception e){
			System.out.println("Online Market App-Remove Items Exception: " +e.getMessage());
		}
		return removeItem;
	}


	//removeCustomer method which calls removeCustomer from interface
	public String removeCustomer(Session session,int customerId){
		
		try{
			//send user input to store them to database
			removeCustomer= marketApp.removeCustomer(session,customerId);
		}
		catch(Exception e){
			System.out.println("Online Market App-Remove Customer Exception: " +e.getMessage());
		}
		return removeCustomer;
	}

	//main method
	public static void main(String args[]){
		// creates a security manager for RMI
		System.setSecurityManager(new SecurityManager( ));
		//variable declaration
		int regId;
		MarketViewController viewController = new MarketViewController();
		String register,userAct = "";
		
		//try and catch block for exception handling
		try{
			MarketFrontController frontController = new MarketFrontController();
			// Locate Online Market Server
			String name = "//10.234.136.55:5432/OnlineMarketServer";
			marketApp = (OnlineMarket) Naming.lookup(name);
			
			//market common view instance
			MarketCommonView marketView=new MarketCommonView();
			//loop for continuous input i.e., either login or register
			while(true){
				userAct = marketView.userAction();
				//registers or login a customer/admin based on user input	
				if(userAct.equalsIgnoreCase("register")){
					viewController.getRegInfo();
				}

				if(userAct.equalsIgnoreCase("login")){
					//System.out.println("Hi this is login");
					frontController.dispatchRequest(marketView.getLoginType());					
				}
			}
		} 
		catch(Exception e){
			System.out.println("Online Market App - View Controller Exception: " +e.getMessage());
		}
		
		System.exit(0);
	}
}
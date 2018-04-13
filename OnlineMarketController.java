// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
// Ryan: Do you need everything in this package?
import java.util.*;
//import proxy 
import java.lang.reflect.Proxy;


/*
*	Controller class which acts as intermediate in between client controller and model
*	Implements OnlineMarket interface and defines all the functions
*
*/
public class OnlineMarketController extends UnicastRemoteObject implements OnlineMarket{
	private String name;
	private int regId = 0;

	//constructor for controller
	public OnlineMarketController(String name) throws RemoteException {
		super(); 
		this.name = name;
	}

	//this method returns a registration id for a customer
	public synchronized int regId() throws RemoteException {
		regId++; 
		return regId;
	}
	
	
	//this method register a user
	public String registerCustomer() throws RemoteException{
		OnlineMarketModel modelObj= new OnlineMarketModel();
		return modelObj.registerCustomer();
	}
	

	//OnlineMarketModel object calls for validateLogin function which has business logic
	@Override
	public boolean validateAdminLogin(String inputId,String inputPwd,String loginType) throws RemoteException{
		OnlineMarketModel modelObj= new OnlineMarketModel();
		return modelObj.validateLogin(inputId,inputPwd,loginType);
	
	}

	//overridden method to validate customer login
	@Override
	public boolean validateCustomerLogin(String inputId,String inputPwd,String loginType) throws RemoteException{
		OnlineMarketModel modelObj= new OnlineMarketModel();
		return modelObj.validateLogin(inputId,inputPwd,loginType);
	
	}

	//overridden sync method to browse items
	@Override
	public synchronized ArrayList browseItems(Session session){
		OnlineMarketModel modelObj= new OnlineMarketModel();
		return modelObj.browseItems();
	}

	////overridden sync method to purchase items
	@Override
	public synchronized String purchaseItems(Session session,int itemId,int itemQuantity){
		OnlineMarketModel modelObj= new OnlineMarketModel();
		return modelObj.purchaseItems(itemId,itemQuantity);
	}

	//overridden sync method to add items
	@Override
	public synchronized String addItems(Session session,int itemId,String itemName,String itemPrice, int itemQuant){
		OnlineMarketModel modelObj= new OnlineMarketModel();
		return modelObj.addItems(itemId,itemName,itemPrice,itemQuant);
	}

	//overridden sync method to create session
	@Override
	public Session createSession(String userType) {
		Session session = new Session(userType);
		return session;
	}

	//Added main method
	public static void main(String args[]) {
		// Set the RMI Security Manager...
		System.setSecurityManager(new SecurityManager());
		//try and catch block for exception handling
		try {
			System.out.println("You are now entering Online Market Place");
			
			// Connection string to Online Market Server
			String name = "//10.234.136.55:5432/OnlineMarketServer";
			
			//creating a proxy with initializing it to a new AuthenticateUserInvocationHandler
			OnlineMarket createProxy = (OnlineMarket)Proxy.newProxyInstance(OnlineMarket.class.getClassLoader(),
	                new Class<?>[] {OnlineMarket.class}, new AuthenticateUserInvocationHandler(new OnlineMarketController(name)));

			// Create a new instance of a Online market server.
			//OnlineMarketController marketCntrlr = new OnlineMarketController(name);
			System.out.println("Reaching server:" + name);
			
			// rebind binds the server and RMI Service
			Naming.rebind(name, createProxy);
			
			System.out.println("Interface is Ready!You can register, login and shop");
		} 
		catch (Exception e){
			System.out.println("Online Market Server Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
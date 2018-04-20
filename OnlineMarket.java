// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

// Ryan: Do you need everything in this package?

// FIXED: imported only required package
import java.util.ArrayList;
/**
 *	Below is the interface declaration OnlineMarket which will be implemented
 *	by OnlineMarketController.
 */
public interface OnlineMarket extends java.rmi.Remote {
	//interface should extend from Remote super class
	//this method returns an Id for each customer
	int regId() throws java.rmi.RemoteException;

	//this method registers a new customer
	String registerCustomer(String firstName,String lastName,String userName,String password) throws java.rmi.RemoteException;
	
	//this method verifies for a valid admin based on role
	//@AnnotateInterface("admin")
	boolean validateAdminLogin(String inputId,String inputPwd,String loginType) throws java.rmi.RemoteException;

	//role based login verification for customer
	//@AnnotateInterface("customer")
	boolean validateCustomerLogin(String inputId,String inputPwd,String loginType) throws java.rmi.RemoteException;

	//role based access for customer to browse items
	//@AnnotateInterface("customer")
	@SuppressWarnings("unchecked")
	public ArrayList browseItems(Session session) throws java.rmi.RemoteException;

	//role based access for customer to purchase items
	@AnnotateInterface("customer")
	public String addItemsToCart(Session session,int itemId,int itemQuantity) throws java.rmi.RemoteException;

	//role based access for customer to purchase items
	@AnnotateInterface("customer")
	public String purchaseItems(Session session,int itemId,int itemQuantity) throws java.rmi.RemoteException;

	//role based access for admin to add items
	@AnnotateInterface("admin")
	public String addItems(Session session,int itemId,String itemName,String itemType,String itemPrice, int itemQuantity) throws java.rmi.RemoteException;

	//role based access for admin to add Users
	@AnnotateInterface("admin")
	public String addUsers(Session session,String accType,String firstName,String lastName,String inputLoginId,String inputLoginPwd) throws java.rmi.RemoteException;

	//createSession() creates and returns a session based on userType
	public Session createSession(String userType) throws java.rmi.RemoteException;

}

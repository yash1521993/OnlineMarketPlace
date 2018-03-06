// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/**
 *	Below is the interface declaration OnlineMarket which will be implemented
 *	by OnlineMarketController.
 */
public interface OnlineMarket extends java.rmi.Remote {
	//interface should extend from Remote super class
	//this method returns an Id for each customer
	int regId() throws java.rmi.RemoteException;
	//this method registers a new customer
	String registerCustomer() throws java.rmi.RemoteException;
	
	//this method verifies for a valid admin based on role
	@AnnotateInterface("admin")
	boolean validateAdminLogin(Session session,String inputId,String inputPwd,String loginType) throws java.rmi.RemoteException;

	//role based login verification for customer
	@AnnotateInterface("customer")
	boolean validateCustomerLogin(Session session,String inputId,String inputPwd,String loginType) throws java.rmi.RemoteException;

	//role based access for customer to browse items
	@AnnotateInterface("customer")
	public String browseItems(Session session) throws java.rmi.RemoteException;

	@AnnotateInterface("customer")
	public String purchaseItems(Session session) throws java.rmi.RemoteException;

	@AnnotateInterface("admin")
	public String addItems(Session session) throws java.rmi.RemoteException;
	
	public Session createSession(String userType) throws java.rmi.RemoteException;

}

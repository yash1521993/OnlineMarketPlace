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
	//this method verifies for a valid user
	public Session validateLogin(String inputId,String inputPwd,String loginType) throws java.rmi.RemoteException;
	
		
}

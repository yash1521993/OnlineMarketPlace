// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/**
 *	Below is the interface declaration OnlineMarket which will be implemented
 *	by OnlineMarketModelController.
 */
public interface OnlineMarket extends java.rmi.Remote {
	//interface should extend from Remote super class
	//this method returns an Id for each customer
	int regId() throws java.rmi.RemoteException;
	//this method registers a new customer
	String registerCustomer() throws java.rmi.RemoteException;
	//admin related functions
	void loginToMarket() throws java.rmi.RemoteException;
	
	void addItemsToMarket() throws java.rmi.RemoteException;
	void removeItemsFromMarket() throws java.rmi.RemoteException;
	void updateItems() throws java.rmi.RemoteException;
	void viewMarketItems() throws java.rmi.RemoteException;
	void viewMarketCart() throws java.rmi.RemoteException;
	
}

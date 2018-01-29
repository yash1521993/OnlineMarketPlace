// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/**
 * This interface serves as the proxy between the BankServer and the
 * BankClient. The BankServer must implement this method.
 *
 */
public interface OnlineMarket extends java.rmi.Remote {
	/**
	 * Gets the Transaction ID number.
	 * 
	 * @return Transaction ID
	 * @throws java.rmi.RemoteException
	 */
	int get_id() throws java.rmi.RemoteException;
	String registerCustomer() throws java.rmi.RemoteException;
}

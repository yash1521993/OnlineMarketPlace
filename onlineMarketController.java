// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OnlineMarketController extends UnicastRemoteObject implements OnlineMarket{
	private String name;
		private int id = 0;

	public OnlineMarketController(String name) throws RemoteException {
		super(); 
		this.name = name;
	}

		/**
	 * Implemented remote method from Bank interface.
	 */
	public synchronized int get_id() throws RemoteException {
		id++; 
		return id;
	}
	
	
	public String registerCustomer() throws RemoteException{
		OnlineMarketModel modelObj= new OnlineMarketModel();
		return modelObj.registerCustomer();
	}
}
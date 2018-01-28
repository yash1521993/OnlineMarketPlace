// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * BankServer - Must implement any and all methods found in the Bank
 * interface. The variable 'name' must include the location where the
 * BankServer is going to be registered with RMI to run.
 */
public class onlineMarketServer extends UnicastRemoteObject implements onlineMarket {
	private int id = 0;
	private String name;

	public onlineMarketServer(String name) throws RemoteException {
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

	public static void main(String args[]) {
		// Set the RMI Security Manager...
		System.setSecurityManager(new SecurityManager());
		
		try {
			System.out.println("Creating a Bank Server!");
			
			// Location of BankServer
			String name = "//tesla.cs.iupui.edu:5555/home/yashkuru/OOAD/Assignment1/onlineMarketServer";
			
			// Create a new instance of a BankServer.
			onlineMarketServer bank = new onlineMarketServer(name);
			
			System.out.println("BankServer: binding it to name: " + name);
			
			// Binds the BankServer to the RMI Service.
			Naming.rebind(name, bank);
			
			System.out.println("Bank Server Ready!");
		} catch (Exception e){
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}



// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.rmi.Naming;

/**
 * BankClient - Value in the 'name' variable should be the location
 * of the BankServer.
 *
 */
public class onlineMarketClient {
	public static void main(String args[]){
		// RMI Security Manager
		System.setSecurityManager(new SecurityManager());
		
		int id;

		try{
			String name = "//tesla.cs.iupui.edu:5555/home/yashkuru/OOAD/Assignment1/onlineMarketServer";
			// Attempt to locate the BankServer...
			onlineMarket myBank = (onlineMarket) Naming.lookup(name);
			id = myBank.get_id();
			System.out.println("Transaction ID: " + id);	
		} catch(Exception e){
			System.out.println("BankClient Exception: " +
			e.getMessage());
			e.printStackTrace();
		}
		
		System.exit(0);
	}
}

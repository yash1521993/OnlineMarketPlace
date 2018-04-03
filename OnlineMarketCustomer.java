// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.util.*;
/*
*	OnlineMarketCustomer provides customer view
*	and implements methods from Customer interface
*/

public class OnlineMarketCustomer implements MarketCustomerInterface{

	
		private MarketViewController mvc=new MarketViewController();
		private String samp;
		private ArrayList browseItem;
		//overriding interface method
		@Override
		public void customerInfo(){
			System.out.println("------------------------------------");
			System.out.println("-Welcome to the Customer Home Page-");
			System.out.println("------------------------------------");
		}

		//member method- purchaseItems helps a customer to purchase items
		@Override
		public void purchaseItems(Session session){
			try{
				samp=mvc.purchaseItems(session);
				System.out.println(samp);
			}
			catch(Exception e){
				System.out.println("Online Market App Exception: " +e.getMessage());
				e.printStackTrace();
			}
			
		}

		//member method- browseItems helps a customer to browse items
		@Override
		public void browseItems(Session session){
			try{
				browseItem=mvc.browseItems(session);
				System.out.println("<---+++---Your shopping items list here----+++--->");
				System.out.println(browseItem);
			}
			catch(Exception e){
				System.out.println("Online Market App Exception: " +e.getMessage());
				e.printStackTrace();
			}
		}
}
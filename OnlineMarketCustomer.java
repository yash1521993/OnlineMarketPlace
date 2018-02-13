// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/*
*	OnlineMarketCustomer provides customer view
*	and implements methods from Customer interface
*/

public class OnlineMarketCustomer implements MarketCustomerInterface{
	//overriding interface method
	public void customerInfo(){
		System.out.println("Welcome to the Customer Home Page!");
	}
	//member method- purchaseItems helps a customer to purchase items
	public void purchaseItems(){
		System.out.println("Purchase your browsed items here");
	}
	//member method- browseItems helps a customer to browse items
	public void browseItems(){
		System.out.println("======Browse Market App to shop======");
		System.out.println("<-Your shopping items list here->");
	}

}
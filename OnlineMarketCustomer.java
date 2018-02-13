// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

public class OnlineMarketCustomer implements MarketCustomerInterface{
	//overriding interface method
	public void customerInfo(){
		System.out.println("Welcome to the Customer Home Page!");
	}
	//member method
	public void purchaseItems(){
		System.out.println("Purchase here");
	}
	//member method
	public void browseItems(){
		System.out.println("Browse here");
	}

}
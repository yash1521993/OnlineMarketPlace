// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/*
	MarketCustomerInterface has a customerInfo() method
	which will be implemented by its concrete classes
	Abstract factory pattern
*/
public interface MarketCustomerInterface{
	//customer related functions along with session object
	public void customerInfo();
	public void purchaseItems(Session session);
	public void browseItems(Session session);
}
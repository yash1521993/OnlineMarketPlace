// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/*
*	AddItemsToCart implements Customer command interface
	Also forwards a call to allow a customer to add items to cart
*/
public class AddItemsToCart implements CustomerCmdInterface{
	//object declaration
	private MarketCustomerInterface custCommand;
	// Ryan: Should this really be publicly visible?

	// FIXED: Made the variable private
	private Session session;
	//constructor initializing customer commands and session
	public AddItemsToCart(MarketCustomerInterface custCommand, Session session){
      this.custCommand = custCommand;
      this.session=session;
	}
	
	//calling addItemsToCart() method
	@Override
	public void customerTasks(){
		custCommand.addItemsToCart(session);
		
	}
}
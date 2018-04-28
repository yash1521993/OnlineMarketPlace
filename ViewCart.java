// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/*
*	ViewCart implements Customer command interface
	Also forwards a call to allow a customer to view items in cart
*/
public class ViewCart implements CustomerCmdInterface{
	//object declaration
	private MarketCustomerInterface custCommand;
	
	private Session session;
	//constructor initializing customer commands and session
	public ViewCart(MarketCustomerInterface custCommand, Session session){
      this.custCommand = custCommand;
      this.session=session;
	}
	
	//calling addItemsToCart() method
	@Override
	public void customerTasks(){
		custCommand.viewCart(session);
	}
}
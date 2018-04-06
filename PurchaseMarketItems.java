// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/*
*	PurchaseMarketItems implements from Customer command interface.
*/

public class PurchaseMarketItems implements CustomerCmdInterface{
	//object declaration
	private MarketCustomerInterface custCommand;
	// Ryan: Should this really be publicly visible?

	// FIXED: Made the variable private
	private Session session;
	//constructor initializing customer command and session object to purchase items
	public PurchaseMarketItems(MarketCustomerInterface custCommand,Session session){
      this.custCommand = custCommand;
      this.session=session;
	}
	
	//overridden method further calls purchaseItems() method
	@Override
	public void customerTasks(){
		custCommand.purchaseItems(session);
	}
}
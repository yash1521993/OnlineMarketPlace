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
	private MarketCustomerInterface custCommand;
	
	//constructor
	public PurchaseMarketItems(MarketCustomerInterface custCommand){
      this.custCommand = custCommand;
	}
	
	//overridden method further calls purchaseItems() method
	public void customerTasks(){
		custCommand.purchaseItems();
	}
}
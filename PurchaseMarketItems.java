// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

public class PurchaseMarketItems implements CustomerCmdInterface{
	private OnlineMarketCustomer custCommand;
	
	//constructor
	public PurchaseMarketItems(OnlineMarketCustomer custCommand){
      this.custCommand = custCommand;
	}
	
	public void customerTasks(){
		custCommand.purchaseItems();
	}
}
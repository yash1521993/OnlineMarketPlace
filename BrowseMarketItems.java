// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/*
*	BrowseMarketItems implements Customer command interface
	Also forwards a call to allow a customer to browse items
*/
public class BrowseMarketItems implements CustomerCmdInterface{
	//object declaration
	private OnlineMarketCustomer custCommand;
	
	//constructor
	public BrowseMarketItems(OnlineMarketCustomer custCommand){
      this.custCommand = custCommand;
	}
	
	//calling browseItems() method
	public void customerTasks(){
		custCommand.browseItems();
	}
}
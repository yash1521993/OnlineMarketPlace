// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

public class BrowseMarketItems implements CustomerCmdInterface{
	private OnlineMarketCustomer custCommand;
	
	//constructor
	public BrowseMarketItems(OnlineMarketCustomer custCommand){
      this.custCommand = custCommand;
	}
	
	public void customerTasks(){
		custCommand.browseItems();
	}
}
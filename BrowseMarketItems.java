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
	private MarketCustomerInterface custCommand;
	// Ryan: Should this really be publicly visible?

	// FIXED: Made the variable private
	private Session session;
	//constructor initalizing customer commands and session
	public BrowseMarketItems(MarketCustomerInterface custCommand, Session session){
      this.custCommand = custCommand;
      this.session=session;
	}
	
	//calling browseItems() method
	@Override
	public void customerTasks(){
		custCommand.browseItems(session);
		
	}
}
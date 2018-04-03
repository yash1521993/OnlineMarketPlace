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
public class BrowseAdminMarketItems implements AdminCmdInterface{
	//object declaration
	private MarketAdminInterface custCommand;
	private Session session;

	//constructor initalizing customer commands and session
	public BrowseAdminMarketItems(MarketAdminInterface custCommand, Session session){
      this.custCommand = custCommand;
      this.session=session;
	}
	
	//calling browseItems() method
	@Override
	public void adminTasks(){
		custCommand.browseItems(session);
		
	}
}
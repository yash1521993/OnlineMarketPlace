// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/*
*	BrowseAdminMarketItems implements admin command interface
	Also forwards a call to allow a admin to browse items
*/
public class BrowseAdminMarketItems implements AdminCmdInterface{
	//object declaration
	private MarketAdminInterface custCommand;
	private Session session;

	//constructor initializing customer commands and session
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
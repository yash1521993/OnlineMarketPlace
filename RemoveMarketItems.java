// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/*
*	RemoveMarketItems implements Admin command interface
	Also forwards a call to allow a admin to remove items
*/
public class RemoveMarketItems implements AdminCmdInterface{
	//object declaration
	private MarketAdminInterface adminCommand;
	private Session session;
	//constructor initializing session and admin commands
	public RemoveMarketItems(MarketAdminInterface adminCommand, Session session){
      this.adminCommand = adminCommand;
      this.session=session;
	}
	
	//calling removeItem() method
	@Override
	public void adminTasks(){
		adminCommand.removeItem(session);
		
	}
}
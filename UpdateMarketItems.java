// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/*
*	UpdateMarketItems implements Admin command interface
	Also forwards a call to allow a admin to update items
*/
public class UpdateMarketItems implements AdminCmdInterface{
	//object declaration
	private MarketAdminInterface adminCommand;
	private Session session;
	//constructor initializing session and admin commands
	public UpdateMarketItems(MarketAdminInterface adminCommand, Session session){
      this.adminCommand = adminCommand;
      this.session=session;
	}
	
	//calling updateItems() method
	@Override
	public void adminTasks(){
		adminCommand.updateItems(session);
		
	}
}
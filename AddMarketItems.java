// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/*
*	AddMarketItems implements Admin command interface
	Also forwards a call to allow a customer to add items
*/
public class AddMarketItems implements AdminCmdInterface{
	//object declaration
	private MarketAdminInterface adminCommand;
	// Ryan: Should this really be publicly visible?

	// FIXED: Made the variable private
	private Session session;
	//constructor initializing session and customer commands
	public AddMarketItems(MarketAdminInterface adminCommand, Session session){
      this.adminCommand = adminCommand;
      this.session=session;
	}
	
	//calling addItems() method
	@Override
	public void adminTasks(){
		adminCommand.addItems(session);
		
	}
}
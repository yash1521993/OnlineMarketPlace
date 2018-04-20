// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/*
*	DeleteCustomer implements Admin command interface
	Also forwards a call to allow a admin to remove customer
*/
public class DeleteCustomer implements AdminCmdInterface{
	//object declaration
	private MarketAdminInterface adminCommand;
	private Session session;
	//constructor initializing session and admin commands
	public DeleteCustomer(MarketAdminInterface adminCommand, Session session){
      this.adminCommand = adminCommand;
      this.session=session;
	}
	
	//calling removeCustomer() method
	@Override
	public void adminTasks(){
		adminCommand.removeCustomer(session);
		
	}
}
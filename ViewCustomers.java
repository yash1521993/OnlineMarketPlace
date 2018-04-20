// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/*
*	ViewCustomers implements Admin command interface
	Also forwards a call to allow a admin to view customer
*/
public class ViewCustomers implements AdminCmdInterface{
	//object declaration
	private MarketAdminInterface adminCommand;
	private Session session;
	//constructor initializing session and admin commands
	public ViewCustomers(MarketAdminInterface adminCommand, Session session){
      this.adminCommand = adminCommand;
      this.session=session;
	}
	
	//calling viewCustomers() method
	@Override
	public void adminTasks(){
		adminCommand.viewCustomers(session);
		
	}
}
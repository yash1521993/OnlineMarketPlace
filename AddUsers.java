// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/*
*	AddUsers implements Admin command interface
	Also forwards a call to allow a admin to add customer/admin
*/
public class AddUsers implements AdminCmdInterface{
	//object declaration
	private MarketAdminInterface custCommand;
	private Session session;

	//constructor initializing session and customer commands
	public AddUsers(MarketAdminInterface custCommand, Session session){
      this.custCommand = custCommand;
      this.session=session;
	}
	
	//calling addUsers() method
	@Override
	public void adminTasks(){
		custCommand.addUsers(session);
		
	}
}
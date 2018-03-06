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
	private MarketAdminInterface custCommand;
	Session session;
	//constructor
	public AddMarketItems(MarketAdminInterface custCommand, Session session){
      this.custCommand = custCommand;
      this.session=session;
	}
	
	//calling browseItems() method
	@Override
	public void adminTasks(){
		custCommand.addItems(session);
		
	}
}
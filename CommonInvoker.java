// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.util.ArrayList;
import java.util.List;

/*
* Acts as a broker class which maintains action queue for both admin and customer
*/
public class CommonInvoker{

	/*
		Customer related invoker module
	*/

	//taskList stores customer commands in a queue
	private List<CustomerCmdInterface> taskList = new ArrayList<CustomerCmdInterface>(); 

	//add commands to the list
	public void tasksList(CustomerCmdInterface custCommands){
	  taskList.add(custCommands);
	}

	//executes the added commands
	public void executeCustomerTasks(){
	  for (CustomerCmdInterface custCommands: taskList) {
		 custCommands.customerTasks();
	  }
	  taskList.clear();
	}

	/*
		Admin related invoker module
	*/

	//taskListAdmin stores admin commands in a queue
	private List<AdminCmdInterface> taskListAdmin = new ArrayList<AdminCmdInterface>(); 

	//add commands to the list
	public void tasksListAdmin(AdminCmdInterface adminCommands){
	  taskListAdmin.add(adminCommands);
	}

	//executes the added commands
	public void executeAdminTasks(){
	  for (AdminCmdInterface adminCommands: taskListAdmin) {
		 adminCommands.adminTasks();
	  }
	  taskListAdmin.clear();
	}


}
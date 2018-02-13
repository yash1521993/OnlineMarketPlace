// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

import java.util.ArrayList;
import java.util.List;

/*
* Acts as a broker class which maintains action queue
*/
public class CustomerInvoker{
	private List<CustomerCmdInterface> taskList = new ArrayList<CustomerCmdInterface>(); 

	public void tasksList(CustomerCmdInterface custCommands){
	  taskList.add(custCommands);
	}

	public void executeCustomerTasks(){

	  for (CustomerCmdInterface custCommands: taskList) {
		 custCommands.customerTasks();
	  }
	  taskList.clear();
	}
}
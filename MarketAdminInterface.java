// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/*
	Admin interface containing adminINfo
*/
public interface MarketAdminInterface{
	//partially implemented admin related methods
	public void adminInfo();
	public void addItems(Session session);
	public void updateItems(Session session);
	public void browseItems(Session session);
	public void addUsers(Session session);
	public void removeItem(Session session);
	public void removeCustomer(Session session);
}
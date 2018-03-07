// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/*
	MarketAdminFactory inherits MarketAbstractFactory and overrides
	adminInfo method. Creates new admin view
*/
public class MarketAdminFactory extends MarketAbstractFactory {
	//overriding
	@Override
	public MarketAdminInterface getAdminInfo(String loginType){
	   
      if(loginType.equalsIgnoreCase("Admin")){
         return new OnlineMarketAdmin();
	  }
	  return null;
	}
	
	//overriding
	@Override
	public MarketCustomerInterface getCustomerInfo(String loginType){
		return null;
	}
}
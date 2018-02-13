// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

public class MarketAdminFactory extends MarketAbstractFactory {
	//overriding
	public MarketAdminInterface getAdminInfo(String loginType){
	   
      if(loginType.equalsIgnoreCase("Admin")){
         return new OnlineMarketAdmin();
	  }
	  return null;
	}
	
	//overriding
	public MarketCustomerInterface getCustomerInfo(String loginType){
		return null;
	}
}
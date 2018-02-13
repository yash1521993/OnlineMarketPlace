// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

public class MarketCustomerFactory extends MarketAbstractFactory {
	//overriding
	public MarketCustomerInterface getCustomerInfo(String loginType){
	   
      if(loginType.equalsIgnoreCase("Customer")){
         return new OnlineMarketCustomer();
	  }
	  return null;
	}
	
	//overriding
	public MarketAdminInterface getAdminInfo(String loginType){
		return null;
	}
}
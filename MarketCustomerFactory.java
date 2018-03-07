// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/*
	MarketCustomerFactory creates customer factiries extending 
	MarketAbstractFactory class
*/
public class MarketCustomerFactory extends MarketAbstractFactory {
	//overriding
	@Override
	public MarketCustomerInterface getCustomerInfo(String loginType){
	  
      if(loginType.equalsIgnoreCase("Customer")){
         return new OnlineMarketCustomer();
	  }
	  return null;
	}
	
	//overriding
	@Override
	public MarketAdminInterface getAdminInfo(String loginType){
		return null;
	}
}
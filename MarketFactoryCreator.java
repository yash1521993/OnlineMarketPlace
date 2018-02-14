// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/*
*	MarketFactoryCreator creates a factory based on type of account
	admin factory or customer factory
*/
public class MarketFactoryCreator {
	public static MarketAbstractFactory getLoginType(String account){
   
      if(account.equalsIgnoreCase("AdminFact")){
         return new MarketAdminFactory();
         
      }
	  else if(account.equalsIgnoreCase("CustFact")){
         return new MarketCustomerFactory();
      }
      
      return null;
   }
}
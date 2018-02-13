// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

public abstract class MarketAbstractFactory {
   abstract MarketAdminInterface getAdminInfo(String loginType);
   abstract MarketCustomerInterface getCustomerInfo(String loginType) ;
}
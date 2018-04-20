// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

// Ryan: Do you need everything in this package?

// FIXED: imported only required package
import java.util.ArrayList;
import java.util.Scanner;
/*
*	OnlineMarketCustomer provides customer view
*	and implements methods from Customer interface
*/

public class OnlineMarketCustomer implements MarketCustomerInterface{

	private MarketViewController mvc=new MarketViewController();
	private String samp;
	private ArrayList browseItem,cartItem;
	//overriding interface method
	@Override
	public void customerInfo(){
		System.out.println("------------------------------------");
		System.out.println("-Welcome to the Customer Home Page-");
		System.out.println("------------------------------------");
	}

	//member method- purchaseItems helps a customer to purchase items
	@Override
	public void addItemsToCart(Session session){
		try{
			System.out.print("+++++++++++Add items to cart here+++++++++++\n");
			//scanner class allows the admin to enter his/her input
			Scanner scanner = new Scanner(System.in);
			//read input item name and quantity
			
			System.out.print("Enter Item Id of the above Items you wish: ");
			int itemId = scanner.nextInt();

			System.out.print("Enter Item Quantity to be purchased: ");
			int itemQuantity = scanner.nextInt();

			samp=mvc.addItemsToCart(session,itemId,itemQuantity);
			//System.out.println("ItemId"+"  "+"ItemName"+"  "+"ItemPrice"+"  "+"ItemQuantity");
			System.out.println(samp);
		}
		catch(Exception e){
			System.out.println("Online Market App Exception: " +e.getMessage());
		}
		
	}

	//member method- purchaseItems helps a customer to purchase items
	@Override
	public void purchaseItems(Session session){
		try{
			System.out.print("+++++++++++Check out items here+++++++++++\n");
			//scanner class allows the admin to enter his/her input
			Scanner scanner = new Scanner(System.in);
			//read input item name and quantity
			
			System.out.print("Enter Item Id to check out: ");
			int itemId = scanner.nextInt();

			samp=mvc.purchaseItems(session,itemId);
			//System.out.println("ItemId"+"  "+"ItemName"+"  "+"ItemPrice"+"  "+"ItemQuantity");
			System.out.println(samp);
		}
		catch(Exception e){
			System.out.println("Online Market App Exception: " +e.getMessage());
		}
		
	}

	//member method- browseItems helps a customer to browse items
	@Override
	public void browseItems(Session session){
		try{
			browseItem=mvc.browseItems(session);
			//displaying items from database
			System.out.println("<---+++---Your shopping items list here----+++--->");
			System.out.println("ItemId"+"  "+"ItemName"+"  "+"ItemPrice"+"  "+"ItemQuantity");
			for(int i = 0; i < browseItem.size(); i++) {
	            System.out.println(browseItem.get(i));
	        }
		}
		catch(Exception e){
			System.out.println("Online Market App Exception: " +e.getMessage());
		}
	}

	//member method- viewCart helps a customer to view cart items
	@Override
	public void viewCart(Session session){
		try{
			cartItem=mvc.viewCart(session);
			//displaying items from cart db
			if(cartItem.isEmpty()){
				System.out.println("<---+++---Your Cart is Empty----+++--->");
			}
			//if cart is not null, display items
			else{
				System.out.println("<---+++---Your Cart items list here----+++--->");
				System.out.println("CartId"+"	"+"ItemId"+"	"+"ItemQuantity");
				for(int i = 0; i < cartItem.size(); i++) {
		            System.out.println(cartItem.get(i));
		        }
			}
		}
		catch(Exception e){
			System.out.println("Online Market App Exception - View Cart: " +e.getMessage());
		}
	}
}
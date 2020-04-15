package ctrl;

import javax.servlet.http.HttpSession;

import model.ShoppingCart;

/**
 * 
 * @author alanyork
 *
 */
public class SessionManagement {
	private static final String MEZZO_USERNAME = "MezzoUsername";
	private static final String MEZZO_CART = "MezzoCart";
	
	public static void bindUser(HttpSession session, String username) {
		session.setAttribute(MEZZO_USERNAME, username);
	}
	
	public static void unbindUser(HttpSession session) {
		session.removeAttribute(MEZZO_USERNAME);
		session.invalidate();
	}
	
	public static String getBoundUsername(HttpSession session) {
		return (String) session.getAttribute(MEZZO_USERNAME);
	}
	
	public static void bindCart(HttpSession session, ShoppingCart cart)
	{
		session.setAttribute(MEZZO_CART, cart);
	}
	
	public static ShoppingCart getCart(HttpSession session)
	{
		return (ShoppingCart) session.getAttribute(MEZZO_CART);
	}
}

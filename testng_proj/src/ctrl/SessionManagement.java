package ctrl;

import javax.servlet.http.HttpSession;

import model.ShoppingCart;

/**
 * 
 * Utility class for the management of session storage data
 * 
 * @author alanyork
 *
 */
public class SessionManagement {
	private static final String MEZZO_USERNAME = "MezzoUsername";
	private static final String MEZZO_CART = "MezzoCart";
	private static final String FAIL_CTR = "failCounter";

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

	/**
	 * Binds a new cart to the session
	 * @param session
	 * @throws ClassNotFoundException
	 */
	public static void bindCart(HttpSession session) throws ClassNotFoundException {
		ShoppingCart cart = new ShoppingCart();
		session.setAttribute(MEZZO_CART, cart);
	}

	public static void bindCart(HttpSession session, ShoppingCart cart) {
		session.setAttribute(MEZZO_CART, cart);
	}

	public static void unbindCart(HttpSession session) {
		session.removeAttribute(MEZZO_CART);
	}

	public static void replaceCart(HttpSession session, ShoppingCart cart) {
		SessionManagement.unbindCart(session);
		SessionManagement.bindCart(session,cart);
	}
	
	public static boolean cartBound(HttpSession session) {
		return session.getAttribute(MEZZO_CART) != null;
	}

	/**
	 * 
	 * @param session
	 * @return The ShoppingCart bound to the session. Binds a cart if no cart bound.
	 * @throws ClassNotFoundException
	 */
	public static ShoppingCart getCart(HttpSession session) throws ClassNotFoundException {
		if (!SessionManagement.cartBound(session)) {
			SessionManagement.bindCart(session);
		}
		return (ShoppingCart) session.getAttribute(MEZZO_CART);
	}
	
	public static int getFailCounter(HttpSession session) {
		return (int) session.getAttribute(FAIL_CTR);		
	}
	
	public static void setFailCounter(HttpSession session, int value) {
		session.setAttribute(FAIL_CTR, value);		
	}
}

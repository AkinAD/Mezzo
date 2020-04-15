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
}

package filter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.Album;
import bean.CartItemBean;
import ctrl.SessionManagement;
import model.MusicStore;
import model.ShoppingCart;

/**
 * Servlet Filter implementation class CartFilter
 * 
 * @author akinad1
 */
@WebFilter({ "/*" })
public class CartFilter implements Filter {
	private static final String CART_TOTAL = "cartTotal";
	private static final String CART_TOTAL_QTY = "cartTotalQty";
	private static final String CART_ITEMS = "cartItems";
	
	private static final String PARAM_QUANTITY = "quantity";
	private static final String PARAM_DELETE = "delete";
	private static final String PARAM_ADD_TO_CART = "addToCart";
	private static final String PARAM_ADD_QUANTITY = "addQuantity";

	private static final String REGISTER_ENDPOINT2 = "/Register";
	private static final String REGISTER_ENDPOINT1 = "/register";
	private static final String LOGIN_ENDPOINT2 = "/Login";
	private static final String LOGIN_ENDPOINT1 = "/login";


	private MusicStore musicStore;

	/**
	 * Default constructor.
	 */
	public CartFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * Every path except those blacklisted have the following parameters:
	 * <ul>
	 * <li>addToCart - The aid of an item to be added to the cart</li>
	 * <li>delete - overrides quantity with 0</li>
	 * <li>addQuantity - increases the quantity, overrides quantity</li>
	 * <li>quantity - quantity of the item specified by addToCart</li>	 * 
	 * </ul>
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String[] pathBlacklist = { LOGIN_ENDPOINT1, LOGIN_ENDPOINT2, REGISTER_ENDPOINT1, REGISTER_ENDPOINT2 };

		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpSession session = httpReq.getSession();
		ShoppingCart cart;
		try {
			cart = SessionManagement.getCart(session);
		} catch (ClassNotFoundException e) {
			throw new ServletException(); // Probably our fault
		}

		if (!Arrays.asList(pathBlacklist).contains(httpReq.getServletPath())) {
			// Update Cart
			String paramAddToCart = httpReq.getParameter(PARAM_ADD_TO_CART);
			String paramDelete = httpReq.getParameter(PARAM_DELETE);
			String paramQuantity = httpReq.getParameter(PARAM_QUANTITY);
			String paramAddQuantity = httpReq.getParameter(PARAM_ADD_QUANTITY);

			Integer changeAid;
			Integer changeQty;

			
			try {
				System.out.println("CartFilter: Cart updating");
				changeAid = Integer.parseInt(paramAddToCart);
				if (paramDelete != null) {
					// Change is removal
					changeQty = 0;
				} else if (paramAddQuantity != null) {
					// Change is addition of addQuantity
					changeQty = Integer.parseInt(paramAddQuantity);
					if (cart.getAlbums().containsKey(changeAid)) {
						changeQty += cart.getAlbums().get(changeAid);
					}
				} else if (paramQuantity == null) {
					// Change is increment of 1
					changeQty = 1;
				} else {
					// Change to specified quantity
					changeQty = Integer.parseInt(paramQuantity);
					if (changeQty < 0) {
						throw new IllegalArgumentException();
					}
				}
				cart.updateQuantity(changeAid, changeQty);
			} catch (Exception e) {
				// Failed to update cart
				//e.printStackTrace();
			}
		}

		// Pass cart data to be displayed by the page
		List<CartItemBean> cartItems;
		try {
			cartItems = new LinkedList<CartItemBean>(cart.getCartItemBeans().values());
		} catch (SQLException e) {
			throw new ServletException();
		}

		int totalQty = cart.getAlbumCount();
		float cartTotal = cart.getTotalPrice();
		
		request.setAttribute(CART_ITEMS, cartItems);
		request.setAttribute(CART_TOTAL_QTY, totalQty);
		request.setAttribute(CART_TOTAL, cartTotal);

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		try {
			musicStore = MusicStore.getInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ServletException();
		}
	}

}

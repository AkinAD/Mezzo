package filter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
	private MusicStore MS = null;

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
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpSession session = httpReq.getSession();
		ShoppingCart cart = null;
		if (SessionManagement.getCart(session) != null) {
			cart = SessionManagement.getCart(session);
		}
		else
		{
			try {
				SessionManagement.bindCart(session, new ShoppingCart());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		cart = SessionManagement.getCart(session);

		MS = (MusicStore) httpReq.getServletContext().getAttribute("MS");
		Map<String, Album> data = new HashMap<String, Album>();
		Map<String, List<String>> out = new HashMap<String, List<String>>();
		session.setAttribute("cartCount", cart.getAlbumCount());

	//	if(httpReq.getServletPath().equals("/cart") )
		//{			
					
			if(request.getParameter("addToCart") != null )
			{	
				int aid = Integer.parseInt(request.getParameter("addToCart"));
				if(request.getParameter("quantity") != null)
				{
					int quantity = Integer.parseInt(request.getParameter("quantity"));
					cart.addAlbum(aid, quantity);
				}
				else
				{
					cart.addAlbum(aid, 1);
				}			
				request.setAttribute("cartCount", cart.getAlbumCount());
				chain.doFilter(request, response);
			}
			if(cart.getAlbumCount() != 0 && session.getAttribute("total") == null 
					&& session.getAttribute("booksInCart") == null){
				session.setAttribute("total", cart.getTotalPrice()); // MIGHT NEED TO MOVE THIS TO SESSION MANAGEMENT
				session.setAttribute("Albums", cart.getAlbums());// MIGHT NEED TO MOVE THIS TO SESSION MANAGEMENT
				session.setAttribute("cartCount", cart.getAlbumCount());

			}
			if(request.getParameter("delete") != null){
				int aid = Integer.parseInt(request.getParameter("addToCart"));
				cart.removeAlbum(aid);
				session.setAttribute("cartCount", cart.getAlbumCount());
				chain.doFilter(request, response);
			}
			if(request.getParameter("quantity") != null && request.getParameter("aid") != null){	
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				int aid = Integer.parseInt(request.getParameter("addToCart"));
				cart.updateQuantity(aid, quantity);
//				response.setHeader("total", 
//						"<b>Total: $" + cart.getTotalPrice() + ".00</b></td><br/>");
//				response.setHeader("subtotal", 
//						"$" + cart.getPricePerQuan(aid) + ".00</td><br/>");
//				response.setHeader("size", cart.getAlbumCount() +""); 
				session.setAttribute("cartCount", cart.getAlbumCount());
				chain.doFilter(request, response);
			}
			
			chain.doFilter(request, response);
		}
//		else {
//		// pass the request along the filter chain
//		chain.doFilter(request, response);
//		}
//		chain.doFilter(request, response);
//	}

	
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

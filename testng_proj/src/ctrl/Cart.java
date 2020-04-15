package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ShoppingCart;

/**
 * Servlet implementation class Cart
 * 
 * - Currently not being used / Not needed
 */
@WebServlet({ "/Cart", "/cart", "/Cart/*", "/cart/*" })
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Cart() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//request.getRequestDispatcher("/cart.jsp").forward(request, response);
		// THOROUGHLY CHECK CODE BELOW
		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
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
		}
		if(cart.getAlbumCount() != 0 && session.getAttribute("total") == null 
				&& session.getAttribute("booksInCart") == null){
			session.setAttribute("total", cart.getTotalPrice()); // MIGHT NEED TO MOVE THIS TO SESSION MANAGEMENT
			session.setAttribute("booksInCart", cart.getAlbums());// MIGHT NEED TO MOVE THIS TO SESSION MANAGEMENT
		}
		if(request.getParameter("delete") != null){
			int aid = Integer.parseInt(request.getParameter("addToCart"));
			cart.removeAlbum(aid);
		}
		if(request.getParameter("quantity") != null && request.getParameter("bid") != null){	
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			int aid = Integer.parseInt(request.getParameter("addToCart"));
			cart.updateQuantity(aid, quantity);
			response.setHeader("total", 
					"<b>Total: $" + cart.getTotalPrice() + ".00</b></td><br/>");
			response.setHeader("subtotal", 
					"$" + cart.getPricePerQuan(aid) + ".00</td><br/>");
			response.setHeader("size", cart.getAlbumCount() +""); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

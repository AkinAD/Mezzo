package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Album;
import model.MusicStore;
import model.ShoppingCart;

/**
 * Servlet implementation class Cart
 */
@WebServlet({ "/Cart", "/cart", "/Cart/*", "/cart/*" })
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MusicStore MS;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
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
		MS = (MusicStore) request.getServletContext().getAttribute("MS");
		Map<String, Album> data = new HashMap<String, Album>();
		Map<String, List<String>> out = new HashMap<String, List<String>>();
		session.setAttribute("cartCount", cart.getAlbumCount());
		if(request.getParameter("updateCart") != null && request.getParameter("updateCart").equals("true"))
		{
			try {
				putData(data, out, cart);
				session.setAttribute("cartCount", cart.getAlbumCount());
				session.setAttribute("cartItems", out);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (request.getPathInfo() != null && request.getPathInfo().contains("/Ajax"))
		{		
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		}
		else
		{ // if it has to get to this
			//request.getRequestDispatcher("/standaloneCart.jsp").forward(request, response);
		}

	}

	private void putData(Map<String, Album> data, Map<String, List<String>> out, ShoppingCart  cart) throws SQLException
	{		
		for(int aid : cart.getAlbums().keySet())
		{
		 List<String> tmp = new ArrayList<String>();
		 
		String  quantity = cart.getAlbums().get(aid).toString();
		tmp.add(Integer.toString(aid));
		tmp.add(cart.getArtist(aid));
		tmp.add(cart.getTitle(aid));
		tmp.add(cart.getCategory(aid));
		tmp.add(Float.toString(cart.getPricePerQuan(aid)));
		tmp.add(cart.getPicture(aid));
		tmp.add(quantity);
		out.put(Integer.toString(aid), tmp);
		}
	}	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

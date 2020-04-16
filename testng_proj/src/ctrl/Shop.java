package ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AlbumBean;
import model.MusicStore;

/**
 * Human-readable product catalogue
 * 
 * Servlet implementation class Shop
 */
@WebServlet({ "/Shop", "/shop", "/shop/*", "/Shop/*" })
public class Shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MusicStore MS = null;
	private String category = "category";
	private String error = "";
	private String search = "search";
	private String page = "page";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Shop() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			this.getServletContext().setAttribute("MS", MusicStore.getInstance());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		MS = (MusicStore) this.getServletContext().getAttribute("MS");
		Map<String, AlbumBean> data = new HashMap<String, AlbumBean>();
		Map<String, List<String>> out = new HashMap<String, List<String>>();

		if (request.getParameter(category) == null && request.getParameter(search) == null) {
			request.getRequestDispatcher("/shop.jsp").forward(request, response);
		} else if (request.getParameter(category) != null && request.getParameter(search) == null) {
			System.out.println("it got here- Shop by category");
			
			try {
				data = MS.retrievAlbumsByCat(request.getParameter(category));
				String cat = request.getParameter(category);
				System.out.println("the value of category is: " + cat);
				data = MS.retrievAlbumsByCat(cat);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				error = "bad cat?";
			}
			
			if (error.equals("")) {
				putData(data, out);
			} 
			else {
				System.out.println("there Was an Error");
			}
			
			request.setAttribute("shopDisq", "category");
			request.setAttribute("shopItems", out);
			request.getRequestDispatcher("/shop.jsp").forward(request, response);
		}
		else if(request.getParameter(search) != null && request.getParameter(category) == null)
		{
			System.out.println("it got here- Shop by search");
			System.out.println(search);
			try {
				data = MS.retrieveAlbumByGodKnowsWhat(request.getParameter(search));
				String srch = request.getParameter(search);
				System.out.println("the value of search is: " + srch);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				error = "bad search param?";
			}
			
			if (error.equals("")) {
				putData(data, out);
			} 
			else {
				System.out.println("there Was an Error");
			}
			
			request.setAttribute("shopDisq", "search");
			request.setAttribute("shopItems", out);
			request.getRequestDispatcher("/shop.jsp").forward(request, response);
		}
		else if(request.getParameter(page) != null && request.getParameter(category) == null && request.getParameter(search) == null)
		{
			System.out.println("it got here- Shop by search");
			System.out.println(search);
			try {
				data = MS.retrieveAlbumByGodKnowsWhat(request.getParameter(search));
				String srch = request.getParameter(search);
				System.out.println("the value of search is: " + srch);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				error = "bad search param?";
			}
			
			if (error.equals("")) {
				putData(data, out);
			} 
			else {
				System.out.println("there Was an Error");
			}
			
			request.setAttribute("shopDisq", "search");
			request.setAttribute("shopItems", out);
			request.getRequestDispatcher("/shop.jsp").forward(request, response);
		}

		
	

	}

	private void putData(Map<String, AlbumBean> data, Map<String, List<String>> out )
	{
		
			for (String stuff : data.keySet()) {
				List<String> tmp = new ArrayList<String>();
				tmp.add(Integer.toString(data.get(stuff).getAid()));
				tmp.add(data.get(stuff).getArtist());
				tmp.add(data.get(stuff).getTitle());
				tmp.add(data.get(stuff).getCategory());
				tmp.add(Float.toString(data.get(stuff).getPrice()));
				tmp.add(data.get(stuff).getPicture());
				out.put(stuff, tmp);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

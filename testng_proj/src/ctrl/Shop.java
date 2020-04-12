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

import bean.Album;
import model.MusicStore;

/**
 * Servlet implementation class Shop
 */
@WebServlet({ "/Shop", "/shop", "/shop/*", "/Shop/*" })
public class Shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MusicStore MS = null;
	private String category = "category";
	private String error = "";

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
		MusicStore MS = (MusicStore) this.getServletContext().getAttribute("MS");
		Map<String, Album> data = new HashMap<String, Album>();
		Map<String, List<String>> out = new HashMap<String, List<String>>();

		if (request.getParameter(category) == null) {
			request.getRequestDispatcher("/shop.jsp").forward(request, response);
		} else {
			try {
				data = MS.retrievAlbumsByCat(request.getParameter(category));
				String cat = request.getParameter(category);
				data = MS.retrievAlbumsByCat(cat);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				error = "bad cat?";
			}

			if (error.equals("")) {
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
			} else {
				System.out.println("there Was an Error");
			}
			request.setAttribute("shopItems", out);
			request.setAttribute("shopDisp", "filter");
			request.getRequestDispatcher("/shop.jsp").forward(request, response);
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

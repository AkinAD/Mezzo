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
 * Servlet implementation class Mezzo
 */
@WebServlet({ "/Test" })
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String query = "query";
	private static String error = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initializes servlet with given config to serlvet container 
	 */
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
		System.out.println("TESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSST");
		int aid = -1;
		MusicStore MS = (MusicStore) this.getServletContext().getAttribute("MS");
		Map<String, AlbumBean> data = new HashMap<String, AlbumBean>();
		Map<String, List<String>> out = new HashMap<String, List<String>>();

		response.getWriter().append("Served at: ").append(request.getContextPath());
		if (request.getParameter(query) == null) {
			request.getRequestDispatcher("/test.jsp").forward(request, response);
		} else {
			aid = Integer.parseInt(request.getParameter("aid"));

			try {
				data = MS.retrieveAlbum(aid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Failure to Access DB.. or something");
				error = "Bad 'aid'?";
				e.printStackTrace();
			}
			if (error.equals("")) {
				for (String stuff : data.keySet()) {
					List<String> tmp = new ArrayList<String>();
					tmp.add(Integer.toString(data.get(stuff).getAid()));
					// delete line below
					System.out.println(data.get(stuff).getAid());

					tmp.add(data.get(stuff).getArtist());
					System.out.println(data.get(stuff).getArtist());

					tmp.add(data.get(stuff).getTitle());
					System.out.println(data.get(stuff).getTitle());

					tmp.add(data.get(stuff).getCategory());
					System.out.println(data.get(stuff).getCategory());

					tmp.add(Float.toString(data.get(stuff).getPrice()));
					System.out.println(Float.toString(data.get(stuff).getPrice()));

					tmp.add(data.get(stuff).getPicture());
					System.out.println((data.get(stuff).getPicture()));

					out.put(stuff, tmp);
				}
			} else {
				System.out.println("there Was an Error");
			}
//				HttpSession sesh = request.getSession();
			request.setAttribute("output", out);
			request.setAttribute("error", error);
//				sesh.setAttribute("output", out);
//				sesh.setAttribute("error", error);
			if (request.getParameter(query) != null && request.getParameter(query).equals("true")) {
				request.getRequestDispatcher("/testOut.jsp").forward(request, response);
				System.out.println("Re routing to Result was executed");

			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

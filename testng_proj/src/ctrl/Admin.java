package ctrl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MusicStore;

/**
 * Servlet implementation class Admin
 */
@WebServlet({ "/Admin", "/admin" })
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MusicStore MS = null;
	private String category = "category";
	private String artist = "artist";
	private String title = "title";
	private String price = "price";
	private String url = "url";
	private String error = "";

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/admin.jsp").forward(request, response);
		MS = (MusicStore) this.getServletContext().getAttribute("MS");
		// Retrieve DAOs from context scope.
		//POAccessor = new POData();
		
		//Current Date to get report period
		Date date = new Date();
		SimpleDateFormat YMformate = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat YMDformate = new SimpleDateFormat("yyyyMMdd");
		
		try {
			//UC A1: Set report with book id and quantity as attribute.
			//request.setAttribute("report", POAccessor.retrieveOrderHistory(YMformate.format(date)+"01", YMDformate.format(date)));
			System.out.println(request.getAttribute("report"));
			/*
			Map<String, Integer> report = POAccessor.retrieveOrderHistory(YMformate.format(date)+"01", YMDformate.format(date));
			for (String bid: report.keySet())
				System.out.println("bid:"+bid+", quantity:"+report.get(bid));
			*/
			//UC A3: Set all PO records as attribute.
		//	request.setAttribute("anonymizedpo", POAccessor.retrieveAllPO());
			/*
			Map<POBean, Map<String, Integer>> map = POAccessor.retrieveAllPO();
			for (POBean key : map.keySet()) {
				System.out.println("***" + key.toString());
				for (String bid : map.get(key).keySet()) {
					System.out.println("book: " + bid + ", quantity: " + map.get(key).get(bid));
				}
			}
			*/
			//Forward to page
			request.getRequestDispatcher("/admin.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(request.getParameter("addAlbum") == null)
		{
			// DO NOTHING
		}
		else if(request.getParameter("addAlbum") != null)
		{
			String album_artist = request.getParameter(artist);
			String album_title = request.getParameter(artist);
			String album_category = request.getParameter(artist);
			Float album_price = Float.valueOf(request.getParameter(artist));
			String album_picture = request.getParameter(url);
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

package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.time.Month; 

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ProfileBean;
import bean.POBean;
import model.UserModel;
import model.MusicStore;
//import model.Payment;
import model.PurchaseOrder;

/**
 * Admin/analytics pages
 * 
 * User should be logged in to use this.
 * 
 * Servlet implementation class Admin
 * @author Akin, Alan, Aya, Dave
 */
@WebServlet({ "/Admin", "/admin" })
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MusicStore MS = null;
	private PurchaseOrder PO = null;
	//private Payment PAY = null;
	private String category = "category";
	private String artist = "artist";
	private String title = "title";
	private String price = "price";
	private String url = "url";
	private String USER = "user";
	private String POWER_LEVEL = "privilege";
	private String error = "";

	private static final String CUR_PROFILE = "CurProfile";
	private static final String ALBUM_CATS = "albumCats";
	private static final String PURCHASE_ORDER = "PO";
	private final String ADMIN_PAGE = "/admin.jsp";

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
		this.getServletContext().setAttribute("UM", UserModel.getInstance());
		this.getServletContext().setAttribute(PURCHASE_ORDER, PurchaseOrder.getInstance());


	}   
	/**
	 * Handles data passed to and from admin page view
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @throws ServletException IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// BEGIN SETUP
		UserModel uModel = (UserModel) this.getServletContext().getAttribute("UM");
		MS = (MusicStore) this.getServletContext().getAttribute("MS");
		PO = (PurchaseOrder) this.getServletContext().getAttribute("PO");
		ServletContext context = this.getServletContext();
		String curUsername = SessionManagement.getBoundUsername(request.getSession());
		Map<String, ProfileBean> data = new HashMap<String, ProfileBean>();
		
		//RETRIEV ECURRENT ADMIN USER
		try {
			data = uModel.retrieveAccountByUsername(curUsername);
			ProfileBean curProfile = data.values().iterator().next();
			request.setAttribute(CUR_PROFILE, curProfile);
		} catch (Exception e) {
			throw new ServletException(); // This is probably our fault
		}	
		
		//ANALYTICS
		try {
			PurchaseOrder po = (PurchaseOrder) context.getAttribute(PURCHASE_ORDER);
			int[] arr = po.retrieveAlbumsPerMonth();
			request.setAttribute("apm", arr); // apm - albums per month
			
			// A2
			String[] topThree = po.getTopThree();
			System.out.printf("one: %s  two: %s  three: %s \n", topThree[0], topThree[1], topThree[2]);
			request.setAttribute("topThree", topThree); // most sold albums
			String mostPopular = po.getMostPopular();
			request.setAttribute("mostPopular", mostPopular); // most sold albums
			
			//A3
			request.setAttribute("zipPurchaseCount", po.getZipPurchaseCount()); // zpc - zip purchase count
			Map<String, String> zipPurchaseCount = po.getZipPurchaseCount();
			for (String zip : zipPurchaseCount.keySet()) {
				System.out.printf("Zip: %s  PurchaseCount: %s \n", zip, zipPurchaseCount.get(zip));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//POPULATE ALBUM CATEGORIES 
		try {
			request.setAttribute(ALBUM_CATS, MS.retrieveAllCats());
		} catch (SQLException e) {
			throw new ServletException();
		}
		
		if(request.getParameter("addAlbum") == null && request.getParameter("updateRole") == null)
		{
			// DO NOTHING		
			request.getRequestDispatcher(ADMIN_PAGE).forward(request, response); //END SETUP

		}
		//END SETUP
		
		//ADD NEW ALBUM TO STORE
		else if(request.getParameter("addAlbum") != null)
		{
			String album_artist = request.getParameter(artist);
			String album_title = request.getParameter(title);
			String album_category = request.getParameter(category);
			Float album_price = Float.valueOf(request.getParameter(price));
			String album_picture = request.getParameter(url);
			
			try {
				MS.addAlbum(0, album_artist, album_title, album_category, album_price, album_picture);
			} catch (Exception e) {
				request.setAttribute("finalStatus", "failed");
				e.printStackTrace();
				error = "Failed to add Album to Database";
				request.setAttribute("error", error);				
				System.out.println(error);
			}
			request.getRequestDispatcher("/final.jsp").forward(request, response);

		}
		
		//UPDATE USER ROLE/PRIVILEGE
		if(request.getParameter("updateRole") != null)
		{
			String user = request.getParameter(USER);
			String POWER = request.getParameter(POWER_LEVEL);
			try {
				boolean result = uModel.updatePrivilege(user,POWER);
				if (!result)
				{
					System.out.println("Failed at update privilege, possible cause: " + uModel.getError());
					request.setAttribute("finalStatus", "failed");
				}
			} catch (SQLException e) {
				System.out.println("Failed at update privilege with error: " + uModel.getError());
				request.setAttribute("error", uModel.getError());		
				request.setAttribute("finalStatus", "failed");
				e.printStackTrace();
			}
			request.getRequestDispatcher("/final.jsp").forward(request, response);

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

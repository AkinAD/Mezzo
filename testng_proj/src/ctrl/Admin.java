package ctrl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.time.Month; 

import javax.servlet.ServletConfig;
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
	private String error = "";

	private static final String CUR_PROFILE = "CurProfile";

	
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

	}   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		UserModel uModel = (UserModel) this.getServletContext().getAttribute("UM");
		String curUsername = SessionManagement.getBoundUsername(request.getSession());
		Map<String, ProfileBean> data = new HashMap<String, ProfileBean>();
		try {
			data = uModel.retrieveAccountByUsername(curUsername);
			ProfileBean curProfile = data.values().iterator().next();
			request.setAttribute(CUR_PROFILE, curProfile);
		} catch (Exception e) {
			throw new ServletException(); // This is probably our fault
		}		

		if(request.getParameter("addAlbum") == null)
		{
			// DO NOTHING
			request.getRequestDispatcher("/admin.jsp").forward(request, response);
		}
		else if(request.getParameter("addAlbum") != null)
		{
			String album_artist = request.getParameter(artist);
			String album_title = request.getParameter(title);
			String album_category = request.getParameter(category);
			Float album_price = Float.valueOf(request.getParameter(price));
			String album_picture = request.getParameter(url);
			
			try {
				System.out.println("we got here fellas -Album");
				MS.addAlbum(0, album_artist, album_title, album_category, album_price, album_picture);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				error = "Failed to add Album to Database";
				System.out.println(error);
			}
		}
		
		
		MS = (MusicStore) this.getServletContext().getAttribute("MS");
		PO = (PurchaseOrder) this.getServletContext().getAttribute("PO");
		//PAY = (Payment) this.getServletContext().getAttribute("PAY");

		// Retrieve DAOs from context scope.
		//POAccessor = new POData();
		
		//Current Date to get report period
		Date date = new Date();
		SimpleDateFormat YMformate = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat YMDformate = new SimpleDateFormat("yyyyMMdd");
		
		try {
			//UC A1: generate report w/ albums sold each month
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
					*/
			
			System.out.println(request.getAttribute("report"));
			
			Map<String, Map<String, POBean>> monthlyProcessed = new HashMap<String, Map<String, POBean>>(); //<month, <PO_ID, POBean>>: month and all processed PO 
			Map<String, Map<String, Integer>> monthlySales = new HashMap<String, Map<String, Integer>>();
			
			for (int m = 1; m <13; m++) {
				String month = Integer.toString(m);
				if (month.length() == 1) { //if its "6" for june make it "06"
					month = "0"+month;
				}
				System.out.println(month);
				if (!monthlyProcessed.containsKey(month)) {
					monthlyProcessed.put(month, new HashMap<String, POBean>());
				}
				monthlyProcessed.get(month).putAll(PO.retrieveProcessedByMonth(month));
			}
			
			for (String month: monthlyProcessed.keySet()) {
				for (Map<String, POBean> po: monthlyProcessed.values()) {
					for (String po_ID: po.keySet()) {
						monthlySales.put(month, PO.retrieveItemByID(po_ID));
					}
				}
			} //data SHOULD now have (month, <aID, quantity>)
			
			
			//UC A3: provide annoymized reports w/ user buying statistics 
		//	request.setAttribute("anonymizedpo", POAccessor.retrieveAllPO());
			
			Map<POBean, Map<String, Integer>> allPO = PO.retrieveAllPO();
			Map<String, Map<String, Float>> total = new HashMap<String, Map<String, Float>>();
			Map<String, Map<String, Float>> anon = new HashMap<String, Map<String, Float>>();
//			for (POBean po : allPO.keySet()) {
//				 Map<String, Integer> idNq = PO.retrieveItemByID(po.getPO_id()); //idnq = <albumID, quantity>
//				 for (String aid : idNq.keySet()) {
//					 if (!total.containsKey(aid)) {
//						 total.put(aid, new HashMap<String, String>());
//					 }
//					total.get(po.getUsername()).put(MS.retrieveAlbumByID(Integer.parseInt(aid)).getPrice()*idNq.get(aid)));
//				 } //POtotal should have a mapping of PO and its total cost
//			} // anon should have (***, (zipcode, 
//			anon.put(user.replaceAll(".*", "*"), ( (PAY.retrieveBillingAddr(user)).getZip(), POtotal.get() ));
			//anon.put(po.getUsername().replaceAll(".*", "*"), ( (PAY.retrieveBillingAddr(po.getUsername())).getZip(), POtotal.get(po) ));

			//Forward to page
			//request.getRequestDispatcher("/admin.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
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

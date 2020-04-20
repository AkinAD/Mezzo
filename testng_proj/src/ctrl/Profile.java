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

import bean.AddressBean;
import bean.ProfileBean;
import model.UserModel;

/**
 * Profile page
 * 
 * Servlet implementation class Profile
 * 
 * @author alanyork
 */
@WebServlet({ "/Profile", "/profile" })
public class Profile extends HttpServlet {
	private static final String PARAM_PHONE = "phone";
	private static final String PARAM_POSTAL = "postal";
	private static final String PARAM_COUNTRY = "country";
	private static final String PARAM_PROVINCE = "province";
	private static final String PARAM_STREET = "street";
	public static final String PARAM_CAUSE = "cause";
	
	private static final String BILL_ADDR = "BillAddr";
	private static final String CUR_PROFILE = "CurProfile";
	
	private static final String PROFILE_PAGE = "profile.jsp";
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Profile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initializes servlet with given config to serlvet container 
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// this.getServletContext().setAttribute("MS", MusicStore.getInstance());
		this.getServletContext().setAttribute("UM", UserModel.getInstance());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		UserModel uModel = (UserModel) this.getServletContext().getAttribute("UM");
		String curUsername = SessionManagement.getBoundUsername(request.getSession());

		// Logic in case of updates
		String streetParam = request.getParameter(PARAM_STREET);
		String provParam = request.getParameter(PARAM_PROVINCE);
		String cuntParam = request.getParameter(PARAM_COUNTRY);
		String postParam = request.getParameter(PARAM_POSTAL);
		String phonParam = request.getParameter(PARAM_PHONE);
		
		boolean isBillUpdateSubmission;
		String[] upBillSubmitParams = {streetParam,provParam,cuntParam,postParam};		
		isBillUpdateSubmission = streetParam != null && !streetParam.isEmpty();
		for (String x : upBillSubmitParams) {
			isBillUpdateSubmission = isBillUpdateSubmission && x != null && !x.isEmpty();
		}
		
		if (isBillUpdateSubmission) {
			try {
				uModel.updateBillingAddressByUsername(curUsername, streetParam, provParam, cuntParam, postParam, phonParam);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Page display
		retrieveProfileBilling(request, response, curUsername, uModel);
		request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);
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

	/**
	 * Retrieves the AddressBean for the billing address,
	 * places it in request scope with Profile.BILL_ADDR
	 * as the key.
	 * 
	 * Also, retrieves the ProfileBean for the user
	 * bound to the current session and places it
	 * inside a single-element map with "fname, lname"
	 * as the key. This is placed in the request scope
	 * with key Profile.CUR_PROFILE.
	 * 
	 * @param request
	 * @param response
	 * @param curUsername
	 * @param uModel
	 * @throws ServletException
	 * @throws IOException
	 */
	private static void retrieveProfileBilling(HttpServletRequest request, HttpServletResponse response, String curUsername,
			UserModel uModel) throws ServletException, IOException {
		AddressBean billingAddress;
		try {
			billingAddress = uModel.retrieveBillingAddressByUsername(curUsername);
		} catch (Exception e) {
			billingAddress = new AddressBean("", "", "", "", "", "", "");
			//e.printStackTrace();
			System.out.println("Profile: Failed to retrieve billing address");
		}
		request.setAttribute(BILL_ADDR, billingAddress);

		// Why is this single object in a collection?
		// Because it interfaces with another collaborator's code  
		Map<String, ProfileBean> data = new HashMap<String, ProfileBean>();
		try {
			data = uModel.retrieveAccountByUsername(curUsername);
			ProfileBean curProfile = data.values().iterator().next();
			request.setAttribute(CUR_PROFILE, curProfile);					
		} catch (Exception e) {
			throw new ServletException(); // This is probably our fault
		}		
	}
}

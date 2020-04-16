package ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AddressBean;
import bean.CheckoutProfileBean;
import bean.ProfileBean;
import model.PurchaseOrder;
import model.UserModel;

/**
 * Checkout page.
 * 
 * User verifies billing/shipping addresses, payment info.
 * User should be logged in to use this.
 * 
 * Servlet implementation class Checkout.
 * 
 * @author alanyork
 */
@WebServlet({ "/Checkout", "/checkout" })
public class Checkout extends HttpServlet {
	private static final String PROFILE_ENDPOINT = "/Profile?"+Profile.PARAM_CAUSE+"=BadBillingInfo";

	private static final long serialVersionUID = 1L;
	
	private static final String BILL_ADDR = "BillAddr";
	private static final String CUR_PROFILE = "CurProfile";
	
	
	private UserModel userModel = UserModel.getInstance();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
        super();
    }
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
		retrieveProfileBilling(request, response, curUsername, uModel);
//		request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);

		try {
			CheckoutProfileBean payProfile = userModel.retrieveCheckoutProfileByUsername(curUsername);
			request.setAttribute(BILL_ADDR, payProfile.getBillingAddress());
			request.setAttribute(CUR_PROFILE, payProfile.getProfile());
		} catch (Exception e) {
			//e.printStackTrace();
			//Cannot proceed without billing and other profile information
			response.sendRedirect(request.getServletContext().getContextPath()+PROFILE_ENDPOINT);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
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

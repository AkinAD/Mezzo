package ctrl;

import java.io.IOException;
import java.util.Map;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Handle page display
		String curUsername = SessionManagement.getBoundUsername(request.getSession());

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

}

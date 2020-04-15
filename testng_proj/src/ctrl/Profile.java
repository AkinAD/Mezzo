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
 * Servlet implementation class Profile
 * 
 * @author alanyork
 */
@WebServlet({ "/Profile", "/profile" })
public class Profile extends HttpServlet {
	private static final String PHONE = "phone";
	private static final String POSTAL = "postal";
	private static final String COUNTRY = "country";
	private static final String PROVINCE = "province";
	private static final String STREET = "street";
	private static final String BILL_ADDR = "BillAddr";
	private static final String CUR_PROFILE = "CurProfile";
	private static final long serialVersionUID = 1L;
	private static final String PROFILE_PAGE = "profile.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Profile() {
		super();
		// TODO Auto-generated constructor stub
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

		
		String streetParam = request.getParameter(STREET);
		String provParam = request.getParameter(PROVINCE);
		String cuntParam = request.getParameter(COUNTRY);
		String postParam = request.getParameter(POSTAL);
		String phonParam = request.getParameter(PHONE);
		
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
		
		displayPage(request, response, curUsername, uModel);
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

	protected void displayPage(HttpServletRequest request, HttpServletResponse response, String curUsername,
			UserModel uModel) throws ServletException, IOException {
		AddressBean billingAddress;
		try {
			billingAddress = uModel.retrieveBillingAddressByUsername(curUsername);
		} catch (Exception e) {
			billingAddress = new AddressBean("", "", "", "", "", "", "");
			e.printStackTrace();
		}
		request.setAttribute(BILL_ADDR, billingAddress);

		Map<String, ProfileBean> data = new HashMap<String, ProfileBean>();
		try {
			data = uModel.retrieveAccountByUsername(curUsername);
			ProfileBean curProfile = data.values().iterator().next();
			request.setAttribute(CUR_PROFILE, curProfile);
			request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);			
		} catch (Exception e) {
			throw new ServletException(); // This is probably our fault
		}		
	}
}

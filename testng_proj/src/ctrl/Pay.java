package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AddressBean;
import bean.POBean;
import bean.ProfileBean;
import model.PurchaseOrder;
import model.ShoppingCart;
import model.UserModel;

/**
 * Payment processing
 * Displays whether or not the transaction was successful
 * 
 * Servlet implementation class Pay
 */
@WebServlet({ "/Pay", "/pay" })
public class Pay extends HttpServlet {	
	private static final long serialVersionUID = 1L;
	
	private static final String PARAM_PHONE = "phone";
	private static final String PARAM_POSTAL = "postal";
	private static final String PARAM_COUNTRY = "country";
	private static final String PARAM_PROVINCE = "province";
	private static final String PARAM_STREET = "street";
	private static final String PARAM_CREDIT = "cardNo";
	private static final String PARAM_CVV = "cardPass";
	private static final String PARAM_CARDHOLDER = "cardName";
	private static final String PARAM_CARDEXPMO = "cardExpMo";
	private static final String PARAM_CARDEXPYE = "cardExpYe";
	
	private static final String PURCHASE_FAIL_REASON = "failReason";
	
	private static final String FAIL_GENERAL = "general";
	private static final String FAIL_INTENTIONAL = "intentional";
	
	private PurchaseOrder purchaseOrder = PurchaseOrder.getInstance();
	private UserModel userModel = UserModel.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean failNow = false;
		boolean ohCrap = false;
		HttpSession curSession = request.getSession();
		String curUsername = SessionManagement.getBoundUsername(curSession);
		ProfileBean curProfile;
		try {
			curProfile = userModel.retrieveAccountByUsername(curUsername).values().iterator().next();
		} catch (IllegalArgumentException | SQLException e1) {
			throw new ServletException();
		}
		
		// Shipping address params
		String streetParam = request.getParameter(PARAM_STREET);
		String provParam = request.getParameter(PARAM_PROVINCE);
		String cuntParam = request.getParameter(PARAM_COUNTRY);
		String postParam = request.getParameter(PARAM_POSTAL);
		String phonParam = request.getParameter(PARAM_PHONE);
		// Credit card params
		String creditCard = request.getParameter(PARAM_CREDIT);
		String cvv = request.getParameter(PARAM_CVV);
		String cardholder = request.getParameter(PARAM_CARDHOLDER);
		String cardExpiryMo = request.getParameter(PARAM_CARDEXPMO);
		String cardExpiryYe = request.getParameter(PARAM_CARDEXPYE);
		
		// Insert card verification here
		// We ran out of time to figure out Luhn's algorithm
		// Check if card is expired
		YearMonth currentMonth = YearMonth.now();
		try {
			int expMo = new Integer(cardExpiryMo);
			int expYe = new Integer(cardExpiryYe);
			YearMonth presentedMonth = YearMonth.now().withYear(expYe).withMonth(expMo);
			if (currentMonth.isAfter(presentedMonth)) {
				failNow = true;
			} else {
				failNow = false;
			}
		} catch (Exception e) {
			// Failed verification
			e.printStackTrace();
			failNow = true;
		}

		// Fail every 3 payments
		if (SessionManagement.getFailCounter(curSession) >= 2) {
			SessionManagement.setFailCounter(curSession, 0);
			failNow = true;
			ohCrap = true;
		}

		if (!failNow) {
			// Attempt model transactions
			
			Date timeNow = Date.from(Instant.now());
			SimpleDateFormat idFormat = new SimpleDateFormat("yyyyMMddHHmmSS");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			AddressBean shippingAddr = new AddressBean(curUsername, streetParam, provParam, cuntParam, postParam, phonParam, AddressBean.SHIPPING);
			POBean curPo = new POBean(idFormat.format(timeNow),curUsername,PurchaseOrder.ORDER_ORDER,shippingAddr,dateFormat.format(timeNow),curProfile.getLname(),curProfile.getFname());
			
			// Prepare cart items
			ShoppingCart cart;
			try {
				cart = SessionManagement.getCart(curSession);
			} catch (ClassNotFoundException e) {
				throw new ServletException();
			}
			Map<Integer,Integer> cartMap = new HashMap<Integer,Integer>();
			cart.getAlbums().entrySet().stream().forEach(
				(curItem) -> cartMap.put(curItem.getKey(), curItem.getValue())
			);

			try {
				// Commit purchase
				purchaseOrder.updatePO(curPo,cartMap);
			} catch (Exception e) {
				// Model transaction failed
				failNow = true;
				e.printStackTrace();
			}
		}
		
		if (failNow) {
			// Purchase failed
			if (ohCrap) {
				// Intentional failure
				request.setAttribute(PURCHASE_FAIL_REASON, FAIL_INTENTIONAL);
			} else {
				// General failure
				request.setAttribute(PURCHASE_FAIL_REASON, FAIL_GENERAL);
			}
		} else {
			// Purchase succeeded
			try {
				SessionManagement.bindCart(curSession);
				request.setAttribute(PURCHASE_FAIL_REASON, null);
			} catch (ClassNotFoundException e) {
				throw new ServletException();
			}			
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

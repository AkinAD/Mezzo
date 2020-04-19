package ctrl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class DiffAdmin
 */
@WebServlet("/DiffAdmin")
public class DiffAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String purchaseOrder = "PO";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DiffAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.getServletContext().setAttribute(this.purchaseOrder, PurchaseOrder.getInstance());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = this.getServletContext(); 
		final String adminPage = "/admin.jsp";
		try {
			PurchaseOrder po = (PurchaseOrder) context.getAttribute(this.purchaseOrder);
			int[] arr = po.retrieveAlbumsPerMonth();
			request.setAttribute("apm", arr); // apm - albums per month


			request.setAttribute("topFive", po.retrieveTopFive());
			String topFive[] = po.retrieveTopFive();
			 System.out.printf("most pop : %s , second : %s, thr: %s , fou: %s, fiv: %s \n",
			 topFive[0], topFive[1], topFive[2], topFive[3], topFive[4]);

			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(adminPage).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @param po
	 * @return Returns the books sold per month parsed by ";"
	 * @throws Exception
	 * 
	 *                   private String getMonthData(PurchaseOrder po) throws
	 *                   Exception { String text = ""; String month = "";
	 *                   Map<String, POBean> map = new HashMap<String, POBean>();
	 *                   for (int i = 1; i <= 12; i++) { int counter = 0; if (i <
	 *                   10) month = String.format("0%s", Integer.toString(i)); else
	 *                   month = Integer.toString(i); map =
	 *                   po.retrieveProcessedByMonth(month); // for (int j = 0; j <
	 *                   map.size(); j++) { counter++; } text += month +
	 *                   Integer.toString(counter) + ";"; } return text; }
	 * 
	 *                   public int[] retrieveBooksPerMonth() throws SQLException {
	 *                   int[] bookMonths = new int[12]; String query = "SELECT *
	 *                   from PO"; Connection con = this.ds.getConnection();
	 *                   PreparedStatement p = con.prepareStatement(query);
	 *                   ResultSet r = p.executeQuery(); while (r.next()) {
	 *                   bookMonths[Integer.valueOf(r.getString("PO_DATE").substring(4,
	 *                   6)) - 1]++; } r.close(); p.close(); con.close(); return
	 *                   bookMonths; }
	 */
}

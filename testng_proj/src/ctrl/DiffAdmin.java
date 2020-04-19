package ctrl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import bean.*;
import model.*;

import dao.PODAO;

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
			String bpm = ""; // bpm - books per month
			int[] arr = po.retrieveBooksPerMonth();
			for (int i = 0; i < 12; i++) {
				bpm += Integer.toString(arr[i]) + ";";
			}
			System.out.print(bpm);
			request.getSession().setAttribute("bpm", bpm);
			
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
	 
	private String getMonthData(PurchaseOrder po) throws Exception {
		String text = "";
		String month = "";
		Map<String, POBean> map = new HashMap<String, POBean>();
		for (int i = 1; i <= 12; i++) {
			int counter = 0;
			if (i < 10)
				month = String.format("0%s", Integer.toString(i));
			else
				month = Integer.toString(i);
			map = po.retrieveProcessedByMonth(month); // 
			for (int j = 0; j < map.size(); j++) {
				counter++;
			}
			text += month + Integer.toString(counter) + ";";
		}
		return text;
	}
	
	public int[] retrieveBooksPerMonth() throws SQLException {
		int[] bookMonths = new int[12];
		String query = "SELECT * from PO";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			bookMonths[Integer.valueOf(r.getString("PO_DATE").substring(4, 6)) - 1]++;
		}
		r.close();
		p.close();
		con.close();
		return bookMonths;
	}*/
}

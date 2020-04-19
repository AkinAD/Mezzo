package dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

import bean.POBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PODAO {

	private DataSource ds;
	private AddressDAO addrDAO = new AddressDAO();

	public PODAO() throws ClassNotFoundException {
		try {
			//ds = (DataSource) (new InitialContext()).lookup("jdbc/Db2-4413");
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/Db2-4413");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update Purchase Order by given a POBean, the address will be stored in
	 * database here
	 * 
	 * @param po - POBean that need to be stored
	 * @throws SQLException
	 */
	public void updatePO(POBean po) throws SQLException {
		int a_id = addrDAO.updateAddr(po.getAddress());
		String query = "INSERT INTO PO VALUES ('" + po.getPO_id() + "', '" + po.getUsername() + "', '" + po.getStatus()
				+ "', " + a_id + ", '" + po.getPO_date() + "', '" + po.getLname() + "', '" + po.getFname() + "')";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.executeUpdate();
		p.close();
		con.close();
	}

	/**
	 * Return a Purchase Order POBean with PO_id
	 * 
	 * @param PO_id
	 * @return a Purchase Order POBean with PO_id, return null is PO_id is not found
	 * @throws SQLException
	 */
	public POBean retrievePOByID(String PO_id) throws SQLException {
		String query = "SELECT * from PO where po_id = ?";
		POBean po = null;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setString(1, PO_id);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String username = r.getString("USERNAME");
			String status = r.getString("STATUS");
			int a_id = r.getInt("A_ID");
			String PO_date = r.getString("PO_DATE");
			String lname = r.getString("LNAME");
			String fname = r.getString("FNAME");
			po = new POBean(PO_id, username, status, addrDAO.retrieveAddrByID(a_id), PO_date, lname, fname);
		}
		r.close();
		p.close();
		con.close();
		return po;
	}

	/**
	 * Return the user's past purchase orders, ordered from recent to past.
	 * 
	 * @param username
	 * @return Return the user's past purchase orders, null is user did
	 * @throws SQLException
	 */
	public Map<String, POBean> retrievePOByUser(String username) throws SQLException {
		String query = "SELECT * from PO where USERNAME='" + username + "' order by PO_DATE desc";
		Map<String, POBean> map = new HashMap<String, POBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String PO_id = r.getString("PO_ID");
			String status = r.getString("STATUS");
			int a_id = r.getInt("A_ID");
			String PO_date = r.getString("PO_DATE");
			String lname = r.getString("LNAME");
			String fname = r.getString("FNAME");
			map.put(PO_id, new POBean(PO_id, username, status, addrDAO.retrieveAddrByID(a_id), PO_date, lname, fname));
		}
		r.close();
		p.close();
		con.close();
		return map;
	}

	/**
	 * Return all purchase order
	 * 
	 * @return all purchase order
	 * @throws SQLException
	 */
	public Map<String, POBean> retrieveAll() throws SQLException {
		String query = "SELECT * from PO";
		Map<String, POBean> map = new HashMap<String, POBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String PO_id = r.getString("PO_ID");
			String username = r.getString("USERNAME");
			String status = r.getString("STATUS");
			int a_id = r.getInt("A_ID");
			String PO_date = r.getString("PO_DATE");
			String lname = r.getString("LNAME");
			String fname = r.getString("FNAME");
			map.put(PO_id, new POBean(PO_id, username, status, addrDAO.retrieveAddrByID(a_id), PO_date, lname, fname));
		}
		r.close();
		p.close();
		con.close();
		return map;
	}

	/**
	 * Return purchase orders within the date range from to until
	 * 
	 * @param start - starting date in String with format 'yyyymmdd', given date is
	 *              included
	 * @param end   - ending date in String with format 'yyyymmdd', given date is
	 *              included
	 * @return purchase orders within the date range from to until, null if no order
	 *         within those date
	 * @throws SQLException
	 */
	public Map<String, POBean> retrievePOByDate(String start, String end) throws SQLException {
		String query = "select * from PO where PO_id >='" + start + "' and PO_id <='" + end + "'";
		Map<String, POBean> map = new HashMap<String, POBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String PO_id = r.getString("PO_ID");
			String username = r.getString("USERNAME");
			String status = r.getString("STATUS");
			int a_id = r.getInt("A_ID");
			String PO_date = r.getString("PO_DATE");
			String lname = r.getString("LNAME");
			String fname = r.getString("FNAME");
			map.put(PO_id, new POBean(PO_id, username, status, addrDAO.retrieveAddrByID(a_id), PO_date, lname, fname));
		}
		r.close();
		p.close();
		con.close();
		return map;
	}

	/**
	 * Return purchase orders that is in the status given
	 * 
	 * @param status - "ORDERD", "PROCESSED" or "DENIED"
	 * @return purchase orders within the date range from to until, null if no order
	 *         within those date
	 * @throws SQLException
	 */
	public Map<String, POBean> retrievePOByStatus(String status) throws SQLException {
		String query = "select * from PO where STATUS ='" + status + "'";
		Map<String, POBean> map = new HashMap<String, POBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String PO_id = r.getString("PO_ID");
			String username = r.getString("USERNAME");
			int a_id = r.getInt("A_ID");
			String PO_date = r.getString("PO_DATE");
			String lname = r.getString("LNAME");
			String fname = r.getString("FNAME");
			map.put(PO_id, new POBean(PO_id, username, status, addrDAO.retrieveAddrByID(a_id), PO_date, lname, fname));
		}
		r.close();
		p.close();
		con.close();
		return map;
	}
	
	public Map<String, POBean> retrieveProcessedPOByMonth(String month) throws SQLException {
		String query = "select * from PO where STATUS = 'PROCESSED' and select where left(right(PO_DATE, len(PO_DATE)-2), len(right(PO_DATE, len(PO_DATE)-2))-4) as month = " + month;
		Map<String, POBean> map = new HashMap<String, POBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String PO_id = r.getString("PO_ID");
			String username = r.getString("USERNAME");
			String status = r.getString("STATUS");
			int a_id = r.getInt("A_ID");
			String PO_date = r.getString("PO_DATE");
			String lname = r.getString("LNAME");
			String fname = r.getString("FNAME");
			map.put(PO_id, new POBean(PO_id, username, status, addrDAO.retrieveAddrByID(a_id), PO_date, lname, fname));
			
		}
		r.close();
		p.close();
		con.close();
		return map;
	}

	/**
	 * Return the lname and fname of receivers for users' order
	 * 
	 * @param username
	 * @return Return the lname and fname of receivers for users' order
	 * @throws SQLException
	 */
	public Map<String, Map<String, String>> retrieveName(String username) throws SQLException {
		String query = "select A_ID, FNAME, LNAME from PO where USERNAME = '" + username + "' order by A_ID";
		Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String A_id = r.getString("A_ID");
			String lname = r.getString("LNAME");
			String fname = r.getString("FNAME");
			if (!map.containsKey(A_id))
				map.put(A_id, new HashMap<String, String>());
			map.get(A_id).put(fname, lname);
		}
		r.close();
		p.close();
		con.close();
		return map;
	}
	
	/**
	 * @return an array of book counts per month
	 * @throws SQLException
	 */
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
	}

}

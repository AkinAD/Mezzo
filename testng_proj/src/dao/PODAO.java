package dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import bean.POBean;

public class PODAO {

	private DataSource ds;
	private AddressDAO addrDAO = new AddressDAO();

	public PODAO() throws ClassNotFoundException {
		try {
			// ds = (DataSource) (new InitialContext()).lookup("jdbc/Db2-4413");
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
	 * @return an array of albums counts per month
	 * @throws SQLException
	 */
	public int[] retrieveAlbumsPerMonth() throws SQLException {
		int[] albuMonths = new int[12];
		String query = "SELECT * from PO";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			albuMonths[Integer.valueOf(r.getString("PO_DATE").substring(4, 6)) - 1]++;
		}
		r.close();
		p.close();
		con.close();
		return albuMonths;
	}

	/**
	 * @return a map of album id and the purchase count of that album
	 * @throws SQLException
	 */
	public Map<String, Integer> retrieveAidCount() throws SQLException {
		Map<String, Integer> aidCount = new HashMap<String, Integer>();
		String query = "SELECT * from PO";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String aid = r.getString("A_ID");
			if (aidCount.containsKey(aid))
				aidCount.put(aid, aidCount.get(aid) + 1);
			else
				aidCount.put(aid, 1);
		}
		r.close();
		p.close();
		con.close();
		return aidCount;
	}

	/**
	 * @param aid - album id
	 * @return album title given the album id
	 * @throws SQLException
	 */
	public String retrieveAlbumTitle(String aid) throws SQLException {
		String title = "";
		String query = "select * from album where aid=" + aid;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			title = r.getString("title");
		}
		r.close();
		p.close();
		con.close();
		return title;
	}

	/**
	 * @param userName
	 * @return the purchase count associated with the user name
	 * @throws SQLException
	 */
	public int retrievePurchaseCount(String userName) throws SQLException {
		int purchaseCount = 0;
		String query = "select * from PO where USERNAME = '" + userName + "'";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next())
			purchaseCount++;
		r.close();
		p.close();
		con.close();
		return purchaseCount;
	}
}

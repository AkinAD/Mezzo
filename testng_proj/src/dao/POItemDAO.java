package dao;

import java.sql.*;
import java.util.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.*;

public class POItemDAO {

	private DataSource ds;

	public POItemDAO() throws ClassNotFoundException {
		try {
			 ds = (DataSource) (new InitialContext()).lookup("jdbc/Db2-4413");
//			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/Db2-4413"); // USE THIS TO DEBUG
																								// LOCALLY
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update POItem by giving PO_id from POBean and a map stored bid and quantity
	 * for each album
	 * 
	 * @param PO_id
	 * @param albums
	 * @throws SQLException
	 */
	public void updateItem(String PO_id, Map<Integer, Integer> albums) throws SQLException {
		String query = "INSERT INTO POITEM VALUES (?, ?, ?)";
		Connection con = this.ds.getConnection();
		PreparedStatement p;
		for (int aid : albums.keySet()) {
			p = con.prepareStatement(query);
			p.setString(1, PO_id);
			p.setInt(2, aid);
			p.setInt(3, albums.get(aid));
			p.executeUpdate();
			p.close();
		}
		con.close();
	}

	/**
	 * Returns a map of orders of an album and the corresponding POItem
	 * 
	 * @param aid
	 * @return
	 * @throws SQLException
	 */
	public Map<String, POItemBean> retrievePOItemWithAlbum(int AID) throws SQLException {
		String query = "SELECT PO_ID, AID, QUANTITY FROM POITEM WHERE AID = ?";
		Map<String, POItemBean> returnValue = new TreeMap<String, POItemBean>();

		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setInt(1, AID);

		ResultSet r = p.executeQuery();

		while (r.next()) {
			String PO_id = r.getString("PO_ID");
			int aid = r.getInt("AID");
			int quantity = r.getInt("QUANTITY");
			POItemBean curItem = new POItemBean(PO_id, aid, quantity);
			returnValue.put(PO_id, curItem);
		}

		r.close();
		p.close();
		con.close();

		return returnValue;
	}

}
package dao;

import java.sql.*;
import java.util.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.*;

public class POItemDAO {

	private DataSource ds;

	/**
	 * Initializes the PO item data access object for data retrieval
	 * 
	 * @throws ClassNotFoundException
	 */
	public POItemDAO() throws ClassNotFoundException {
		try {
			//ds = (DataSource) (new InitialContext()).lookup("jdbc/Db2-4413");
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/Db2-4413"); // USE THIS TO DEBUG LOCALLY		
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
	 * Return Map with AID and Quantity for each album
	 * 
	 * @param PO_id
	 * @return Map with AID and Quantity for each album
	 * @throws SQLException
	 */
	public Map<Integer, Integer> retrieveItemByID(String PO_id) throws SQLException {
		String query = "select * from POItem where PO_ID=?";
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setString(1, PO_id);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			int  aid = r.getInt("BID");
			int quantity = r.getInt("QUANTITY");
			map.put(aid, quantity);
		}
		r.close();
		p.close();
		con.close();
		return map;
	}

	/**
	 * Return the ordered item information for an album in a particular Purchase order
	 * 
	 * @param POID
	 * @param AID
	 * @return Return the ordered item information for an album in a particular
	 *         Purchase order
	 * @throws SQLException
	 */
	public POItemBean retrieveItem(String POID, int AID) throws SQLException {
		String query = "select P.po_id, P.AID, P.QUANTITY from POITEM P where P.po_id = ? and P.AID = ?";
		POItemBean POItem = null;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setString(1, POID);
		p.setInt(2, AID);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String PO_id = r.getString("PO_ID");
			int aid = r.getInt("AID");
			int quantity = r.getInt("QUANTITY");
			POItem = new POItemBean(PO_id, aid, quantity);
		}
		r.close();
		p.close();
		con.close();
		return POItem;
	}

	/**
	 * Returns a map of orders of an album and the corresponding POItem
	 * @param aid
	 * @return
	 * @throws SQLException
	 */
	public Map<String, POItemBean> retrievePOItemWithAlbum(int AID) throws SQLException {
		String query = "SELECT PO_ID, AID, QUANTITY FROM POITEM WHERE AID = ?";
		Map<String, POItemBean> returnValue = new TreeMap<String,POItemBean>();

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

	/**
	 * Return albums with sold quantity that has been ordered in this period (from
	 * start to end)
	 * 
	 * @param start - starting date in String with format 'yyyymmdd', given date is
	 *              included
	 * @param end   - ending date in String with format 'yyyymmdd', given date is
	 *              included
	 * @return Return all albums that have been ordered in this period with quantity
	 * @throws SQLException
	 */
	public Map<Integer, Integer> retrieveOrderHistory(String start, String end) throws SQLException {
		String query = "select POItem.aid, sum(POItem.quantity) as \"QUANTITY\" from POItem, PO where POItem.PO_id=PO.PO_id and PO.status<>'DENIED' and POItem.PO_id >= (select min(PO_id) from POItem where PO_id like '"
				+ start + "%') and POItem.PO_id <= (select max(PO_id) from POItem where PO_id like '" + end
				+ "%') group by POItem.aid";
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			int aid = r.getInt("AID");
			int quantity = r.getInt("QUANTITY");
			map.put(aid, quantity);
		}
		r.close();
		p.close();
		con.close();
		return map;
	}

	/**
	 * Return the most popular album of all time
	 * 
	 * @return the most popular album
	 * @throws SQLException
	 */
	public Map<Integer, Integer> retrieveMostPopular() throws SQLException {
		String query = "select aid, Q from (select POItem.bid, sum(POItem.quantity) as \"Q\" from POItem, PO where POItem.PO_id=PO.PO_id and PO.status<>'DENIED' group by POItem.aid) as M where Q=(select max(Q) from (select POItem.aid, sum(POItem.quantity) as \"Q\" from POItem, PO where POItem.PO_id=PO.PO_id and PO.status<>'DENIED' group by POItem.aid) as M)";
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			int aid = r.getInt("AID");
			int quantity = r.getInt("Q");
			map.put(aid, quantity);
		}
		r.close();
		p.close();
		con.close();
		return map;
	}

}
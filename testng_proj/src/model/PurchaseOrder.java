package model;

import java.sql.SQLException;
import java.util.*;

import dao.*;
import bean.*;
import ctrl.SessionManagement;

/**
 * PurchaseOrder model
 * 
 *
 */
public class PurchaseOrder {

	private PODAO po;
	private POItemDAO poitem;
	private AddressDAO addressDao;
	private UserModel userModel;
	private static final PurchaseOrder INSTANCE = new PurchaseOrder();

	public static final String ORDER_PROC = "PROCESSED";
	public static final String ORDER_FAIL = "DENIED";
	public static final String ORDER_ORDER = "ORDERD";

	/**
	 * Initializes purchase order 
	 */
	private PurchaseOrder() {
		try {
			po = new PODAO();
			poitem = new POItemDAO();
			addressDao = new AddressDAO();
			userModel = UserModel.getInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return instance of purchase order
	 */
	public static PurchaseOrder getInstance() {
		return INSTANCE;
	}

	/**
	 * Return Map<POBean, Map<String, Integer>>, where POBean stores information for
	 * every PO for this user, inner map Map<String, Integer> stores each book and
	 * quantity user ordered in this PO.
	 * 
	 * @param username
	 * @return Map<POBean, Map<String, Integer>>
	 * @throws Exception
	 */
	public Map<POBean, Map<Integer, Integer>> retrievePO(String username) throws Exception {
		Map<String, POBean> pobean = po.retrievePOByUser(username);
		Map<POBean, Map<Integer, Integer>> result = new HashMap<POBean, Map<Integer, Integer>>();
		for (String key : pobean.keySet()) {
			result.put(pobean.get(key), poitem.retrieveItemByID(key));
		}
		return result;
	}

	/**
	 * Return Map<POBean, Map<String, Integer>>, where POBean stores information for
	 * every PO for all users, inner map Map<String, Integer> stores each book and
	 * quantity user ordered in this PO.
	 * 
	 * @param username
	 * @return Map<POBean, Map<String, Integer>>
	 * @throws Exception
	 */
	public Map<POBean, Map<Integer, Integer>> retrieveAllPO() throws Exception {
		Map<String, POBean> pobean = po.retrieveAll();
		Map<POBean, Map<Integer, Integer>> result = new HashMap<POBean, Map<Integer, Integer>>();
		for (String key : pobean.keySet()) {
			result.put(pobean.get(key), poitem.retrieveItemByID(key));
		}
		return result;
	}

	/**
	 * Update a new purchase order with created POBean and map in shopping cart
	 * 
	 * @param pobean - pobean that need to be stored
	 * @param albums - from shopping cart, ShoppingCart.getBooks()
	 * @return PO_id for pobean
	 * @throws Exception
	 */
	public String updatePO(POBean pobean, Map<Integer, Integer> albums) throws Exception {
		po.updatePO(pobean);
		poitem.updateItem(pobean.getPO_id(), albums);
		return pobean.getPO_id();
	}

	/**
	 * Return a Purchase Order POBean with PO_id
	 * 
	 * @param PO_id
	 * @return a Purchase Order POBean with PO_id, return null is PO_id is not found
	 * @throws SQLException
	 */
	public POBean retrievePOByID(String PO_id) throws Exception {
		return po.retrievePOByID(PO_id);
	}

	/**
	 * Return the user's past purchase orders, ordered from recent to past.
	 * 
	 * @param username
	 * @return Return the user's past purchase orders, null is user did
	 * @throws SQLException
	 */
	public Map<String, POBean> retrievePOByUser(String username) throws Exception {
		return po.retrievePOByUser(username);
	}

	/**
	 * Return all purchase order
	 * 
	 * @return all purchase order
	 * @throws SQLException
	 */
	public Map<String, POBean> retrieveAll() throws SQLException {
		return po.retrieveAll();
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
		return po.retrievePOByDate(start, end);
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
		return po.retrievePOByStatus(status);
	}

	/**
	 * Return Map with BookBean and Quantity for each book
	 * 
	 * @param PO_id
	 * @return Map with BookBean and Quantity for each book
	 * @throws SQLException
	 */
	public Map<Integer, Integer> retrieveItemByID(String PO_id) throws SQLException {
		return poitem.retrieveItemByID(PO_id);
	}

	/**
	 * Returns a map of orders of an album and the corresponding POItem
	 * 
	 * @param aid
	 * @return
	 * @throws SQLException
	 */
	public Map<String, POItemBean> retrieveItemsByAlbum(int aid) throws SQLException {
		return poitem.retrievePOItemWithAlbum(aid);
	}

	/**
	 * Return the ordered item information for a book in a particular Purchase order
	 * 
	 * @param POID
	 * @param bookID
	 * @return Return the ordered item information for a book in a particular
	 *         Purchase order
	 * @throws SQLException
	 */
	public POItemBean retrieveItem(String POID, int aid) throws SQLException {
		return poitem.retrieveItem(POID, aid);
	}

	/**
	 * Return all book with quantity that has been ordered in this period (from
	 * start to end)
	 * 
	 * @param start - starting date in String with format 'yyyymmdd', given date is
	 *              included
	 * @param end   - ending date in String with format 'yyyymmdd', given date is
	 *              included
	 * @return Return all book that has been ordered in this period with quantity
	 * @throws SQLException
	 */
	public Map<Integer, Integer> retrieveOrderHistory(String start, String end) throws SQLException {
		return poitem.retrieveOrderHistory(start, end);
	}

	/**
	 * Return the most popular book from the first order
	 * 
	 * @return the most popular book
	 * @throws SQLException
	 */
	public Map<Integer, Integer> retrieveMostPopular() throws SQLException {
		return poitem.retrieveMostPopular();
	}

	/**
	 * NOT USED
	 * 
	 * @param month
	 * @return
	 * @throws SQLException
	 */
	public Map<String, POBean> retrieveProcessedByMonth(String month) throws SQLException {
		// TODO Auto-generated method stub
		return po.retrieveProcessedPOByMonth(month);
	}

	/**
	 * Retrieves all albums sold given a month 
	 * 
	 * @return Array of albums sold per month
	 * @throws SQLException
	 */
	public int[] retrieveAlbumsPerMonth() throws SQLException {
		return po.retrieveAlbumsPerMonth();
	}

	/**
	 * Retrieves an album's title given an album ID
	 * 
	 * @param aid - album id
	 * @return title of the album purchased
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	private String retrieveAlbumTitle(String aid) throws NumberFormatException, SQLException {
		return po.retrieveAlbumTitle(aid);
	}

	/**	  
	 * @return the most sold album title
	 * @throws SQLException
	 */
	public String getMostPopular() throws SQLException {
		Map<String, Integer> aidCount = po.retrieveAidCount();
		ArrayList<String> aid = new ArrayList<String>(aidCount.keySet());
		ArrayList<Integer> count = new ArrayList<Integer>(aidCount.values());
		int max = Collections.max(count);
		return this.retrieveAlbumTitle(aid.get(count.indexOf(max)));
	}

	/**
	 * @return the top three most sold album title or simply most sold if less than
	 *         3 albums were sold
	 * @throws SQLException 
	 */
	public String[] getTopThree() throws SQLException {
		Map<String, Integer> aidCount = po.retrieveAidCount();
		if (aidCount.size() < 3)
			return new String[] { this.getMostPopular() };
		String[] topThree = new String[3];
		ArrayList<String> aid = new ArrayList<String>(aidCount.keySet());
		ArrayList<Integer> count = new ArrayList<Integer>(aidCount.values());

		int max = Collections.max(count);
		String maxAid = aid.get(count.indexOf(max));
		topThree[0] = this.retrieveAlbumTitle(maxAid);
		aid.remove(aid.indexOf(maxAid));
		count.remove(count.indexOf(max));

		max = Collections.max(count);
		maxAid = aid.get(count.indexOf(max));
		topThree[1] = this.retrieveAlbumTitle(maxAid);
		aid.remove(aid.indexOf(maxAid));
		count.remove(count.indexOf(max));

		max = Collections.max(count);
		maxAid = aid.get(count.indexOf(max));
		topThree[2] = this.retrieveAlbumTitle(aid.get(count.indexOf(max)));
		aid.remove(aid.indexOf(maxAid));
		count.remove(count.indexOf(max));

		return topThree;
	}
	 
	/**
	 * @return a billing ZIP and purchase count associated with that ZIP
	 * @throws SQLException
	 */
	public Map<String, String> getZipPurchaseCount() throws SQLException {
		Map<String, String> usernameZip = this.addressDao.retrieveUserNameZip();
		ArrayList<String> userNames = new ArrayList<String>(usernameZip.keySet());
		ArrayList<String> zips = new ArrayList<String>(usernameZip.values());
		ArrayList<Integer> purchaseCount = new ArrayList<Integer>();
		Map<String, String> zipPurchaseCount = new HashMap<String, String>();
		for(String users : userNames) {
			purchaseCount.add(po.retrievePurchaseCount(users));
		}
		for (int i = 0; i < purchaseCount.size(); i++) {
			if (zipPurchaseCount.containsKey(zips.get(i))) {
				zipPurchaseCount.put(zips.get(i), Integer.toString(purchaseCount.get(i) + Integer.parseInt(zipPurchaseCount.get(zips.get(i)))));
			} else {
				zipPurchaseCount.put(zips.get(i), Integer.toString(purchaseCount.get(i)));
			}
		}
		return zipPurchaseCount;
	}

	/*
	 * public int insertShippingAddress(String username, String street, String
	 * province, String country, String zip, String phone) throws
	 * IllegalArgumentException, SQLException { String errorMsg = ""; if
	 * (username.length() < 2 || username.equals("")) { errorMsg = "Invalid field";
	 * } else if (userModel.retrieveAccountByUsername(username).isEmpty()) {
	 * errorMsg = "Invalid field"; } else if (street == null || street.isEmpty() ||
	 * street.length() > 100) { errorMsg = "Invalid field"; } else if (province ==
	 * null || province.isEmpty() || province.length() > 25) { errorMsg =
	 * "Invalid field"; } else if (country == null || country.isEmpty() ||
	 * country.length() > 24) { errorMsg = "Invalid field"; } else if (zip == null
	 * || zip.isEmpty() || zip.length() > 20) { errorMsg = "Invalid field"; } else
	 * if (!(phone == null || phone.isEmpty()) && phone.length() > 20) { errorMsg =
	 * "Invalid field"; } if (!errorMsg.isEmpty()) { throw new
	 * IllegalArgumentException(errorMsg); }
	 * 
	 * AddressBean inAddr = new AddressBean(username, street, province, country,
	 * zip, phone, "Shipping"); int curAddr = addressDao.retrieveAddrByBean(inAddr);
	 * if (curAddr == 0) { // Address does not exist in DB // Insert address curAddr
	 * = addressDao.updateAddr(inAddr); curAddr =
	 * addressDao.retrieveAddrByBean(inAddr); }
	 * 
	 * return curAddr; }
	 */
}

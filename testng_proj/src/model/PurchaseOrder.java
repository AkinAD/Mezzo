package model;

import java.sql.SQLException;
import java.util.*;

import dao.*;
import bean.*;

/**
 * PurchaseOrder model
 */
public class PurchaseOrder {

	private PODAO po;
	private POItemDAO poitem;
	private AddressDAO addressDao;
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
	 * Update a new purchase order with created POBean and map in shopping cart
	 * 
	 * @param pobean - pobean that need to be stored
	 * @param albums - from shopping cart, ShoppingCart.getAlbums()
	 * @return PO_id for pobean
	 * @throws Exception
	 */
	public String updatePO(POBean pobean, Map<Integer, Integer> albums) throws Exception {
		po.updatePO(pobean);
		poitem.updateItem(pobean.getPO_id(), albums);
		return pobean.getPO_id();
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
		for (String users : userNames) {
			purchaseCount.add(po.retrievePurchaseCount(users));
		}
		for (int i = 0; i < purchaseCount.size(); i++) {
			if (zipPurchaseCount.containsKey(zips.get(i))) {
				zipPurchaseCount.put(zips.get(i),
						Integer.toString(purchaseCount.get(i) + Integer.parseInt(zipPurchaseCount.get(zips.get(i)))));
			} else {
				zipPurchaseCount.put(zips.get(i), Integer.toString(purchaseCount.get(i)));
			}
		}
		return zipPurchaseCount;
	}
}

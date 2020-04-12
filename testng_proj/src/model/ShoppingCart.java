package model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import dao.AlbumDAO;
import bean.Album;

/**
 * Shopping Cart model class
 */
public class ShoppingCart {

	private Map<Integer, Integer> albums; // Map of integer (album id) and integer (quantity)
	private AlbumDAO albumInfo; // Album information retriever

	/**
	 * Initializes the shopping cart
	 * 
	 * @throws ClassNotFoundException
	 */
	public ShoppingCart() throws ClassNotFoundException {
		this.albums = new HashMap<Integer, Integer>();
		this.albumInfo = new AlbumDAO();
	}

	/**
	 * Adds the album to the shopping cart
	 * 
	 * @param aid
	 * @param quantity
	 */
	public void addAlbum(int aid, int quantity) {
		if (!albums.containsKey(aid)) {
			albums.put(aid, quantity);
		} else {
			albums.put(aid, albums.get(aid) + quantity);
		}
	}

	/**
	 * Remove album from the shopping cart
	 * 
	 * @param aid
	 */
	public void removeAlbum(int aid) {
		if (albums.containsKey(aid)) {
			albums.remove(aid);
		}
	}

	/**
	 * Update the quantity of the album in the cart
	 * 
	 * @param aid
	 * @param quantity
	 */
	public void updateQuantity(int aid, int quantity) {
		if (albums.containsKey(aid)) {
			albums.put(aid, quantity);
		}
	}

	/**
	 * @return the total price of the shopping cart
	 */
	public float getTotalPrice() {
		float subtotal = 0f;
		for (Map.Entry<Integer, Integer> entry : this.albums.entrySet()) {
			Album al;
			try {
				al = this.albumInfo.retrieveAlbum(entry.getKey());
				subtotal += al.getPrice() * entry.getValue();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return subtotal;
	}

	/**
	 * @return the total number of albums in the shopping cart
	 */
	public int getAlbumCount() {
		int totalCount = 0;
		for (Integer count : albums.values())
			totalCount += count;
		return totalCount;
	}

	/**
	 * @return the albums in the shopping cart
	 */
	public Map<Integer, Integer> getAlbums() {
		return this.albums;
	}

	/**
	 * @param aid
	 * @return the Album from
	 */
	public Album retrieveAlbum(int aid) throws SQLException {
		Album al = null;
		if (albums.containsKey(aid)) {

		}
		return al;
	}

}

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
	 * @return the Album according to album id
	 */
	public Album getAlbum(int aid) throws SQLException {
		Album al = null;
		if (this.albums.containsKey(aid)) {
			try {
				al = this.albumInfo.retrieveAlbum(aid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return al;
	}

	/**
	 * @param aid
	 * @return the artist name of the album
	 * @throws SQLException
	 */
	public String getArtist(int aid) throws SQLException {
		return this.getAlbum(aid).getArtist();
	}
	
	/**
	 * @param aid
	 * @return the title of the album
	 * @throws SQLException
	 */
	public String getTitle (int aid) throws SQLException {
		return this.getAlbum(aid).getTitle();
	}
	
	/**
	 * @param aid
	 * @return the category of the album
	 * @throws SQLException
	 */
	public String getCategory (int aid) throws SQLException {
		return this.getAlbum(aid).getCategory();
	}
	
	/**
	 * @param aid
	 * @return the price of the album
	 * @throws SQLException
	 */
	public Float getPrice (int aid) throws SQLException {
		return this.getAlbum(aid).getPrice();
	}
	
	/*
	 * Picture retrieval not being used 
	 * 
	 * 
	 */
	public String getPicture (int aid) throws SQLException {
		return this.getAlbum(aid).getPicture();
	}

	
	/**
	 * 
	 * @param AID album id of an album.
	 * @return the unit price * quantity of the given album in cart.
	 */
	public float getPricePerQuan(int aid){
		float totalPrice = 0;
		Album alb;
		try {
			if(albums.containsKey(aid)){
				alb = albumInfo.retrieveAlbum(aid);
				totalPrice = alb.getPrice() * albums.get(aid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalPrice;
	}
	
	public void emptyCart(){
		this.albums.clear();
	}
}

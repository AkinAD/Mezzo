package bean;

//import java.awt.Image;

/**
 * Album bean
 */
public class AlbumBean {
	public int aid;
	public String artist;
	public String title;
	public String category;
	public Float price;
	public String picture;
	
	/**
	 * Initializes the album
	 * 
	 * @param aid
	 * @param artist
	 * @param title
	 * @param category
	 * @param price
	 * @param picture
	 */
	public AlbumBean(int aid, String artist, String title, String category, Float price, String picture) {
		this.setAid(aid);
		this.setArtist(artist);
		this.setTitle(title);
		this.setCategory(category);
		this.setPrice(price);
		this.setPicture(picture);
	}

	/**
	 * @return the album id
	 */
	public int getAid() {
		return aid;
	}

	/**
	 * Sets the album id
	 * 
	 * @param aid
	 */
	public void setAid(int aid) {
		this.aid = aid;
	}

	/**
	 * @return the artist of the album
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * Set the artist of the album
	 * 
	 * @param artist
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * @return the title of the album
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title of the album
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the category of the album
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category of the album
	 * 
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return price of the album
	 */
	public Float getPrice() {
		return price;
	}

	/**
	 * Sets the price of the album
	 * 
	 * @param price
	 */
	public void setPrice(Float price) {
		this.price = price;
	}

	/**
	 * @return picture of album 
	 */
	public String getPicture() {
		//maybe generate image here;
		return picture;
	}

	/**
	 * Sets the picture of the album
	 * 
	 * @param picture
	 */
	public void setPicture(String picture) {
		//maybe generate image here
		this.picture = picture;
	}
	
}

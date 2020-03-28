package bean;

//import java.awt.Image;

public class Album {
	public int aid;
	public String artist;
	public String title;
	public String category;
	public Float price;
	public String picture;
	
	public Album(int aid, String artist, String title, String category, Float price, String picture) {
		super();
		this.aid = aid;
		this.artist = artist;
		this.title = title;
		this.category = category;
		this.price = price;
		this.picture = picture;
	}

	public Album() {
		// TODO Auto-generated constructor stub
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getPicture() {
		//maybe generage image here;
		return picture;
	}

	public void setPicture(String picture) {
		//maybe generate image here
		this.picture = picture;
	}
	
}

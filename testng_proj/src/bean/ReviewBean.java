package bean;

/**
 * 
 * @author alanyork
 *
 */
public class ReviewBean {
	int aid;
	String username;
	int rating;
	String review;
	/**
	 * @return the aid
	 */
	public int getAid() {
		return aid;
	}
	/**
	 * Sets album ID to given ID
	 * 
	 * @param aid 
	 */
	public void setAid(int aid) {
		this.aid = aid;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Sets username to given username
	 * 
	 * @param username 
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}
	/**
	 * Sets album rating to given rating
	 * 
	 * @param rating 
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	/**
	 * @return the review
	 */
	public String getReview() {
		return review;
	}
	/**
	 * Sets album review to user-given review
	 * 
	 * @param review 
	 */
	public void setReview(String review) {
		this.review = review;
	}
	
	/**
	 * Print out the username and their review
	 */
	public String toString() {
		return username + ": " + review;
	}
}

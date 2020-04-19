package model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.AlbumBean;
import bean.ReviewBean;
import dao.AlbumDAO;
import dao.ReviewDAO;
/**
 * Catalogue model
 * 
 * @author alanyork
 *
 */
public class MusicStore {
	private AlbumDAO alDao;
	private ReviewDAO revDao;
	private static MusicStore instance;
	private static String Error = "";

	/**
	 * Initializes the music store
	 */
	private MusicStore() {
		try {
			this.alDao = new AlbumDAO();
			this.revDao = new ReviewDAO();
		} catch (Exception e) {
			System.out.println("Failed to retrieve ALBUM Data Access Object");

		}
	}

	/**
	 * @return an instance of the store and its albums
	 * @throws ClassNotFoundException
	 */
	public static MusicStore getInstance() throws ClassNotFoundException {
		if (instance == null) {
			instance = new MusicStore();
			instance.alDao = new AlbumDAO();
		}
		return instance;
	}

	/**
	 * (name typo)Retrieves an album given an album ID
	 * 
	 * @param aid
	 * @return album of given ID 
	 * @throws Exception
	 */
	public Map<String, AlbumBean> retrievAlbum(int aid) throws Exception {
		if (aid < 0 || aid > 10000) {
			throw new IllegalArgumentException();
		}
		Error = "";
		return alDao.retrieveAll(aid);
	}
	
	
	/**
	 * Retrieves album by id as well??? This method isn't called anywhere, remove?
	 * 
	 * @param aid
	 * @return 
	 * @throws Exception
	 */
	public AlbumBean retrieveAlbumByID(int aid) throws Exception {
		return alDao.retrieveAlbum(aid);
	}
	
	/**
	 * Retrieves an album's reviews given an album ID
	 * 
	 * @param aid
	 * @return album's reviews 
	 * @throws Exception
	 */
	public Map<String, ReviewBean> retrieveReviews(int aid) throws Exception {
		if (aid < 0 || aid > 10000) {
			throw new IllegalArgumentException();
		}
		Error = "";
		return revDao.retrieveReviews(aid);
	}
	
	/**
	 * Adds a review to an album given the following parameters:
	 * 
	 * @param aid
	 * @param username
	 * @param rating
	 * @param review
	 * @throws Exception
	 */
	public void putReview(int aid, String username, int rating, String review) throws Exception {
		// We ran out of time to implement protections against XSS, CSRF, SQL injection, etc.
		String sanitUser = sanitizeForSql(username);
		String sanitRev = sanitizeForSql(review);
		
		if (aid < 0 || aid > 10000) {
			throw new IllegalArgumentException();
		}
		if (rating < 0 || rating > 5) {
			throw new IllegalArgumentException();
		}
		if (username.length() > 25 || sanitUser.length() > 25) {
			throw new IllegalArgumentException();
		}
		if (review.length() > 499 || sanitRev.length() > 499) {
			throw new IllegalArgumentException();
		}
		
		System.out.println(aid);
		System.out.println(sanitUser);
		System.out.println(rating);
		System.out.println(sanitRev);		
		
		int revCreated = revDao.createReview(aid, sanitUser, rating, sanitRev);
		if (revCreated != 1) {
			System.err.println("wtf yo " + revCreated + " reviews created"); // "Proper" error logging
		}
	}

	/**
	 * @return error status 
	 */
	public String getError() {
		return Error;
	}

	/**
	 * 
	 * 
	 * @param namePrefix
	 * @return
	 * Returns null if invalid input, else poorly sanitizes for SQL injection.
	 * Why doesn't it work? Because I'm dumb, lazy and the CompSec program here is garbo.
	 */
	private static String sanitizeForSql(String input) {
		String returnPrefix = input;
		
		// Holy crap I hate regex
		returnPrefix = returnPrefix.replaceAll("('.*')|(-([%_*\"]|('.*'))-)|(([%_*\"]|(--))*)", "");
		
		return returnPrefix;
  }
  
	/**
	 * Retrieves album(s) given a category 
	 * 
	 * @param cat
	 * @return albums that belong to given category 
	 * @throws SQLException
	 */
	public Map<String, AlbumBean> retrievAlbumsByCat(String cat) throws SQLException {
		// TODO Auto-generated method stub
		return alDao.retrieveAlbumByCat(cat);
	}
	
	/**
	 * Adds album to music store's inventory with the following parameters:
	 * 
	 * @param aid
	 * @param artist
	 * @param title
	 * @param category
	 * @param price
	 * @param picture
	 * @throws Exception
	 */
	public void addAlbum(int aid, String artist, String title, String category, Float price, String picture) throws Exception
	{	
		aid = 0; // this will be set dynamically by the Album DAO 
		if(artist.equals("") || artist == null)
		{
			Error = "Artist name must not be empty";
			throw new IllegalArgumentException();
		}
		else if(title.equals("") || title == null)
		{
			Error = "Title must not be empty";
			throw new IllegalArgumentException();
		}
		else if(category.equals("") || category == null)
		{
			Error = "Category must not be empty";
			throw new IllegalArgumentException();
		}
		else if(price < 1.00)
		{
			Error = "Invalid number! provide a number greater than 1.00 and with 2 decimal trailing values";
			throw new IllegalArgumentException();
		}
		else if(!urlValidator(picture))
		{
			Error = "You have provided an invalid url";
		}
		else
		{
			// if successful error should be empty
			alDao.addAlbum(new AlbumBean(aid, artist, title, category, price, picture)); 
		}
	}
	
	/**
	 * Checks that a string-typed number has 2 decimal places
	 * 
	 * @param string
	 * @return true if given string has 2 decimal places
	 */
	public static boolean isNumberWith2Decimals(String string) {
	    return string.matches("^\\d+\\.\\d{2}$");
	  }
	
	/**
	 * 
	 */
	private static final String URL_REGEX =
			"^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))" +
			"(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)" +
			"([).!';/?:,][[:blank:]])?$";

	private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

	/**
	 * Validates URL by insuring it's not null and that the given string fits URL pattern
	 * 
	 * @param url
	 * @return true if given string is a valid URL
	 */
	public static boolean urlValidator(String url) {

		if (url == null) {
			return false;
		}

		Matcher matcher = URL_PATTERN.matcher(url);
		return matcher.matches();
	}

	/**
	 * ?? whats the paramter?
	 * 
	 * @param parameter
	 * @return
	 * @throws SQLException
	 */
	public Map<String, AlbumBean> retrieveAlbumByGodKnowsWhat(String parameter) throws SQLException {
		// TODO Auto-generated method stub
		if(parameter.equals("") || parameter == null)
		{
			Error = "Search parameter is empty, no result";
			throw new IllegalArgumentException();
		}
		
		return alDao.retrieveBySearch(parameter);
	}
	
	/**
	 * Retrieves all categories available at this store
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<String> retrieveAllCats() throws SQLException {
		return alDao.retrieveAllCats();
	}
}

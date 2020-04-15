package model;

import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.Album;
import bean.ReviewBean;
import dao.AlbumDAO;
import dao.ReviewDAO;

public class MusicStore {
	private AlbumDAO alDao;
	private ReviewDAO revDao;
	private static MusicStore instance;
	private static String Error = "";

	private MusicStore() {
		try {
			this.alDao = new AlbumDAO();
			this.revDao = new ReviewDAO();
		} catch (Exception e) {
			System.out.println("Failed to retrieve ALBUM Data Access Object");

		}
	}

	public static MusicStore getInstance() throws ClassNotFoundException {
		if (instance == null) {
			instance = new MusicStore();
			instance.alDao = new AlbumDAO();
		}
		return instance;
	}

	public Map<String, Album> retrievAlbum(int aid) throws Exception {
		if (aid < 0 || aid > 10000) {
			throw new IllegalArgumentException();
		}
		Error = "";
		return alDao.retrieveAll(aid);
	}
	
	public Map<String, Album> retrieveAlbum(int aid) throws Exception {
		return retrievAlbum(aid);
	}
	
	public Album retrieveAlbumByID(int aid) throws Exception {
		return alDao.retrieveAlbum(aid);
	}
	
	public Map<String, ReviewBean> retrieveReviews(int aid) throws Exception {
		if (aid < 0 || aid > 10000) {
			throw new IllegalArgumentException();
		}
		Error = "";
		return revDao.retrieveReviews(aid);
	}
	
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
		if (review.length() > 25 || sanitRev.length() > 25) {
			throw new IllegalArgumentException();
		}
		
		int revCreated = revDao.createReview(aid, sanitUser, rating, sanitRev);
		if (revCreated != 1) {
			System.err.println("wtf yo " + revCreated + " reviews created"); // "Proper" error logging
		}
	}

	public String getError() {
		return Error;
	}

	/**
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
  
	public Map<String, Album> retrievAlbumsByCat(String cat) throws SQLException {
		// TODO Auto-generated method stub
		return alDao.retrieveAlbumByCat(cat);
	}
	
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
			alDao.addAlbum(new Album(aid, artist, title, category, price, picture)); 
		}
	}
	
	public static boolean isNumberWith2Decimals(String string) {
	    return string.matches("^\\d+\\.\\d{2}$");
	  }
	
	private static final String URL_REGEX =
			"^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))" +
			"(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)" +
			"([).!';/?:,][[:blank:]])?$";

	private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

	public static boolean urlValidator(String url) {

		if (url == null) {
			return false;
		}

		Matcher matcher = URL_PATTERN.matcher(url);
		return matcher.matches();
	}

	public Map<String, Album> retrieveAlbumByGodKnowsWhat(String parameter) throws SQLException {
		// TODO Auto-generated method stub
		if(parameter.equals("") || parameter == null)
		{
			Error = "Search parameter is empty, no result";
			throw new IllegalArgumentException();
		}
		
		return alDao.retrieveBySearch(parameter);
	}
}

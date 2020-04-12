package model;

import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Pattern;

import bean.AccountBean;
import bean.Album;
import bean.ProfileBean;
import bean.ReviewBean;
import dao.AlbumDAO;
import dao.ReviewDAO;
import dao.UserDAO;

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
		return alDao.retrieve(aid);
	}
	
	public Map<String, Album> retrieveAlbum(int aid) throws Exception {
		return retrievAlbum(aid);
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
}

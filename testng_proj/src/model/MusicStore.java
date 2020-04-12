package model;

import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.AccountBean;
import bean.Album;
import bean.ProfileBean;
import dao.AlbumDAO;
import dao.UserDAO;

public class MusicStore {
	private AlbumDAO alDao;
	private static MusicStore instance;
	private static String Error = "";

	private MusicStore() {
		try {
			this.alDao = new AlbumDAO();
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

	public String getError() {
		return Error;
	}

	public Map<String, Album> retrievAlbumsByCat(String cat) throws SQLException {
		// TODO Auto-generated method stub
		return alDao.retrieveAlbumByCat(cat);
	}
	
	public void addAlbum(int aid, String artist, String title, String category, Float price, String picture) throws SQLException
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
		else if(price < 1.00  || !isNumberWith2Decimals(price.toString()))
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
}

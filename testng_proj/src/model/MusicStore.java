package model;

import java.sql.SQLException;
import java.util.Map;
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

}

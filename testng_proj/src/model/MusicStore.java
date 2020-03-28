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
	private UserDAO userDao;
	private static MusicStore instance;
	private static String Error = "";

	private MusicStore() {
		try {
			this.alDao = new AlbumDAO();
		} catch (Exception e) {
			System.out.println("Failed to retrieve ALBUM Data Access Object");

		}
		try {
			this.userDao = new UserDAO();
		} catch (Exception e) {
			System.out.println("Failed to retrieve USER Data Access Object");
		}
	}

	public static MusicStore getInstance() throws ClassNotFoundException {
		if (instance == null) {
			instance = new MusicStore();
			instance.alDao = new AlbumDAO();
			instance.userDao = new UserDAO();
		}
		return instance;
	}

	public String registerUser(String fname, String lname, String userName, String email, String password)
			throws Exception {
		if (fname == null || fname.equals("") || lname == null || lname.equals(""))// do validation
		{
			Error = "Name cannot be empty!";
			throw new IllegalArgumentException();
		} else if (userName == null || userName.contentEquals("")) {
			Error = "Username cannot be empty!";
			throw new IllegalArgumentException();
		} else if (!isValidEmail(email)) {
			Error = "Invalid Email Address!";
			throw new IllegalArgumentException();
		} else if (!isUniqueUsername(userName)) {
			Error = "Username already taken";
			throw new IllegalArgumentException();
		} else if (!isUniqueEmail(email)) {
			Error = "Username already taken";
			throw new IllegalArgumentException();
		} else if (password == null || password.contentEquals("") || password.length() < 6) {
			Error = "Password must be at least 6 characters long!";
			throw new IllegalArgumentException();
		} else {
			System.out.println("Seems all is well with the inputs...");
			Error = "";
			AccountBean newUser = new AccountBean(fname, lname, userName, email, password);
			return userDao.registerUser(newUser);
		}

	}

	public Map<String, ProfileBean> loginUser(String username, String password) throws Exception {
	
			if (!isUniqueUsername(username)) // if usenrame already exists in db
			{
				// validate password for that user
				String pass = userDao.getUserPass(username);
				if (pass.equals(password)) {
					Error = "";
					return this.retrieveAccountByUsername(username);
				}
				else
				{
					Error = "Password does not match!";
					throw new Exception(Error);
				}
			}
			else
			{
				Error = "User does not exist!";
				throw new Exception(Error);
			}
		}

	private boolean isUniqueEmail(String email) {
		try {
			return userDao.uniqueEmail(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Error = "Email not unique!";
			System.out.println("Email not unique!");

			return false;
		}
	}

	private boolean isUniqueUsername(String userName) {
		// TODO Auto-generated method stub
		try {
			return userDao.uniqueUserName(userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("UserName  not unique!");
			Error = "UserName  not unique!";
			return false;
		}
	}

	public Map<String, Album> retrievAlbum(int aid) throws Exception {
		if (aid < 0 || aid > 10000) {
			throw new IllegalArgumentException();
		}
		Error = "";
		return alDao.retrieve(aid);
	}

	public Map<String, ProfileBean> retrieveAccountByUsername(String username) throws Exception {
		if (username.length() < 2 || username.equals("")) {
			Error = "Sorry, this is not a valid username";
			throw new IllegalArgumentException();
		}
		Error="";
		return userDao.retrieveByUsername(username);
	}

	public Map<String, ProfileBean> retrieveAccountByEmail(String email) throws Exception {
		if (!isValidEmail(email)) {
			Error = "Sorry, this is not a valid email address";
			throw new IllegalArgumentException();
		}
		Error = "";
		return userDao.retrieveByEmail(email);
	}

	public String getError() {
		return Error;
	}

	public static boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}
}

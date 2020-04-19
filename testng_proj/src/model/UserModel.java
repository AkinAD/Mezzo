package model;

import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Pattern;

import bean.AccountBean;
import bean.AddressBean;
import bean.CheckoutProfileBean;
import bean.ProfileBean;
import dao.AddressDAO;
import dao.UserDAO;

/**
 * Profile/account model
 * 
 * @author Akin x Alan
 *
 */
public class UserModel {
	public static final String ADMIN_PRIV = "admin";
	public static final String CUSTOMER_PRIV = "customer";
	public static final String PARTNER_PRIV = "partner";
	
	private static final UserModel INSTANCE = new UserModel();
	
	private static String Error = "";
	private UserDAO userDao;
	private AddressDAO addressDao;
	
	public UserModel() {
		try {
			this.userDao = new UserDAO();
			this.addressDao = new AddressDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static UserModel getInstance() {
		return INSTANCE;
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
			System.out.println("UserModel: Seems all is well with the inputs...");
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
	
	/**
	 * 
	 * @param username
	 * @return
	 * A map with a single element that is the ProfileBean. Empty if not found.
	 */
	public Map<String, ProfileBean> retrieveAccountByUsername(String username) throws IllegalArgumentException, SQLException {
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
	
	public AddressBean retrieveBillingAddressByUsername(String username) throws Exception {
		if (username.length() < 2 || username.equals("")) {
			Error = "Sorry, this is not a valid username";
			throw new IllegalArgumentException();
		}
		Error="";
		return addressDao.retrieveBillingAddr(username);
	}
	
	public CheckoutProfileBean retrieveCheckoutProfileByUsername(String username) throws Exception {
		CheckoutProfileBean returnValue = new CheckoutProfileBean();
		returnValue.setBillingAddress(retrieveBillingAddressByUsername(username));
		returnValue.setProfile(retrieveAccountByUsername(username).values().iterator().next());
		return returnValue;
	}
	
	/**
	 * 
	 * @param username
	 * @param street
	 * @param province
	 * @param country
	 * @param zip
	 * @param phone
	 * May be null or empty
	 * @throws Exception
	 */
	public void updateBillingAddressByUsername(String username, String street, String province, String country, String zip, String phone) throws Exception {
		if (username.length() < 2 || username.equals("")) {
			Error = "Sorry, this is not a valid username";
			throw new IllegalArgumentException();
		} else if (street == null || street.isEmpty() || street.length() > 100) {
			Error = "Invalid entry";
			throw new IllegalArgumentException();
		} else if (province == null || province.isEmpty() || province.length() > 25) {
			Error = "Invalid entry";
			throw new IllegalArgumentException();
		} else if (country == null || country.isEmpty() || country.length() > 24) {
			Error = "Invalid entry";
			throw new IllegalArgumentException();
		} else if (zip == null || zip.isEmpty() || zip.length() > 20) {
			Error = "Invalid entry";
			throw new IllegalArgumentException();
		} else if (!(phone == null || phone.isEmpty()) && phone.length() > 20) {
			Error = "Invalid entry";
			throw new IllegalArgumentException();
		}
		Error="";
		
		// Retrieve current billing address or make a new one if non-existent
		AddressBean curAddr;
		curAddr = retrieveBillingAddressByUsername(username);
		if (curAddr == null) {
			curAddr = new AddressBean(username, "", "", "", "", "", AddressBean.BILLING);
		}		
		
		curAddr.setStreet(street);
		curAddr.setProvince(province);
		curAddr.setCountry(country);
		curAddr.setZip(zip);
		if (phone != null && !phone.isEmpty()) {
			curAddr.setPhone(phone);
		} else {
			curAddr.setPhone("");
		}
		
		addressDao.updateAddr(curAddr);
	}
	
	private boolean isUniqueEmail(String email) {
		try {
			return userDao.uniqueEmail(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Error = "Email not unique!";
			System.out.println("UserModel: Email not unique!");

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
			System.out.println("UserModel: UserName not unique!");
			Error = "UserName  not unique!";
			return false;
		}
	}
	
	public static boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}

	public Object getError() {
		return Error;
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 * Returns null if invalid input, else poorly sanitizes for SQL injection.
	 * Why doesn't it work? Because I'm dumb, lazy and the CompSec program here is garbo.
	 * 
	 * @author alanyork
	 * 
	 */
	private static String sanitizeForSql(String input) {
		String returnPrefix = input;
		
		// Holy crap I hate regex
		returnPrefix = returnPrefix.replaceAll("('.*')|(-([%_*\"]|('.*'))-)|(([%_*\"]|(--))*)", "");
		
		return returnPrefix;
	}

	public boolean updatePrivilege(String user, String POWER) throws SQLException {
		if(user == null || user.isEmpty())
		{
			Error = "Username cannot be empty";
			return false;
		}
		else if(POWER == null || POWER.isEmpty())
		{
			Error ="Privilege cannot be empty";
			return false;
		}
		else
		return userDao.updatePrivilege(user, POWER);
		
	}
}

package bean;

/**
 * Account bean
 */
public class AccountBean {
	
	private String fname;
	private String lname;
	private String userName;
	private String email;
	private String password;

	/**
	 * Initializes the account information
	 * 
	 * @param fname
	 * @param lname
	 * @param userName
	 * @param email
	 * @param password
	 */
	public AccountBean(String fname, String lname, String userName, String email, String password) {
		this.setFname(fname);
		this.setLname(lname);
		this.setUserName(userName);
		this.setEmail(email);
		this.setPassword(password);
	}

	/**
	 * @return the first name of the account
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * Sets the first name of the account
	 * 
	 * @param fname
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the last name of the account
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * Sets the last name of the account
	 * 
	 * @param lname
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * @return email address of the account
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the account
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the user name of the account
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name of the account
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password of the account
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the account
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}

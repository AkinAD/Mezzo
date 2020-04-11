package bean;

/**
 * Profile bean
 */
public class ProfileBean {
	
	private String fname;
	private String lname;
	private String userName;
	private String email;
	private String privilege;
	
	/**
	 * Initializes the profile
	 * 
	 * @param fname
	 * @param lname
	 * @param userName
	 * @param email
	 * @param privilege
	 */
	public ProfileBean(String fname, String lname, String userName, String email, String privilege) {
		this.setFname(fname);
		this.setLname(lname);
		this.setUserName(userName);
		this.setEmail(email);
		this.setPriviledge(privilege);
	}

	/**
	 * @return the first name of the profile
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * Sets the first name of the profile
	 * 
	 * @param fname
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the last name of the profile
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * Sets the last name of the profile
	 * 
	 * @param lname
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * @return the user name of the profile
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name of the profile
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the email address of the profile
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the profile
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the privilege of the profile
	 */
	public String getPrivilege() {
		return privilege;
	}

	/**
	 * Sets the privilege of the profile
	 * 
	 * @param privilege
	 */
	public void setPriviledge(String privilege) {
		this.privilege = privilege;
	}

	

}

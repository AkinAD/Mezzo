package bean;

public class AccountBean {
	
	private String fname;
	private String lname;
	private String userName;
	private String email;
	private String password;

	public AccountBean(String fname, String lname, String userName, String email, String password) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

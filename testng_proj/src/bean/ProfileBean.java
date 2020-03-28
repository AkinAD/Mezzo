package bean;

public class ProfileBean {
	
	private String fname;
	private String lname;
	private String userName;
	private String email;
	private String privilege;
	
	public ProfileBean(String fname, String lname, String userName, String email, String privilege) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.userName = userName;
		this.email = email;
		this.privilege = privilege;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPriviledge(String privilege) {
		this.privilege = privilege;
	}

	

}

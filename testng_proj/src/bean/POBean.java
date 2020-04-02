package bean;

import java.text.SimpleDateFormat;

public class POBean {
	private String PO_id;
	private String username;
	private String status;
	private AddressBean address;
	private String PO_date;
	private String lname;
	private String fname;

	private static SimpleDateFormat idf = new SimpleDateFormat("yyyyMMddHHmmSS");
	private static SimpleDateFormat datef = new SimpleDateFormat("yyyyMMdd");

	public final static String ORDERED = "ORDERED";
	public final static String PROCESSED = "PROCESSED";
	public final static String DENIED = "DENIED";
	
	public POBean(String pO_id, String username, String status, AddressBean address, String pO_date, String lname,
			String fname) {
		super();
		PO_id = pO_id;
		this.username = username;
		this.status = status;
		this.address = address;
		PO_date = pO_date;
		this.lname = lname;
		this.fname = fname;
	}

	public String getPO_id() {
		return PO_id;
	}

	public void setPO_id(String pO_id) {
		PO_id = pO_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AddressBean getAddress() {
		return address;
	}

	public void setAddress(AddressBean address) {
		this.address = address;
	}

	public String getPO_date() {
		return PO_date;
	}

	public void setPO_date(String pO_date) {
		PO_date = pO_date;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String toString() {
		return "PO_id: " + PO_id
				+ "\nusername: " + username
				+ "\nstatus" + status
				+ "\naddress: " +  address.toString()
				+ "\nPO_date: " +  PO_date
				+ "\nlname: " +   lname
				+ "\nfname: " +   fname;
	}
}

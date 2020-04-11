package bean;

import java.text.SimpleDateFormat;

/**
 * Purchase order bean
 */
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
	
	/**
	 * Initializes the Purchase order
	 * 
	 * @param pO_id
	 * @param username
	 * @param status
	 * @param address
	 * @param pO_date
	 * @param lname
	 * @param fname
	 */
	public POBean(String pO_id, String username, String status, AddressBean address, String pO_date, String lname,
			String fname) {
		this.setPO_id(pO_id);
		this.setUsername(username);
		this.setStatus(status);
		this.setAddress(address);
		this.setPO_date(pO_date);
		this.setLname(lname);
		this.setFname(fname);
	}

	/**
	 * @return the purchase order id
	 */
	public String getPO_id() {
		return PO_id;
	}

	/**
	 * Sets the purchase order id to the given id
	 * 
	 * @param pO_id
	 */
	public void setPO_id(String pO_id) {
		PO_id = pO_id;
	}

	/**
	 * @return user name
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the user name to the given user name
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the status of the purchase order
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status of the purchase order to the given status
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the address
	 */
	public AddressBean getAddress() {
		return address;
	}

	/**
	 * Sets the address of the purchase order
	 * 
	 * @param address
	 */
	public void setAddress(AddressBean address) {
		this.address = address;
	}

	/**
	 * @return purchase order date
	 */
	public String getPO_date() {
		return PO_date;
	}

	/**
	 * Sets the purchase order date
	 * 
	 * @param pO_date
	 */
	public void setPO_date(String pO_date) {
		PO_date = pO_date;
	}

	/**
	 * @return the last name of the user
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * Sets the last name of the user
	 * 
	 * @param lname
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	/**
	 * @return the first name of the user
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * Sets the first name of the user
	 * 
	 * @param fname
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	/**
	 * Print out the purchase order as a string
	 */
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

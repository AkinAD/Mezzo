package bean;

/**
 * Address Bean
 */
public class AddressBean {

	private String username;
	private String street;
	private String province;
	private String country;
	private String zip;
	private String phone;
	private String type;
	
	public static String BILLING = "Billing";
	public static String SHIPPING = "Shipping";
	
	/**
	 * Initializes the address information
	 * 
	 * @param username
	 * @param street
	 * @param province
	 * @param country
	 * @param zip
	 * @param phone
	 * @param type
	 */
	public AddressBean(String username, String street, String province, String country, String zip, String phone,
			String type) {
		this.setUsername(username);
		this.setStreet(street);
		this.setProvince(province);
		this.setCountry(country);
		this.setZip(zip);
		this.setPhone(phone);
		this.setType(type);
	}

	/**
	 * @return the user name
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the user name
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the street of the address
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Sets the street of the address
	 * 
	 * @param street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the province of the address
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * Sets the province of the address
	 * 
	 * @param province
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the country of the address
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country of the address
	 * 
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the zip of the address
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * Sets the zip of the address
	 * 
	 * @param zip
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the phone number of the address
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone number of the address
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the type of the address
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type of the address
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Prints the address information as a string
	 */
	public String toString() {
		return "Username: " + username
				+ "\nStreet: " + street
				+ "\nProvince: " + province
				+ "\nCountry: " + country
				+ "\nZip: " + zip
				+ "\nPhone: " + phone
				+ "\nType: " + type;
	}

}

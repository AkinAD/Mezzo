package bean;

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
	
	public AddressBean(String username, String street, String province, String country, String zip, String phone,
			String type) {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
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

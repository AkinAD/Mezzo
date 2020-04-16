package bean;

public class CheckoutProfileBean {
	private ProfileBean profile;
	private AddressBean billingAddress;
	
	/**
	 * @return the profile
	 */
	public ProfileBean getProfile() {
		return profile;
	}
	/**
	 * @param profile the profile to set
	 */
	public void setProfile(ProfileBean profile) {
		this.profile = profile;
	}
	/**
	 * @return the billingAddress
	 */
	public AddressBean getBillingAddress() {
		return billingAddress;
	}
	/**
	 * @param billingAddress the billingAddress to set
	 */
	public void setBillingAddress(AddressBean billingAddress) {
		this.billingAddress = billingAddress;
	} 
}

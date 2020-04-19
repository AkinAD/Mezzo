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
	 * Set profile to the given profile
	 * 
	 * @param profile 
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
	 * Sets the billing address
	 * 
	 * @param billingAddress 
	 */
	public void setBillingAddress(AddressBean billingAddress) {
		this.billingAddress = billingAddress;
	} 
}

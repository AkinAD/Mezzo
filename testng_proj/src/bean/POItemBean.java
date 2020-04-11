package bean;

/**
 * Purchase Order item bean
 */
public class POItemBean {
	private String PO_id;
	private String bid;
	private int quantity;
	
	/**
	 * Sets the purchase order id, bid, and quantity
	 * 
	 * @param pO_id
	 * @param bid
	 * @param quantity
	 */
	public POItemBean(String pO_id, String bid, int quantity) {
		super();
		this.setPO_id(pO_id);
		this.setBid(bid);
		this.setQuantity(quantity);
	}
	
	/**
	 * @return purchase order id
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
	 * @return the bid
	 */
	public String getBid() {
		return bid;
	}

	/**
	 * Sets the bid to the given bid
	 * 
	 * @param bid
	 */
	public void setBid(String bid) {
		this.bid = bid;
	}

	
	/**
	 * @return the quantity of the purchase order
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity to the given quantity
	 * 
	 * @param quantity 
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}

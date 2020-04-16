package bean;

/**
 * Purchase Order item bean
 */
public class POItemBean {
	private String PO_id;
	private int aid;
	private int quantity;
	
	/**
	 * Sets the purchase order id, bid, and quantity
	 * 
	 * @param pO_id
	 * @param aid
	 * @param quantity
	 */
	public POItemBean(String pO_id, int aid, int quantity) {
		super();
		this.setPO_id(pO_id);
		this.setAid(aid);
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
	public int getAid() {
		return aid;
	}

	/**
	 * Sets the bid to the given bid
	 * 
	 * @param bid
	 */
	public void setAid(int aid) {
		this.aid = aid;
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

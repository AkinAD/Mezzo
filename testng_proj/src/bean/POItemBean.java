package bean;

public class POItemBean {
	private String PO_id;
	private String bid;
	private int quantity;
	
	public POItemBean(String pO_id, String bid, int quantity) {
		super();
		PO_id = pO_id;
		this.bid = bid;
		this.quantity = quantity;
	}
	
	public String getPO_id() {
		return PO_id;
	}

	public void setPO_id(String pO_id) {
		PO_id = pO_id;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	

	
}

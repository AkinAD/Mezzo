package bean;

public class CartItemBean {
	private int qty;
	private AlbumBean item;
	private float effectivePrice;
	
	public CartItemBean(int qty, AlbumBean item){
		this.qty = qty;
		this.item = item;
		this.effectivePrice = (float)qty*item.getPrice();
	}
	
	/**
	 * @return the qty
	 */
	public int getQty() {
		return qty;
	}
	/**
	 * @param qty the qty to set
	 */
	public void setQty(int qty) {
		this.qty = qty;
	}
	/**
	 * @return the item
	 */
	public AlbumBean getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(AlbumBean item) {
		this.item = item;
	}

	/**
	 * @return the effectivePrice
	 */
	public float getEffectivePrice() {
		return effectivePrice;
	}

	/**
	 * @param effectivePrice the effectivePrice to set
	 */
	public void setEffectivePrice(float effectivePrice) {
		this.effectivePrice = effectivePrice;
	}
}

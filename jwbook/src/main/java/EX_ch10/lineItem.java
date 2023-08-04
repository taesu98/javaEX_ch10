package EX_ch10;

public class lineItem {
	private int id;
	private int bilId;
	private int menuId;
	private int menuQuantity;
	private bills bills;
	private creditCards creditCards;
	private coupons coupons;
	private menus menus;
	
	public bills getBills() {
		return bills;
	}
	public void setBills(bills bills) {
		this.bills = bills;
	}
	public menus getMenus() {
		return menus;
	}
	public void setMenus(menus menus) {
		this.menus = menus;
	}
	
	public creditCards getCreditCards() {
		return creditCards;
	}
	public void setCreditCards(creditCards creditCards) {
		this.creditCards = creditCards;
	}
	public coupons getCoupons() {
		return coupons;
	}
	public void setCoupons(coupons coupons) {
		this.coupons = coupons;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBilId() {
		return bilId;
	}
	public void setBilId(int bilId) {
		this.bilId = bilId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getMenuQuantity() {
		return menuQuantity;
	}
	public void setMenuQuantity(int menuQuantity) {
		this.menuQuantity = menuQuantity;
	}
	
	

}

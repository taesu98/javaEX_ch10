package EX_ch10;

public class orders {
	private int id;
	private int bilId;
	private int menuId;
	private int menuquantity;
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
	public int getMenuquantity() {
		return menuquantity;
	}
	public void setMenuquantity(int menuquantity) {
		this.menuquantity = menuquantity;
	}
	
	

}

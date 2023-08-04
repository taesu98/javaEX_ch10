package EX_ch10;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class bills {
	private int id;
	private int sellingprice;
	private int price;
	private Date orderDate;
	private int cardId;
	private int couponId;
	private creditCards creditCards;
	private coupons coupons;
	private menus menus;
	private orders orders;
//	private orders[] ordersList;
	
	public bills() {
//		ordersList = new orders[0];
	}
	
//	public orders getOrders() {
//		return orders;
//	}
//
//	public orders[] getOrdersList() {
//		return ordersList;
//	}
//
//	public void setOrdersList(orders[] ordersList) {
//		this.ordersList = ordersList;
//	}

	public void setOrders(orders orders) {
		this.orders = orders;
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
	public int getSellingprice() {
		return sellingprice;
	}
	public void setSellingprice(int sellingprice) {
		this.sellingprice = sellingprice;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public int getCouponId() {
		return couponId;
	}
	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}	
	
}

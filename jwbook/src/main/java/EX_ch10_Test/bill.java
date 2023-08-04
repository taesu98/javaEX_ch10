package EX_ch10_Test;

import java.util.*;

import EX_ch10.lineItem;



public class bill {
	private int id;
	private int cardId;
	private int couponId;
	private int price;
	private int discountPrice;
	private Date billDate;
	private lineItems[] lineItems;
	
	public bill() {
		lineItems = new lineItems[0];
	}
	public lineItems[] getLineItems() {
		return lineItems;
	}
	public void setLineItems(lineItems[] lineItems) {
		this.lineItems = lineItems;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
}

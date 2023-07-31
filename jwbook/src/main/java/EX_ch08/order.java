package EX_ch08;

import java.util.Date;

public class order {
	private int id;
	private int customerid;
	private int bookid;
	private int sellingprice;
	private String orderdate;
	
	public order(int id, int customerid, int bookid, int sellingprice ,String orderdate) {
		this.id = id;
		this.customerid = customerid;
		this.bookid = bookid;
		this.sellingprice = sellingprice;
		this.orderdate = orderdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public int getSellingprice() {
		return sellingprice;
	}

	public void setSellingprice(int sellingprice) {
		this.sellingprice = sellingprice;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrerdate(String orderdate) {
		this.orderdate = orderdate;
	}
	
}

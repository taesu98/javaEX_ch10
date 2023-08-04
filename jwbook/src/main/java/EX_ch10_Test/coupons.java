package EX_ch10_Test;

public class coupons {
	private int id;
	private String title;
	private int discount;
	private boolean doudleDiscount;
	private String discountType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public boolean isDoubleDiscount() {
		return doudleDiscount;
	}
	public void setDoudleDiscount(boolean doudleDiscount) {
		this.doudleDiscount = doudleDiscount;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	
}

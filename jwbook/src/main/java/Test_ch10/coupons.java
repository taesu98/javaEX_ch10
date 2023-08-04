package Test_ch10;

import org.json.simple.JSONObject;

public class coupons {
	private int id;
	private String title;
	private int discount;
	private boolean doubleDiscount;
	private String discountType;
	
	public String toJsonString() {
        String rtn = null;

        JSONObject jo = new JSONObject();
        jo.put("id", id);
        jo.put("title", title);
        jo.put("discount", discount);
        jo.put("discountType", discountType);
        jo.put("doubleDiscount", doubleDiscount);

        rtn = jo.toString();

        return rtn;
    }
	
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
		return doubleDiscount;
	}
	public void setDoudleDiscount(boolean doudleDiscount) {
		this.doubleDiscount = doudleDiscount;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	
}

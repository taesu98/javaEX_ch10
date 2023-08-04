package Test_ch10;

import org.json.simple.JSONObject;

public class creditCards {
	private int id;
	private String cardType;
	private String cardName;
	private int discount;
	private String discountType;
	
	public String toJsonString() {
        String rtn = null;

        JSONObject jo = new JSONObject();
        jo.put("id", id);
        jo.put("name", cardName);
        jo.put("discount", discount);

        rtn = jo.toString();

        return rtn;
    }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
}

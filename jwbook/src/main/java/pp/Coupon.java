package pp;

import org.json.simple.JSONObject;

public class Coupon {

    private long id;
    private String title;
    private Integer discount;
    private String discountType;
    private Boolean doubleDiscount;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Boolean isDoubleDiscount() {
        return doubleDiscount;
    }

    public void setDoubleDiscount(Boolean doubleDiscount) {
        this.doubleDiscount = doubleDiscount;
    }
}

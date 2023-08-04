package Test_ch10;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import EX_ch10.lineItem;
import pp.Bill;
import pp.Card;
import pp.Coupon;
import pp.LineItem;
import pp.Menu;

public class restaurantService {
	restaurantDAO restaurantDAO;
	
	public restaurantService() {
		restaurantDAO = new restaurantDAO();
	}
	
	public List<menus> getMenus(){
		List<menus> rtn = new ArrayList<>();
		rtn = restaurantDAO.getMenus();
		return rtn;
	}
	
	 public List<creditCards> getCardsByType(String cardType) {
	        List<creditCards> rtn = new ArrayList<>();

	        rtn = restaurantDAO.getCardsByType(cardType);

	        return rtn;
	    }

	    public List<creditCards> getCardsCredit() {
	        List<creditCards> rtn = new ArrayList<>();

	        rtn = restaurantDAO.getCardsCredit();

	        return rtn;
	    }

	    public List<creditCards> getCardsTelecom() {
	        List<creditCards> rtn = new ArrayList<>();

	        rtn = restaurantDAO.getCardsTelecom();

	        return rtn;
	    }

	    public List<creditCards> getCardsPoint() {
	        List<creditCards> rtn = new ArrayList<>();

	        rtn = restaurantDAO.getCardsPoint();

	        return rtn;
	    }

	    public List<creditCards> getCardsOkcashbag() {
	        List<creditCards> rtn = new ArrayList<>();

	        rtn = restaurantDAO.getCardsOkcashbag();

	        return rtn;
	    }

	    public List<coupons> getCoupons() {
	        List<coupons> rtn = new ArrayList<>();

	        rtn = restaurantDAO.getCoupons();

	        return rtn;
	    }
	    
	    public void addBill(bill bill) {
	        creditCards card = restaurantDAO.getCardById(bill.getCardId());
	        coupons coupon = restaurantDAO.getCouponById(bill.getCouponId());
	        List<Long> menuIds = new ArrayList<>();
	        for (lineItems lineItem : bill.getLineItems()) {
	            menuIds.add(lineItem.getMenuId());
	        }
	        String ids = StringUtils.join(menuIds, ",");
	        Map<Integer, menus> menuMap = restaurantDAO.getMenusByIds(ids);

	        int price = 0;
	        for (lineItems lineItem : bill.getLineItems()) {
	            price += menuMap.get(lineItem.getMenuId()).getPrice() * lineItem.getMenuQty();
	        }

	        int discountPrice = discountPrice(price, card, coupon);

	        bill.setPrice(price);
	        bill.setDiscountPrice(discountPrice);

	        restaurantDAO.addBill(bill);
	    }
	    
	    private int discountPrice(int price, creditCards card, coupons coupon) {
	        int rtn = price;

	        if (coupon != null) {
	            if (coupon.isDoubleDiscount()) {
	                if (coupon.getDiscountType().equals("%")) {
	                    rtn -= (rtn * coupon.getDiscount() / 100);
	                } else if (coupon.getDiscountType().equals("W")) {
	                    rtn -= coupon.getDiscount();
	                }
	                if (card != null) {
	                    rtn -= (rtn * card.getDiscount() / 100);
	                } else {
	                    rtn = rtn;
	                }
	            } else {
	                if (coupon.getDiscountType().equals("%")) {
	                    rtn -= (rtn * coupon.getDiscount() / 100);
	                } else if (coupon.getDiscountType().equals("W")) {
	                    rtn -= coupon.getDiscount();
	                }
	            }
	        } else {
	            if (card != null) {
	                rtn -= (rtn * card.getDiscount() / 100);
	            } else {
	                rtn = rtn;
	            }
	        }

	        return rtn;
	    }

	    public List<Object[]> getBills() {
	        List<Object[]> rtn = new ArrayList<>();

	        rtn = restaurantDAO.getBills();

	        return rtn;
	    }
}

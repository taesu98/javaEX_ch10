package EX_ch10_Test;

import java.util.List;

import EX_ch10.lineItem;

public class restaurantService {
restaurantDAO restaurantDAO;
	
	public restaurantService() {
		restaurantDAO = new restaurantDAO();
	} // RestaurantService
	
	public void calc(bill bill) {
		lineItems[] lineItems = bill.getLineItems();
		int cardId = bill.getCardId();
		int couponId = bill.getCouponId();
		
		
		
		
		List<creditCards> selectCard = null;
		if(cardId != -1) {
			selectCard = restaurantDAO.selectCreditCardById(cardId);
		}
		
		List<coupons> selectCoupon = null;
		if(couponId != -1) {
			selectCoupon = restaurantDAO.selectCouponById(couponId);
		}
		
		List<menus> menus = restaurantDAO.selectMenu();		
		int price = 0;
		for(lineItems item : lineItems) {
			for(menus menu : menus) {
				if(item.getMenuId() == menu.getId()) {
					price += menu.price * item.getMenuQty();
				}
			}
		}

		bill.setPrice(price);
		
		
	  int discountPrice = price;
		int cardDiscount = 0;
		
		if(selectCard != null) {
			cardDiscount = selectCard.get(0).getDiscount();
		}
		
		// 쿠폰 o
		if(selectCoupon != null) {
			// 중복 o
			if (selectCoupon.get(0).isDoubleDiscount() == true) {
				if(selectCoupon.get(0).getDiscountType().equals("%")) {
					//discountPrice =	discountPrice * (1 - Integer.parseInt(selectCoupon.get(0).getDiscount()) / 100) * (1 - cardDiscount / 100);
					discountPrice = (int) (discountPrice * (1 - selectCoupon.get(0).getDiscount() / 100.0));
					System.out.println("중복가능/%");
					System.out.println(discountPrice);
				}else {
					discountPrice = (int) ((discountPrice - (selectCoupon.get(0).getDiscount())) * (1 - cardDiscount / 100.0));
					System.out.println("중복가능/''");
				}
			}else{
			// 중복 x
				if(selectCoupon.get(0).getDiscountType().equals("%")) {
					discountPrice = (int) Math.min(discountPrice * (selectCoupon.get(0).getDiscount() / 100.0), discountPrice * (1 - cardDiscount / 100.0));
					System.out.println("중복불가능/%");
				}else {
					discountPrice = (int) Math.min(discountPrice - selectCoupon.get(0).getDiscount(), discountPrice * (1 - cardDiscount / 100.0));
					System.out.println("중복불가능/''");
				}
			}
		} else {
			// 쿠폰 x
			discountPrice = (int) (discountPrice - (discountPrice * cardDiscount) / 100.0);
			System.out.println("쿠폰없음");
		}
		
		bill.setDiscountPrice(discountPrice);
		restaurantDAO.insertBill(bill);

	}
	
	public List<Object[]> get(){
		return restaurantDAO.selectBill();
	}
	
	
}

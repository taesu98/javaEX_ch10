package pp;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class RestaurantService {

    private RestaurantDAO restaurantDao;

    public RestaurantService() {
        restaurantDao = new RestaurantDAO();
    }

    public List<Menu> getMenus() {
        List<Menu> rtn = new ArrayList<>();

        rtn = restaurantDao.getMenus();

        return rtn;
    }

    public List<Card> getCardsByType(String type) {
        List<Card> rtn = new ArrayList<>();

        rtn = restaurantDao.getCardsByType(type);

        return rtn;
    }

    public List<Card> getCardsCredit() {
        List<Card> rtn = new ArrayList<>();

        rtn = restaurantDao.getCardsCredit();

        return rtn;
    }

    public List<Card> getCardsTelecom() {
        List<Card> rtn = new ArrayList<>();

        rtn = restaurantDao.getCardsTelecom();

        return rtn;
    }

    public List<Card> getCardsPoint() {
        List<Card> rtn = new ArrayList<>();

        rtn = restaurantDao.getCardsPoint();

        return rtn;
    }

    public List<Card> getCardsOkcashbag() {
        List<Card> rtn = new ArrayList<>();

        rtn = restaurantDao.getCardsOkcashbag();

        return rtn;
    }

    public List<Coupon> getCoupons() {
        List<Coupon> rtn = new ArrayList<>();

        rtn = restaurantDao.getCoupons();

        return rtn;
    }

    public void addBill(Bill bill) {
        Card card = restaurantDao.getCardById(bill.getCardId());
        Coupon coupon = restaurantDao.getCouponById(bill.getCouponId());
        List<Long> menuIds = new ArrayList<>();
        for (LineItem lineItem : bill.getLineItems()) {
            menuIds.add(lineItem.getMenuId());
        }
        String ids = StringUtils.join(menuIds, ",");
        Map<Long, Menu> menuMap = restaurantDao.getMenusByIds(ids);

        int price = 0;
        for (LineItem lineItem : bill.getLineItems()) {
            price += menuMap.get(lineItem.getMenuId()).getPrice() * lineItem.getMenuQuantity();
        }

        int discountPrice = discountPrice(price, card, coupon);

        bill.setPrice(price);
        bill.setDiscountPrice(discountPrice);

        restaurantDao.addBill(bill);
    }

    private int discountPrice(int price, Card card, Coupon coupon) {
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

        rtn = restaurantDao.getBills();

        return rtn;
    }
}

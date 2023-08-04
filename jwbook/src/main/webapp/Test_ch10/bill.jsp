<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta name="metro4:locale" content="ko-KR" />
        <meta name="metro4:init" content="false" />

        <title>Restaurant Metro</title>

        <link rel="stylesheet" href="https://cdn.korzh.com/metroui/v4.5.1/css/metro-all.min.css" />
        <style></style>
    </head>
    <link rel="icon" href="data:;base64,iVBORw0KGgo=">

    <body>
        <div data-role="appbar">
            <ul class="app-bar-menu">
                <li><a href="<c:url value="/rControl?action=bill"/>">결제</a></li>
                <li><a href="<c:url value="/rControl?action=bills"/>">결산</a></li>
            </ul>
        </div>

        <br />
        <br />
        <br />
        <div class="container">
             <h6>고객</h6>
             <br />
             <div id="customers">
             <c:forEach var="customer" items="${customerList}">
                   <input type="radio" data-role="radio" name="customerId" value="${customer.id}" data-caption="${customer.name}"/>
             </c:forEach>
             </div>
        </div>

        <div class="container">
            <h6>메뉴</h6>
            <c:forEach var="menus" items="${menuList}">
                <button class="button menu" 
                	data-menu-id="${menus.id}" data-menu-name="${menus.menuName}" data-menu-price="${menus.price}">
                	${menus.menuName}(${menus.price}원)
                </button>
            </c:forEach>
        </div>
        <br />
        <div class="container">
            <h6>선택한 메뉴</h6>
            <form action="<c:url value="/rControl?action=addBill"/>" method="post" id="form_bill">
                <input type="hidden" name="cardId" value="-1" id="card_id" />
                <input type="hidden" name="couponId" value="-1" id="coupon_id" />
                <table class="table" data-role="table" data-show-search="false" data-show-rows-steps="false" data-show-table-info="false">
                    <thead>
                        <tr>
                            <th>메뉴명</th>
                            <th>가격</th>
                            <th>수량</th>
                            <th>합계</th>
                        </tr>
                    </thead>
                    <tbody id="selected_menus"></tbody>
                    <tfoot>
                        <tr>
                            <th colspan="3">합계</th>
                            <th><span id="price_sum">0</span></th>
                        </tr>
                    </tfoot>
                </table>
            </form>
        </div>
        <br />
        <div class="container">
            <h6>제휴/할인카드/쿠폰</h6>
            <select data-role="select" data-filter="false" id="select_credit">
                <option value="-1">신용카드 선택하세요.</option>
                <c:forEach var="creditCards" items="${creditCardList}">                
                	<option value="${creditCards.id}">${creditCards.cardName}</option>
            	</c:forEach>
                
            </select>
            <select data-role="select" data-filter="false" id="select_telecom">
                <option value="-1">통신사 선택하세요.</option>
                	<c:forEach var="creditCards" items="${telecomCardList}">                
                	<option value="${creditCards.id}">${creditCards.cardName}</option>
            	</c:forEach>
                
            </select>
            <select data-role="select" data-filter="false" id="select_point">
                <option value="-1">포인트결제 선택하세요.</option>
         		<c:forEach var="creditCards" items="${pointCardList}">                
                	<option value="${creditCards.id}">${creditCards.cardName}</option>
            	</c:forEach>
                
            </select>
            <select data-role="select" data-filter="false" id="select_okcashbag">
            	<option value="-1">OK캐시백 선택하세요.</option>
                <c:forEach var="creditCards" items="${okcashbagCardList}">                
                	<option value="${creditCards.id}">${creditCards.cardName}</option>
            	</c:forEach>
                
            </select>
            <select data-role="select" data-filter="false" id="select_coupon">
                <option value="-1">할인쿠폰 선택하세요.</option>               
                    <c:forEach var="coupons" items="${couponList}">
                	<option value="${coupons.id}">${coupons.title}</option>                
            	</c:forEach>
            </select>
        </div>
        <br />
        <div class="container" id="settle_description"></div>
        <br />
        <div class="container"><span>결제 금액: </span><span id="price_discount">0</span></div>
        <br />
        <div class="container">
            <button class="button" id="button_settle">결제</button>
        </div>

        <script src="https://cdn.korzh.com/metroui/v4.5.1/js/metro.min.js"></script>
        <script src="<c:url value="/Test_ch10/addLocale_ko_KR.js"/>"></script>
        <script>
            let cardJsonArrayString = '[{"name":"CJ ONE 삼성카드","discount":30,"id":1}, {"name":"CJ ONE 신한카드","discount":30,"id":2}, {"name":"THE CJ 카드","discount":20,"id":3}, {"name":"삼성 6 V4카드","discount":20,"id":4}, {"name":"신한 Lady카드","discount":20,"id":5}, {"name":"삼성 SFC","discount":20,"id":6}, {"name":"삼성 S클라스","discount":20,"id":7}, {"name":"하나 Yes OK Saver","discount":20,"id":8}, {"name":"홈플러스 하나줄리엣카드","discount":20,"id":9}, {"name":"하나 줄리엣카드 & Yes 4u shopping","discount":20,"id":10}, {"name":"KB Star","discount":20,"id":11}, {"name":"이마트 KB카드","discount":15,"id":12}, {"name":"KT 멤버십 일반 할인","discount":5,"id":13}, {"name":"KT 멤버십 VIP 할인","discount":15,"id":14}, {"name":"T 멤버십 실버 할인","discount":5,"id":15}, {"name":"T 멤버십 VIP\/골드 할인","discount":15,"id":16}, {"name":"OK캐시백","discount":30,"id":17}, {"name":"BC Top 포인트","discount":10,"id":18}, {"name":"기아멤버스 카드","discount":20,"id":19}, {"name":"삼성카드 포인트","discount":10,"id":20}, {"name":"현대카드 M","discount":20,"id":21}, {"name":"신한 Hi-Point 카드","discount":20,"id":22}, {"name":"블루멤버스 카드","discount":20,"id":23}]';
            let couponJsonArrayString = '[{"discount":5,"discountType":"%","id":1,"title":"5% 할인쿠폰(중복할인 가능)","doubleDiscount":true}, {"discount":10,"discountType":"%","id":2,"title":"10% 할인쿠폰(중복할인 가능)","doubleDiscount":true}, {"discount":15,"discountType":"%","id":3,"title":"15% 할인쿠폰(중복할인 가능)","doubleDiscount":true}, {"discount":5000,"discountType":"W","id":4,"title":"5,000 할인쿠폰(중복할인 가능)","doubleDiscount":true}, {"discount":10000,"discountType":"W","id":5,"title":"10,000 할인쿠폰(중복할인 가능)","doubleDiscount":true}, {"discount":20000,"discountType":"W","id":6,"title":"20,000 할인쿠폰(중복할인 가능)","doubleDiscount":true}, {"discount":5,"discountType":"%","id":7,"title":"5% 할인쿠폰(중복할인 불가능)","doubleDiscount":false}, {"discount":10,"discountType":"%","id":8,"title":"10% 할인쿠폰(중복할인 불가능)","doubleDiscount":false}, {"discount":15,"discountType":"%","id":9,"title":"15% 할인쿠폰(중복할인 불가능)","doubleDiscount":false}, {"discount":5000,"discountType":"W","id":10,"title":"5,000 할인쿠폰(중복할인 불가능)","doubleDiscount":false}, {"discount":10000,"discountType":"W","id":11,"title":"10,000 할인쿠폰(중복할인 불가능)","doubleDiscount":false}, {"discount":20000,"discountType":"W","id":12,"title":"20,000 할인쿠폰(중복할인 불가능)","doubleDiscount":false}]';
            let cards = JSON.parse(cardJsonArrayString);
            let coupons = JSON.parse(couponJsonArrayString);
        </script>
        <script src="<c:url value="/Test_ch10/restaurant.js"/>" type="module"></script>
    </body>
</html>
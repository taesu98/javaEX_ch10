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

</head>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<body>
<div data-role="appbar">
            <ul class="app-bar-menu">
                <li><a href="<c:url value="restaurant?action=menus"/>">결제</a></li>
                <li><a href="<c:url value="restaurant?action=Bills"/>">결산</a></li>
            </ul>
        </div>
        <br />
        <br />
        <br />

<div class="container">
            <label>결제</label>
            <br />
            <table class="table" data-role="table" id="bills_table"
                   data-rows="-1"
                   data-show-pagination="false" data-show-search="false" data-show-rows-steps="false" data-show-table-info="false"
                   data-pagination-short-mode="false" data-pagination-wrapper=".block-pagination-wrapper">
                <thead>
                    <tr>
                        <th>결제번호</th>
                        <th>할인가</th>
                        <th>정가</th>
                        <th>결제일</th>
                        <th>카드</th>
                        <th>쿠폰</th>
                        <th>메뉴</th>
                        <th>수량</th>
                    </tr>
                </thead>
                   <tbody id="bills">
                        <c:forEach var="bills" items="${billsList}">
                            <tr>
                                <td>${bills.id}</td>
                                <td>${bills.sellingprice}</td>
                                <td>${bills.price}</td>
                                <td>${bills.orderDate}</td>
                                <td>${bills.creditCards.cardName}</td>
                                <td>${bills.coupons.ctitle}</td>
                                <td>${bills.menus.menuName}</td>
                                <td>${bills.orders.menuquantity}</td>
                            </tr>
                        </c:forEach>
                   
                 </tbody>
            </table>
            <div class="block-pagination-wrapper" id="bills_pagination"></div>
        </div>
        <script src="https://cdn.korzh.com/metroui/v4.5.1/js/metro.min.js"></script>
        <script src="<c:url value="/EX_ch10/addLocale_ko_KR.js"/>"></script>
        <script>
            let cardJsonArrayString = '[{"name":"CJ ONE 삼성카드","discount":30,"id":1}, {"name":"CJ ONE 신한카드","discount":30,"id":2}, {"name":"THE CJ 카드","discount":20,"id":3}, {"name":"삼성 6 V4카드","discount":20,"id":4}, {"name":"신한 Lady카드","discount":20,"id":5}, {"name":"삼성 SFC","discount":20,"id":6}, {"name":"삼성 S클라스","discount":20,"id":7}, {"name":"하나 Yes OK Saver","discount":20,"id":8}, {"name":"홈플러스 하나줄리엣카드","discount":20,"id":9}, {"name":"하나 줄리엣카드 & Yes 4u shopping","discount":20,"id":10}, {"name":"KB Star","discount":20,"id":11}, {"name":"이마트 KB카드","discount":15,"id":12}, {"name":"KT 멤버십 일반 할인","discount":5,"id":13}, {"name":"KT 멤버십 VIP 할인","discount":15,"id":14}, {"name":"T 멤버십 실버 할인","discount":5,"id":15}, {"name":"T 멤버십 VIP\/골드 할인","discount":15,"id":16}, {"name":"OK캐시백","discount":30,"id":17}, {"name":"BC Top 포인트","discount":10,"id":18}, {"name":"기아멤버스 카드","discount":20,"id":19}, {"name":"삼성카드 포인트","discount":10,"id":20}, {"name":"현대카드 M","discount":20,"id":21}, {"name":"신한 Hi-Point 카드","discount":20,"id":22}, {"name":"블루멤버스 카드","discount":20,"id":23}]';
            let couponJsonArrayString = '[{"discount":5,"discountType":"%","id":1,"title":"5% 할인쿠폰(중복할인 가능)","doubleDiscount":true}, {"discount":10,"discountType":"%","id":2,"title":"10% 할인쿠폰(중복할인 가능)","doubleDiscount":true}, {"discount":15,"discountType":"%","id":3,"title":"15% 할인쿠폰(중복할인 가능)","doubleDiscount":true}, {"discount":5000,"discountType":"W","id":4,"title":"5,000 할인쿠폰(중복할인 가능)","doubleDiscount":true}, {"discount":10000,"discountType":"W","id":5,"title":"10,000 할인쿠폰(중복할인 가능)","doubleDiscount":true}, {"discount":20000,"discountType":"W","id":6,"title":"20,000 할인쿠폰(중복할인 가능)","doubleDiscount":true}, {"discount":5,"discountType":"%","id":7,"title":"5% 할인쿠폰(중복할인 불가능)","doubleDiscount":false}, {"discount":10,"discountType":"%","id":8,"title":"10% 할인쿠폰(중복할인 불가능)","doubleDiscount":false}, {"discount":15,"discountType":"%","id":9,"title":"15% 할인쿠폰(중복할인 불가능)","doubleDiscount":false}, {"discount":5000,"discountType":"W","id":10,"title":"5,000 할인쿠폰(중복할인 불가능)","doubleDiscount":false}, {"discount":10000,"discountType":"W","id":11,"title":"10,000 할인쿠폰(중복할인 불가능)","doubleDiscount":false}, {"discount":20000,"discountType":"W","id":12,"title":"20,000 할인쿠폰(중복할인 불가능)","doubleDiscount":false}]';
            let cards = JSON.parse(cardJsonArrayString);
            let coupons = JSON.parse(couponJsonArrayString);
        </script>
        <script src="<c:url value="/EX_ch10/restaurant.js"/>" type="module"></script>
</body>
</html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

    <body>
        <div data-role="appbar">
            <ul class="app-bar-menu">
                <li><a href="<c:url value="/restaurant?action=bill"/>">결제</a></li>
                <li><a href="<c:url value="/restaurant?action=bills"/>">결산</a></li>
            </ul>
        </div>

        <br />
        <br />
        <br />

        <div class="container">
            <h6>메뉴</h6>
            <c:forEach var="menu" items="${menuList}">
                <button class="button menu"
                        data-menu-id="${menu.id}" data-menu-name="${menu.name}" data-menu-price="${menu.price}">${menu.name}(${menu.price}원)
                </button>
            </c:forEach>
        </div>
        <br />
        <div class="container">
            <h6>선택한 메뉴</h6>
            <form action="<c:url value="/restaurant"/>?action=addBill" method="post" id="form_bill">
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
                <c:forEach var="card" items="${creditCardList}">
                    <option value="${card.id}">${card.name}</option>
                </c:forEach>
            </select>
            <select data-role="select" data-filter="false" id="select_telecom">
                <option value="-1">통신사 선택하세요.</option>
                <c:forEach var="card" items="${telecomCardList}">
                    <option value="${card.id}">${card.name}</option>
                </c:forEach>
            </select>
            <select data-role="select" data-filter="false" id="select_point">
                <option value="-1">포인트결제 선택하세요.</option>
                <c:forEach var="card" items="${pointCardList}">
                    <option value="${card.id}">${card.name}</option>
                </c:forEach>
            </select>
            <select data-role="select" data-filter="false" id="select_okcashbag">
                <option value="-1">OK캐시백 선택하세요.</option>
                <c:forEach var="card" items="${okcashbagCardList}">
                    <option value="${card.id}">${card.name}</option>
                </c:forEach>
            </select>
            <select data-role="select" data-filter="false" id="select_coupon">
                <option value="-1">할인쿠폰 선택하세요.</option>
                <c:forEach var="coupon" items="${couponList}">
                    <option value="${coupon.id}">${coupon.title}</option>
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
        <script src="addLocale_ko_KR.js"></script>
        <script>
            let cardJsonArrayString = '${cardJsonArrayString}';
            let couponJsonArrayString = '${couponJsonArrayString}';
            let cards = JSON.parse(cardJsonArrayString);
            let coupons = JSON.parse(couponJsonArrayString);
        </script>
        <script src="bill.js" type="module"></script>
    </body>
</html>

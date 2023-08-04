<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <c:forEach var="bill" items="${billList}">
                        <tr>
                            <td>${bill[0]}</td>
                            <td>${bill[1]}</td>
                            <td>${bill[2]}</td>
                            <td><fmt:formatDate value="${bill[3]}" pattern="yyyy-MM-dd"/></td>
                            <td>${bill[4]}</td>
                            <td>${bill[5]}</td>
                            <td></td>
                            <td></td>
                        </tr>
                        <c:forEach var="lineItem" items="${bill[6]}">
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td>${lineItem[1]}</td>
                                <td>${lineItem[2]}</td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </tbody>
            </table>
            <div class="block-pagination-wrapper" id="bills_pagination"></div>
        </div>
        <script src="https://cdn.korzh.com/metroui/v4.5.1/js/metro.min.js"></script>
        <script src="addLocale_ko_KR.js"></script>
        <script>
        </script>
        <script src="bills.js" type="module"></script>
    </body>
</html>

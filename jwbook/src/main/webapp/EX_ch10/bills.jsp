<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
                <label>결제</label>
                <br />
                <table class="table" data-role="table"
                       data-rows="-1"
                       data-show-pagination="false" data-show-search="false" data-show-rows-steps="false" data-show-table-info="false">
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
                    <tbody id="orderings">
                        <c:forEach var="ordering" items="${orderingList}">
                            <tr>
                                <td>${ordering.customerId}</td>
                                <td>${orderingList.customer.name}</td>
                                <td>${ordering.bookId}</td>
                                <td>${orderingList.book.title}</td>
                                <td>${ordering.sellingPrice}</td>
                                <td><fmt:formatDate value="${ordering.orderingDate}" pattern="yyyy-MM-dd"/></td>
                                <td><fmt:formatDate value="${ordering.orderingDate}" pattern="yyyy-MM-dd"/></td>
                                <td><fmt:formatDate value="${ordering.orderingDate}" pattern="yyyy-MM-dd"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
</body>
</html>
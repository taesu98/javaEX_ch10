<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta name="metro4:locale" content="ko-KR" />
        <meta name="metro4:init" content="false" />

        <title>Madang Metro</title>

        <link rel="stylesheet" href="https://cdn.korzh.com/metroui/v4.5.1/css/metro-all.min.css" />
        <style>
            a.customer_id, a.customer_id:visited {
                cursor: pointer;
                text-decoration: underline;
                color: blue;
            }
        </style>
    </head>

    <body>
        <div data-role="appbar" data-expand="true">
            <ul class="app-bar-menu">
                <li><a href="<c:url value="madang?action=orderings"/>">주문</a></li>
                <li><a href="<c:url value="madang?action=customers"/>">고객</a></li>
                <li><a href="<c:url value="madang?action=books"/>">도서</a></li>
            </ul>
        </div>
        <br />
        <br />
        <br />

        <div class="container">
            <label>도서</label>
            <br />
            <table class="table" data-role="table"
                   data-rows="-1"
                   data-show-pagination="false" data-show-search="false" data-show-rows-steps="false" data-show-table-info="false">
                <thead>
                    <tr>
                        <th>도서번호</th>
                        <th>도서명</th>
                        <th>출판사</th>
                        <th>가격</th>
                    </tr>
                </thead>
                <tbody id="books">
                    <c:forEach var="book" items="${bookList}">
                        <tr>
                            <td>
                            <a class="book_id" 
                            href="<c:url value="madang?action=book&id=${book.id}"/>" 
                            data-id="${book.id}">
                            ${book.id}
                            </a>
                            </td>
                            <td>${book.title}</td>
                            <td>${book.publisher}</td>
                            <td>${book.price}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="container">
            <form id="goto_form" action="<c:url value="/madang"/>" method="post">
                <input type="hidden" name="action" value="book"/>
                <input type="hidden" name="id" value="-1"/>
                <input type="submit" value="추가" id="add_button"/>
            </form>
        </div>

        <script src="https://cdn.korzh.com/metroui/v4.5.1/js/metro.min.js"></script>
        <script>
            Metro.utils.addLocale({
                'ko-KR': {
                    calendar: {
                        months: [
                            'January',
                            'February',
                            'March',
                            'April',
                            'May',
                            'June',
                            'July',
                            'August',
                            'September',
                            'October',
                            'November',
                            'December',
                            'Jan',
                            'Feb',
                            'Mar',
                            'Apr',
                            'May',
                            'Jun',
                            'Jul',
                            'Aug',
                            'Sep',
                            'Oct',
                            'Nov',
                            'Dec',
                        ],
                        days: [
                            'Sunday',
                            'Monday',
                            'Tuesday',
                            'Wednesday',
                            'Thursday',
                            'Friday',
                            'Saturday',
                            'Su',
                            'Mo',
                            'Tu',
                            'We',
                            'Th',
                            'Fr',
                            'Sa',
                            'Sun',
                            'Mon',
                            'Tus',
                            'Wen',
                            'Thu',
                            'Fri',
                            'Sat',
                        ],
                        time: {
                            days: 'DAYS',
                            hours: 'HOURS',
                            minutes: 'MINS',
                            seconds: 'SECS',
                            month: 'MON',
                            day: 'DAY',
                            year: 'YEAR',
                        },
                        weekStart: 0,
                    },
                    buttons: {
                        ok: 'OK',
                        cancel: 'Cancel',
                        done: 'Done',
                        today: 'Today',
                        now: 'Now',
                        clear: 'Clear',
                        help: 'Help',
                        yes: 'Yes',
                        no: 'No',
                        random: 'Random',
                        save: 'Save',
                        reset: 'Reset',
                    },
                    table: {
                        rowsCount: 'Show entries:',
                        search: 'Search:',
                        info: 'Showing $1 to $2 of $3 entries',
                        prev: 'Prev',
                        next: 'Next',
                        all: 'All',
                        inspector: 'Inspector',
                        skip: 'Goto page',
                        empty: 'N/A',
                    },
                    colorSelector: {
                        addUserColorButton: 'ADD TO SWATCHES',
                        userColorsTitle: 'USER COLORS',
                    },
                    switch : {
                        on: 'on',
                        off: 'off',
                    },
                },
            });
            Metro.init();
        </script>

        <script>
            let hasOrdering = ${hasOrdering};
        </script>

        <script src="<c:url value="/books.js"/>"></script>
    </body>
</html>
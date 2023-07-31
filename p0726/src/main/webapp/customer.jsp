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
    </head>

    <body>
        <div data-role="appbar">
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
            <label>고객 추가/수정/삭제 하기</label>
            <br/>
            <br/>
            <form action="<c:url value="/madang"/>" method="get" id="customer_form">
                <input type="hidden" name="action" value="" id="customer_form_action"/>
                <div class="row">
                    <label class="cell-2">고객번호</label>
                    <div class="cell-10"><input type="number" data-role="input" name="id" value='${customer.id}' id="id" readonly /></div>
                </div>
                <div class="row">
                    <label class="cell-2">고객명</label>
                    <div class="cell-10"><input type="text" data-role="input" name="name" value="${customer.name}" id="name" /></div>
                </div>
                <div class="row">
                    <label class="cell-2">주소</label>
                    <div class="cell-10"><input type="text" data-role="input" name="address" value="${customer.address}" id="address" /></div>
                </div>
                <div class="row">
                    <label class="cell-2">전화번호</label>
                    <div class="cell-10"><input type="text" data-role="input" name="phone" value="${customer.phone}" id="phone" /></div>
                </div>
                <div class="row">
                    <div class="cell-3"></div>
                    <div class="cell-3">
                        <button class="button" id="update_button">저장</button>
                        <button class="button" id="delete_button">삭제</button>
                    </div>
                    <div class="cell-3"></div>
                    <div class="cell-3"></div>
                </div>
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
        <script src="<c:url value="/customer.js"/>"></script>
    </body>
</html>
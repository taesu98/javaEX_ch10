function validateOrdering() {
    if (!$('[name="customerId"]:checked').val()) {
        throw 'customerRequired';
    }
    if (!$('[name="bookId"]:checked').val()) {
        throw 'bookRequired';
    }
    if (!$('#selling_price').val()) {
        throw 'sellingPriceRequired';
    }
}

function orderingCustomerBook() {
    try {
        validateOrdering();

        $('#ordering_form')[0].submit();
    } catch (e) {
        switch (e) {
            case 'customerRequired':
                alert('고객을 선택해 주십시오.');
                break;
            case 'bookRequired':
                alert('도서를 선택해 주십시오.');
                break;
            case 'sellingPriceRequired':
                alert('판매가를 입력해 주십시오.');
                break;
        }
    }
}

$(function () {
    $('#ordering_button').click(orderingCustomerBook);
});
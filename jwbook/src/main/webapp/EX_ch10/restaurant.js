function menuClick(event) {
    let menu = $(event.target);
    let selectedMenus = $('#selected_menus .selected_menu');
    if (selectedMenus.length != 0) {
        if (
                $(selectedMenus)
                .items()
                .some((it) => {
                    return it.dataset.menuId == menu.data().menuId;
                })
                ) {
            alert('이미 추가된 메뉴입니다.');
            return;
        }
    }

    if (selectedMenus.length == 0) {
        $('#selected_menus tr').remove();
        Metro.getPlugin($('#select_credit'), 'select').enable();
        Metro.getPlugin($('#select_telecom'), 'select').enable();
        Metro.getPlugin($('#select_point'), 'select').enable();
        Metro.getPlugin($('#select_okcashbag'), 'select').enable();
        Metro.getPlugin($('#select_coupon'), 'select').enable();
    }

    let tr = $('<tr>', {class: 'selected_menu', 'data-menu-id': menu.data().menuId});
    let td = null;
    let input = null;
    td = $('<td>');
    td.innerText(menu.data().menuName);
    td.appendTo(tr);
    td = $('<td>');
    td.innerText(menu.data().menuPrice);
    td.appendTo(tr);
    td = $('<td>');
    input = $('<input>', {type: 'hidden', class: 'menu_id', value: menu.data().menuId});
    input.appendTo(td);
    input = $('<input>', {type: 'number', class: 'menu_quantity', value: 1, step: 1, min: 1});
    input.appendTo(td);
    td.appendTo(tr);
    td = $('<td>');
    input = $('<input>', {type: 'number', class: 'line_sum', value: menu.data().menuPrice, readonly: true});
    input.appendTo(td);
    td.appendTo(tr);

    $('#selected_menus').append(tr);

    $('#selected_menus .menu_quantity').change(quantityChange);

    sumPrice();
}

function findMaxDiscountCard(cardIds) {
    let rtn = null;

    cardIds = cardIds.filter((it) => {
        return it != -1;
    });
    let selectedCards = [];
    for (let cardId of cardIds) {
        selectedCards.push(
                cards.find((it) => {
                    return it.id == cardId;
                })
                );
    }

    selectedCards.sort((it0, it1) => {
        return it1.discount - it0.discount;
    });

    if (selectedCards.length != 0) {
        rtn = selectedCards[0];
        let maxDiscount = selectedCards[0].discount;
        selectedCards.shift();
        if (
                selectedCards.some((it) => {
                    return it.discount == maxDiscount;
                })
                ) {
            throw 'SameDiscount';
        }
    }

    return rtn;
}

function cardChange(event) {
    $('#card_id').val(-1);
    $('#coupon_id').val(-1);
    $('#settle_description p').remove();

    try {
        let priceSum = parseInt($('#price_sum').text());

        if (priceSum == 0) {
            throw 'ZeroPrice';
        }

        let priceDiscount = -1;

        let cardIds = [];
        cardIds.push($('#select_credit').val());
        cardIds.push($('#select_telecom').val());
        cardIds.push($('#select_point').val());
        cardIds.push($('#select_okcashbag').val());

        let couponId = $('#select_coupon').val();
        let coupon = null;
        if (couponId != -1) {
            coupon = coupons.filter((it) => {
                return it.id == couponId;
            })[0];
        }

        if (coupon) {
            if (coupon.doubleDiscount) {
                if (coupon.discountType == '%') {
                    priceDiscount = priceSum * ((100 - coupon.discount) / 100);
                    $('#coupon_id').val(coupon.id);
                    $(`<p>쿠폰 중복 할인 %: ${coupon.title}</p>`).appendTo($('#settle_description'));
                } else if (coupon.discountType == 'W') {
                    priceDiscount = priceSum - coupon.discount;
                    $('#coupon_id').val(coupon.id);
                    $(`<p>쿠폰 중복 할인 W: ${coupon.title} </p>`).appendTo($('#settle_description'));
                }
                let maxDiscountCard = findMaxDiscountCard(cardIds);
                if (maxDiscountCard) {
                    priceDiscount = priceDiscount * ((100 - maxDiscountCard.discount) / 100);
                    $('#card_id').val(maxDiscountCard.id);
                    $(`<p>카드 할인 %: ${maxDiscountCard.name}</p>`).appendTo($('#settle_description'));
                } else {
                    priceDiscount = priceDiscount;
                }
            } else {
                let couponDiscount = 0;
                let cardDiscount = 0;
                if (coupon.discountType == '%') {
                    couponDiscount = priceSum * (coupon.discount / 100);
                } else if (coupon.discountType == 'W') {
                    couponDiscount = coupon.discount;
                }
                let maxDiscountCard = null;
                try {
                    maxDiscountCard = findMaxDiscountCard(cardIds);
                    if (maxDiscountCard) {
                        cardDiscount = priceSum * (maxDiscountCard.discount / 100);
                    } else {
                        cardDiscount = 0;
                    }
                } catch (e) {
                }
                if (couponDiscount == cardDiscount) {
                    throw 'SameDiscount';
                } else if (couponDiscount > cardDiscount) {
                    priceDiscount = priceSum - couponDiscount;
                    $('#coupon_id').val(coupon.id);
                    $(`<p>쿠폰 할인 ${coupon.discountType}: ${coupon.title} </p>`).appendTo($('#settle_description'));
                } else if (couponDiscount < cardDiscount) {
                    priceDiscount = priceSum - cardDiscount;
                    if (maxDiscountCard) {
                        $('#card_id').val(maxDiscountCard.id);
                        $(`<p>카드 할인 %: ${maxDiscountCard.name}</p>`).appendTo($('#settle_description'));
                    }
                }
            }
        } else {
            let maxDiscountCard = findMaxDiscountCard(cardIds);
            if (maxDiscountCard) {
                priceDiscount = priceSum * ((100 - maxDiscountCard.discount) / 100);
                $('#card_id').val(maxDiscountCard.id);
                $(`<p>카드 할인 %: ${maxDiscountCard.name}</p>`).appendTo($('#settle_description'));
            } else {
                priceDiscount = priceSum;
            }
        }

        $('#price_discount').val(Math.floor(priceDiscount));
        $('#button_settle').removeAttr('disabled');
    } catch (e) {
        switch (e) {
            case 'ZeroPrice':
                break;
            case 'SameDiscount':
                alert('동일한 할인율이 있습니다.');
                break;
        }
    }
}

function quantityChange(event) {
    let quantity = $(event.target);
    let price = quantity.parent().prev().text();
    quantity
            .parent()
            .next()
            .find('.line_sum')
            .val(parseInt(quantity.val()) * parseInt(price));

    sumPrice();
}

function sumPrice() {
    let sum = $('#selected_menus .line_sum')
            .items()
            .reduce(function (sum, lineSum) {
                return sum + parseInt(lineSum.value);
            }, 0);

    $('#price_sum').innerText(sum);

    let priceSum = $('#price_sum').innerText();

    cardChange(null);
}

function addIndexedName(inputs, indexedName, name) {
    for (let i = 0; i < inputs.length; i++) {
        inputs[i].name = `${indexedName}[${i}].${name}`;
    }
}

function settleClick(event) {
    let inputs = null;
    inputs = $('input.menu_quantity');
    /*
     for (let i = 0; i < inputs.length; i++) {
     inputs[i].name = `lineItems[${i}].menuQuantity`;
     }
     */
    addIndexedName(inputs, 'lineItems', 'menuQuantity');

    inputs = $('input.menu_id');
    /*
     for (let i = 0; i < inputs.length; i++) {
     inputs[i].name = `lineItems[${i}].menuId`;
     }
     */
    addIndexedName(inputs, 'lineItems', 'menuId');
    $('#form_bill')[0].submit();
}

$(function () {
    $('button.menu').click(menuClick);

    $('#select_credit').change(cardChange);
    $('#select_telecom').change(cardChange);
    $('#select_point').change(cardChange);
    $('#select_okcashbag').change(cardChange);
    $('#select_coupon').change(cardChange);

    $('#button_settle').click(settleClick);

    Metro.getPlugin($('#select_credit'), 'select').disable();
    Metro.getPlugin($('#select_telecom'), 'select').disable();
    Metro.getPlugin($('#select_point'), 'select').disable();
    Metro.getPlugin($('#select_okcashbag'), 'select').disable();
    Metro.getPlugin($('#select_coupon'), 'select').disable();

    $('#button_settle').attr('disabled', true);
});
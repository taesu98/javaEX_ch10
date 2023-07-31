import { getCustomers, getCustomer, addCustomer, setCustomer, getCustomerWithOrderingsById, removeCustomer, getOrderingsByCustomerId } from './madang_fetch.js';

function makeCustomer() {
  if (!$('#name').val()) {
    throw 'nameRequired';
  }

  return {
    id: $('#id').val(),
    name: $('#name').val(),
    address: $('#address').val(),
    phone: $('#phone').val(),
  };
}

async function updateCustomer() {
  try {
    let customer = makeCustomer();

    let ok = false;
    if (customer.id) {
      ok = await setCustomer(customer);
    } else {
      ok = await addCustomer(customer);
    }
    if (ok) {
      let json = await getCustomers();
      loadCustomers(json);
      clearCustomer();
    } else{
      throw 'json-server_error';
    }
  } catch (e) {
    switch (e) {
      case 'nameRequired':
        alert('이름을 입력해 주십시오.');
        break;
    }
  }
}

async function deleteCustomer() {
  let customer = makeCustomer();

  let json = null;

  /*
  json = await getOrderingsByCustomerId(customer.id);

  if (json.length != 0) {
    alert('주문이 있는 고객은 삭제할 수 없습니다.');
    return;
  }
  */

  json = await getCustomerWithOrderingsById(customer.id);

  if (json.orderings.length != 0) {
    alert('주문이 있는 고객은 삭제할 수 없습니다.');
    return;
  }

  let ok = await removeCustomer(customer.id);

  if (ok) {
    json = await getCustomers();
    loadCustomers(json);
    clearCustomer();
  }
}

function loadCustomers(customers) {
  $('#customers tr').remove();

  let tr = [];
  for (let customer of customers) {
    tr.push(`
      <tr>
        <td><a class="customer_id" data-id="${customer.id}">${customer.id}</a></td>
        <td>${customer.name}</td>
        <td>${Metro.utils.nvl(customer.address, 'N/A')}</td>
        <td>${Metro.utils.nvl(customer.phone, 'N/A')}</td>
      </tr>
      `);
  }
  $('#customers').html(tr.join(''));

  $('a.customer_id').click(loadCustomer);
}

async function loadCustomer(event) {
  let customer = await getCustomer(event.target.dataset.id);
  //console.log(customer);
  clearCustomer();
  $('#id').val(customer.id);
  $('#name').val(customer.name);
  $('#address').val(Metro.utils.nvl(customer.address, ''));
  $('#phone').val(Metro.utils.nvl(customer.phone, ''));
}

function clearCustomer() {
  $('#id').val('');
  $('#name').val('');
  $('#address').val('');
  $('#phone').val('');
}

$(async function () {
  let customers = await getCustomers();

  loadCustomers(customers);

  $('#prepare_button').click(clearCustomer);
  $('#update_button').click(updateCustomer);
  $('#delete_button').click(deleteCustomer);
});
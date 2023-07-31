export async function getCustomers() {
  //let response = await fetch('http://localhost:8080/p0726/madang?action=apiCustomers');
  let response = await fetch('http://localhost:8080/p0726/apimadang/customers');
  let json = await response.json();

  return json;
}

export async function getCustomer(id) {
  let response = await fetch(`http://localhost:8080/p0726/apimadang/customers/${id}`);
  let json = await response.json();

  return json;
}

export async function getCustomerWithOrderingsById(id) {
  let response = await fetch(`http://192.168.0.9:3000/customers/${id}?_embed=orderings`);
  let json = await response.json();

  return json;
}

export async function removeCustomer(id) {
  let response = await fetch(`http://192.168.0.9:3000/customers/${id}`, {
    method: 'DELETE',
  });

  return response.ok;
}

export async function getBooks() {
  let response = await fetch('http://localhost:8080/p0726/madang?action=apiBooks');
  let json = await response.json();

  return json;
}
export async function getBook(id) {
  let response = await fetch(`http://localhost:8080/p0726/madang?action=apiBook&id=${id}`);
  let json = await response.json();

  return json;
}

export async function getOrderings() {
  let response = await fetch('http://192.168.0.9:3000/orderings?_sort=id&_expand=customer&_expand=book');
  let json = await response.json();

  return json;
}

export async function getOrderingsByCustomerId(customerId) {
  let response = await fetch(`http://192.168.0.9:3000/orderings?customerId=${customerId}`);
  let json = await response.json();

  return json;
}

export async function addOrdering(ordering) {
  let response = await fetch('http://192.168.0.9:3000/orderings', {
    method: 'POST',
    body: JSON.stringify(ordering),
    headers: { 'content-type': 'application/json; charset=UTF-8' },
  });

  return response.ok;
}

export async function addCustomer(customer) {
  let response = await fetch('http://192.168.0.9:3000/customers', {
    method: 'POST',
    body: JSON.stringify(customer),
    headers: { 'content-type': 'application/json; charset=UTF-8' },
  });

  return response.ok;
}

export async function setCustomer(customer) {
  let response = await fetch(`http://192.168.0.9:3000/customers/${customer.id}`, {
    method: 'PUT',
    body: JSON.stringify(customer),
    headers: { 'content-type': 'application/json; charset=UTF-8' },
  });

  return response.ok;
}

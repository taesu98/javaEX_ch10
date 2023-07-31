package EX_ch09;

import java.util.*;

public class Ordering {

    private int id;
    private int customerId;
    private int bookId;
    private int sellingPrice;
    private Date orderingDate;
    private Customer customer;
    private Book book;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Date getOrderingDate() {
        return orderingDate;
    }

    public void setOrderingDate(Date orderingDate) {
        this.orderingDate = orderingDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}

//create table ordering(
//	id identity,
//	customerId bigint not null references customer(id),
//	bookId bigint not null references book(id),
//	sellingPrice int not null,
//	orderingDate DATE
//)
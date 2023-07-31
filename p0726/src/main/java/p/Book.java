package p;

import java.util.*;

import org.json.simple.JSONObject;

public class Book {

    private int id;
    private String title;
    private String publisher;
    private int price;
    private List<Ordering> orderings;

    public String toJsonString() {
    	String rtn = null;
    	
    	JSONObject jo = new JSONObject(); 
    	jo.put("id",id);
    	jo.put("title", title);
    	jo.put("publisher", publisher);
    	jo.put("price", price);
    	
    	rtn = jo.toString();
    	
    	return rtn;  	
    }
    
    public Book() {
//        orderings = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Ordering> getOrderings() {
        return orderings;
    }

    public void setOrderings(List<Ordering> orderings) {
        this.orderings = orderings;
    }
}

//create table book(
//	id identity,
//	title vachar(64) not null,
//	publisher varchar(64),
//	price int not null 
//)
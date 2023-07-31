package p;

import java.util.*;

import org.json.simple.JSONObject;

public class Customer {

    private int id;
    private String name;
//    private String rrn;
    private String address;
    private String phone;
    private List<Ordering> orderings;
    
    public String toJsonString() {
    	String rtn = null;
    	
    	JSONObject jo = new JSONObject(); 
    	jo.put("id",id);
    	jo.put("name", name);
    	jo.put("address", address);
    	jo.put("phone", phone);
    	
    	rtn = jo.toString();
    	
    	return rtn;  	
    }
    
    public Customer() {
//        orderings = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Ordering> getOrderings() {
        return orderings;
    }

    public void setOrderings(List<Ordering> orderings) {
        this.orderings = orderings;
    }
}

//create table customer(
//	id identity,
//	name varchar(64),
//	address varchar(64),
//	phone char(13)
//)
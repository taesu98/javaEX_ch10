package EX_ch08;

import java.util.HashMap;
import java.util.*;
import java.util.Map;

public class customerService {
	Map<Integer, customer> customers = new HashMap<>();
	public customerService() {
		customer c = new customer(1, "박지성", "영국 맨체스타", "000-5000-0001");
		customers.put(1, c);
		c = new customer(2, "김연아", "대한민국 서울", "000-6000-0001");
		customers.put(2, c);
		c = new customer(3, "장미란", "대한민국 강원도", "000-7000-0001");
		customers.put(3, c);
		c = new customer(4, "추신수", "미국 클리블랜드", "000-8000-0001");
		customers.put(4, c);
		c = new customer(5, "박세리", "대한민국 대전", "N/A");
		customers.put(5, c);
	}
	public Map<Integer, customer> map(){
		return customers;
	}
	
	public List<customer> findAll(){
		return new ArrayList<>(customers.values());
	}
	
	public customer find(String id) {
		return customers.get(id);
	}
}

package EX_ch08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class orderService {
	Map<Integer, order> orders = new HashMap<>();
	public orderService() {
		order o = new order(1, 1, 1, 6000,"2020-07-01");
		orders.put(1, o);
		o = new order(2, 1, 3, 21000,"2020-07-03");
		orders.put(2, o);
		o = new order(3, 2, 5, 8000,"2020-07-03");
		orders.put(3, o);
		o = new order(4, 3, 6, 6000,"2020-07-04");
		orders.put(4, o);
		o = new order(5, 4, 7, 20000,"2020-07-05");
		orders.put(5, o);
		o = new order(6, 1, 2, 12000,"2020-07-07");
		orders.put(6, o);
		o = new order(7, 4, 8, 13000,"2020-07-07");
		orders.put(7, o);
		o = new order(8, 3, 10, 12000,"2020-07-08");
		orders.put(8, o);
		o = new order(9, 2, 10, 7000,"2020-07-09");
		orders.put(9, o);
		o = new order(10, 3, 8, 13000,"2020-07-10");
		orders.put(10, o);
	}
	public Map<Integer, order> map(){
		return orders;
	}
	
	public List<order> findAll(){
		return new ArrayList<>(orders.values());
	}
	
	public order find(Integer id) {
		return orders.get(id);
	}
	
//	public synchronized void add(order order) {
//		int max = Collections.max(orders.keySet());
//	}
}

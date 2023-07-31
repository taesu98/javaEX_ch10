package p;

import java.util.*;

public class OrderingService {
	OrderingDAO orderingDao;
	
	public OrderingService() {
		orderingDao = new OrderingDAO();
	}
	
	public List<Object[]> get(){
		return orderingDao.select();
	}
	
	public void add(Ordering ordering) {
		orderingDao.insert(ordering);
	}
	
	boolean hasOrderingCustomer(int customerId) {
		boolean rtn = false;
		
		orderingDao.selectCountByCustomerId(customerId);
		
		return rtn;
	}
	
	boolean hasOrderingBook(int bookId) {
		boolean rtn = false;
		
		orderingDao.selectCountByBookId(bookId);
		
		return rtn;
	}
}

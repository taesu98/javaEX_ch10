package p;

import java.util.*;

public class BookService {
	BookDAO bookDao;
	OrderingService orderingService;
	
	public BookService() {
		bookDao = new BookDAO();
		orderingService = new OrderingService();
	}
	
	public List<Book> get(){
		return bookDao.select();
	}
	
	public Book getOrBlack(int id){
		Book rtn = null;
		
		rtn = bookDao.selectById(id);
		
		if(rtn == null) {
			rtn = new Book();
			rtn.setId(-1);
			rtn.setTitle("");
			rtn.setPublisher("");
			rtn.setPrice(-1);
		}
		
		return rtn;
	}
	
	public void addOrSet(Book book) {
		if(book.getId() == -1) {
			add(book);
		} else {
			set(book);
		}
		
	}
	public void add(Book book) {
		bookDao.insert(book);
	}
	
	public void set(Book book) {
		bookDao.update(book);
	}
	
	public void remove(int id ) throws HasOrderingException {
		boolean hasOrdering = orderingService.hasOrderingBook(id);
    	
    	if(hasOrdering) {
    		throw new HasOrderingException("Book has ordering");
    	} else {
    		bookDao.delete(id);
    	}
	}
}

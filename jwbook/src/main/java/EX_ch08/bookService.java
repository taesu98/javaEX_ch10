package EX_ch08;

import java.util.HashMap;
import java.util.*;
import java.util.Map;

public class bookService {
	Map<Integer, book> books = new HashMap<>();
	public bookService() {
		book b = new book(1, "축구의 역사", "굿스포츠", 7000);
		books.put(1, b);
		b = new book(2, "축구아는 여자", "나무수", 13000);
		books.put(2, b);
		b = new book(3, "축구의 이해", "대한미디어", 22000);
		books.put(3, b);
		b = new book(4, "골프 바이블", "대한미디어", 35000);
		books.put(4, b);
		b = new book(5, "피겨 교본", "굿스포츠", 8000);
		books.put(5, b);
		b = new book(6, "역도 단계별기술", "굿스포츠", 6000);
		books.put(6, b);
		b = new book(7, "야구의 추억", "이상미디어", 20000);
		books.put(7, b);
		b = new book(8, "야구를 부탁해", "이상미디어", 13000);
		books.put(8, b);
		b = new book(9, "올림픽 이야기", "삼성당", 7500);
		books.put(9, b);
		b = new book(10, "Olympic Champions", "Pearson", 13000);
		books.put(10, b);
	}
	
	public Map<Integer, book> map(){
		return books;
	}
	
	public List<book> findAll(){
		return new ArrayList<>(books.values());
	}
	
	public book find(Integer id) {
		return books.get(id);
	}
}
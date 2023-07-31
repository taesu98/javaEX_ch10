package p;

import org.apache.commons.csv.*;

import java.io.*;
import java.util.*;

public class BookService {

    Map<Integer, Book> bookMap;

    public BookService() {
        bookMap = new HashMap<>();
        loadBooks();
    }

    public void loadBooks() {
        String data = """
1	축구의 역사	굿스포츠	7000
2	축구아는 여자	나무수	13000
3	축구의 이해	대한미디어	22000
4	골프 바이블	대한미디어	35000
5	피겨 교본	굿스포츠	8000
6	역도 단계별기술	굿스포츠	6000
7	야구의 추억	이상미디어	20000
8	야구를 부탁해	이상미디어	13000
9	올림픽 이야기	삼성당	7500
10	Olympic Champions	Pearson	13000
""";
        try ( StringReader sr = new StringReader(data)) {
            Iterable<CSVRecord> records = CSVFormat.TDF.parse(sr);
            for (CSVRecord record : records) {
                Book book = new Book();
                book.setId(Integer.parseInt(record.get(0)));
                book.setTitle(record.get(1));
                book.setPublisher(record.get(2));
                book.setPrice(Integer.parseInt(record.get(3)));
                bookMap.put(book.getId(), book);
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public Map<Integer, Book> map() {
        return bookMap;
    }

    public List<Book> get() {
        return new ArrayList<>(bookMap.values());
    }
    public Book get(int id) {
    	Book rtn = null;
        
        rtn = bookMap.get(id);
        
        if (rtn == null) {
            rtn = new Book();
            rtn.setId(-1);
            rtn.setTitle("");
            rtn.setPublisher("");
            rtn.setPrice(-1);
        }
        
        return rtn;
    }


	 public synchronized void add(Book book) {
        int max = Collections.max(bookMap.keySet());
        book.setId(max + 1);
        bookMap.put(book.getId(), book);
    }
    
    public synchronized void set(Book book) {
    	bookMap.put(book.getId(), book);
    }
    
    public synchronized void remove(int id) {
    	bookMap.remove(id);
    }
}
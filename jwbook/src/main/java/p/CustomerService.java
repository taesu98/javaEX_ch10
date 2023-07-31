package p;

import org.apache.commons.csv.*;

import java.io.*;
import java.util.*;

public class CustomerService {
    
    Map<Integer, Customer> customerMap;
    
    public CustomerService() {
        customerMap = new HashMap<>();
        loadCustomers();
    }
    
    public void loadCustomers() {
        String data = """
1	박지성	영국 맨체스타	000-5000-0001
2	김연아	대한민국 서울	000-6000-0001
3	장미란	대한민국 강원도	000-7000-0001
4	추신수	미국 클리블랜드	000-8000-0001
5	박세리	대한민국 대전	N/A                      
""";
        try ( StringReader sr = new StringReader(data)) {
            Iterable<CSVRecord> records = CSVFormat.TDF.parse(sr);
            for (CSVRecord record : records) {
                Customer customer = new Customer();
                customer.setId(Integer.parseInt(record.get(0)));
                customer.setName(record.get(1));
                customer.setAddress(record.get(2));
                customer.setPhone(record.get(3));
                customerMap.put(customer.getId(), customer);
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
    
    public Map<Integer, Customer> map() {
        return customerMap;
    }
    
    public List<Customer> get() {
        return new ArrayList<>(customerMap.values());
    }
    
    public Customer get(int id) {
        Customer rtn = null;
        
        rtn = customerMap.get(id);
        
        if (rtn == null) {
            rtn = new Customer();
            rtn.setId(-1);
            rtn.setName("");
            rtn.setAddress("");
            rtn.setPhone("");
        }
        
        return rtn;
    }
    
    public synchronized void add(Customer customer) {
        int max = Collections.max(customerMap.keySet());
        customer.setId(max + 1);
        customerMap.put(customer.getId(), customer);
    }
    
    public synchronized void set(Customer customer) {
        customerMap.put(customer.getId(), customer);
    }
    
    public synchronized void remove(int id) {
        customerMap.remove(id);
    }
    
    
}
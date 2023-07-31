package p;

import org.apache.commons.csv.*;

import java.io.*;
import java.text.*;
import java.util.*;

public class OrderingService {

    Map<Integer, Ordering> orderingMap;

    public OrderingService() {
        orderingMap = new HashMap<>();
        loadOrderings();
    }

    public void loadOrderings() {
        String data = """
1	1	1	6000	2020-07-01
2	1	3	21000	2020-07-03
3	2	5	8000	2020-07-03
4	3	6	6000	2020-07-04
5	4	7	20000	2020-07-05
6	1	2	12000	2020-07-07
7	4	8	13000	2020-07-07
8	3	10	12000	2020-07-08
9	2	10	7000	2020-07-09
10	3	8	13000	2020-07-09
""";
        try ( StringReader sr = new StringReader(data)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Iterable<CSVRecord> records = CSVFormat.TDF.parse(sr);
            for (CSVRecord record : records) {
                Ordering ordering = new Ordering();
                ordering.setId(Integer.parseInt(record.get(0)));
                ordering.setCustomerId(Integer.parseInt(record.get(1)));
                ordering.setBookId(Integer.parseInt(record.get(2)));
                ordering.setSellingPrice(Integer.parseInt(record.get(3)));
                ordering.setOrderingDate(sdf.parse(record.get(4)));
                orderingMap.put(ordering.getId(), ordering);
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
        }
    }

    public List<Ordering> get() {
        return new ArrayList<>(orderingMap.values());
    }

    public Ordering get(int id) {
        return orderingMap.get(id);
    }

    public synchronized void add(Ordering ordering) {
        int max = Collections.max(orderingMap.keySet());
        ordering.setId(max + 1);
        ordering.setOrderingDate(new Date());
        orderingMap.put(ordering.getId(), ordering);
    }
}
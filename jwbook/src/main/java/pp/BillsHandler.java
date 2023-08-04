package pp;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import java.sql.*;
import java.util.*;

public class BillsHandler extends ArrayListHandler {

    private Connection c;
    Map<String, String> queryMap;

    public BillsHandler(Connection c, Map<String, String> queryMap) {
        super();

        this.c = c;
        this.queryMap = queryMap;
    }

    @Override
    public List<Object[]> handle(ResultSet rs) throws SQLException {
        List<Object[]> rtn = super.handle(rs);

        QueryRunner qr = new QueryRunner();
        ResultSetHandler<List<Object[]>> h = new ArrayListHandler();
        for (Object[] oa : rtn) {
            long billId = (Long) oa[0];
            Object[] p = {billId};
            List<Object[]> loa = (List<Object[]>) qr.query(c, queryMap.get("getLineItemsByBillId"), h, p);
            oa[6] = loa;
        }

        return rtn;
    }
}

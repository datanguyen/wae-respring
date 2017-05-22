package wae.thesis.indiv.service.product.internal.orm;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import wae.thesis.indiv.service.product.internal.model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Nguyen Tan Dat.
 */
public class OrderORM implements ResultSetMapper<Order> {

    @Override
    public Order map(int i, ResultSet rs, StatementContext statementContext) throws SQLException {
        return new Order(rs.getString("id"), rs.getString("userid"), rs.getDouble("totalprice"), rs.getString("email"),
                rs.getString("shipaddress"), rs.getString("payment"),
                rs.getString("info"), rs.getString("fullname"), rs.getString("phone"));
    }
}

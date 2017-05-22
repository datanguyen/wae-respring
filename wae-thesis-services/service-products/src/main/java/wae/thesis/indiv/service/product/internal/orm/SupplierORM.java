package wae.thesis.indiv.service.product.internal.orm;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import wae.thesis.indiv.service.product.internal.model.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Nguyen Tan Dat.
 */


public class SupplierORM implements ResultSetMapper<Supplier> {
    @Override
    public Supplier map(int i, ResultSet rs, StatementContext statementContext) throws SQLException {
        return new Supplier(rs.getString("id"), rs.getString("name"), rs.getString("products"), rs.getString("type"));
    }
}

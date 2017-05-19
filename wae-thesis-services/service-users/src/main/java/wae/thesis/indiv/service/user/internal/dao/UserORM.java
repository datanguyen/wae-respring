package wae.thesis.indiv.service.user.internal.dao;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import wae.thesis.indiv.service.user.internal.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Nguyen Tan Dat.
 */
public class UserORM implements ResultSetMapper<User> {
    @Override
    public User map(int i, ResultSet rs, StatementContext statementContext) throws SQLException {
        return new User(rs.getString("id"), rs.getString("username"), rs.getString("password"),
                rs.getString("roles"), rs.getString("info"));
    }
}

package wae.thesis.indiv.service.user.internal.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import wae.thesis.indiv.service.user.internal.model.User;

import java.util.List;

/**
 * Created by Nguyen Tan Dat.
 */

@RegisterMapper(UserORM.class)
public interface UserDAO {

    @SqlQuery("SELECT * FROM wae.account")
    List<User> getAllUser();

    @SqlUpdate("INSERT INTO wae.account(id, username, password, roles, info)" +
            " VALUES(:id, :username, :password, :roles, :info)")
    void addUser(@Bind("id") String id, @Bind("username") String username, @Bind("password") String password,
                 @Bind("roles") String roles, @Bind("info") String info);
}

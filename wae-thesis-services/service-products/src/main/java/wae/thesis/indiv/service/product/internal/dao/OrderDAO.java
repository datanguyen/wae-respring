package wae.thesis.indiv.service.product.internal.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import wae.thesis.indiv.service.product.internal.model.Order;
import wae.thesis.indiv.service.product.internal.orm.OrderORM;

import java.util.List;

/**
 * Created by Nguyen Tan Dat.
 */

@RegisterMapper(OrderORM.class)
public interface OrderDAO {

    @SqlQuery("SELECT * FROM wae.order")
    List<Order> getAllOrder();

    @SqlUpdate("INSERT INTO wae.order(id, userid, totalprice, email, shipaddress, payment, info, fullname, phone)" +
            " VALUES(:id, :userid, :totalprice, :email, :shipaddress, :payment, :info, :fullname, :phone)")
    void addOrder(@Bind("id") String id, @Bind("userid") String userid, @Bind("totalprice") double totalPrice,
                 @Bind("email") String email, @Bind("shipaddress") String shipAddress, @Bind("payment") String payment,
                 @Bind("info") String info, @Bind("fullname") String fullname, @Bind("phone") String phone);

    @SqlQuery("SELECT id FROM wae.account WHERE username = :username")
    String findUserIdByUsername(@Bind("username") String username);
}


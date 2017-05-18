package wae.thesis.indiv.service.product.internal.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import wae.thesis.indiv.service.product.internal.model.Supplier;

import java.util.List;

/**
 * Created by Nguyen Tan Dat.
 */
@RegisterMapper(SupplierORM.class)
public interface ProductDAO {

    @SqlQuery("SELECT * FROM wae.supplier")
    List<Supplier> getAllSuppliers();

    @SqlQuery("SELECT * FROM wae.supplier WHERE type = :type")
    Supplier findSupplierByType(@Bind("type") String type);


    @SqlUpdate("UPDATE wae.supplier SET products = :api WHERE id = :id ")
    void updateProducts(@Bind("id") String id, @Bind("api") String api);
}

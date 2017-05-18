package wae.thesis.indiv.service.product.internal.behavior;

import org.skife.jdbi.v2.DBI;
import wae.thesis.indiv.api.ApiFetcher;
import wae.thesis.indiv.service.product.external.ProductBehavior;
import wae.thesis.indiv.service.product.external.ProductRepository;

/**
 * Created by Nguyen Tan Dat.
 */
public class ProductBehaviorImpl implements ProductBehavior {

    private final ApiFetcher apiFetcher;
    private final DBI dbi;

    public ProductBehaviorImpl(ApiFetcher apiFetcher, DBI dbi) {
        this.apiFetcher = apiFetcher;
        this.dbi = dbi;
    }

    @Override
    public ProductRepository getProductRepo() {
        return new ProductRepositoryImpl(apiFetcher, dbi);
    }
}

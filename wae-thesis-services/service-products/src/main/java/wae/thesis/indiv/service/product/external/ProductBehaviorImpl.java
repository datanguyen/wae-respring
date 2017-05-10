package wae.thesis.indiv.service.product.external;

import wae.thesis.indiv.service.product.internal.behavior.ProductBehavior;
import wae.thesis.indiv.service.product.internal.behavior.ProductRepository;

/**
 * Created by Nguyen Tan Dat.
 */
public class ProductBehaviorImpl implements ProductBehavior{

    @Override
    public ProductRepository getProductRepo() {
        return new ProductRepositoryImpl();
    }
}

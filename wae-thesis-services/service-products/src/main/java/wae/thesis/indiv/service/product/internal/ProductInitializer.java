package wae.thesis.indiv.service.product.internal;

import wae.thesis.indiv.api.behavior.ServiceInitializer;
import wae.thesis.indiv.api.model.ServiceInfo;
import wae.thesis.indiv.service.product.external.ProductBehavior;
import wae.thesis.indiv.service.product.external.ProductRepository;
import wae.thesis.indiv.service.product.internal.model.ProductApiType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nguyen Tan Dat.
 */
public class ProductInitializer implements ServiceInitializer {

    private final ProductBehavior productBehavior;
    private final ProductRepository productRepo;

    public ProductInitializer(ProductBehavior productBehavior) {
        this.productBehavior = productBehavior;
        this.productRepo = this.productBehavior.getProductRepo();
    }

    @Override
    public Map<String, Object> initialize(ServiceInfo serviceInfo) {
        Map<String, Object> data = new HashMap<>();

        data.put("foodApi", productRepo.fetchProductApi("/food-api", ProductApiType.FOOD_API));
        data.put("electronicApi", productRepo.fetchProductApi("/electronic-api", ProductApiType.ELECTRONIC_API));
        data.put("handmadeApi", productRepo.fetchProductApi("/handmade-api", ProductApiType.HANDMADE_API));

        return data;
    }
}

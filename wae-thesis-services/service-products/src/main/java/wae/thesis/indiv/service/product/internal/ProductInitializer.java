package wae.thesis.indiv.service.product.internal;

import wae.thesis.indiv.api.ApiFetcher;
import wae.thesis.indiv.api.behavior.ServiceInitializer;
import wae.thesis.indiv.api.model.ServiceInfo;
import wae.thesis.indiv.service.product.internal.behavior.ProductBehavior;
import wae.thesis.indiv.service.product.internal.behavior.ProductRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nguyen Tan Dat.
 */
public class ProductInitializer implements ServiceInitializer {

    private final ProductBehavior productBehavior;
    private final ApiFetcher apiFetcher;

    public ProductInitializer(ProductBehavior productBehavior, ApiFetcher apiFetcher) {
        this.productBehavior = productBehavior;
        this.apiFetcher = apiFetcher;
    }

    @Override
    public Map<String, Object> initialize(ServiceInfo serviceInfo) {
        Map<String, Object> data = new HashMap<>();

        String asdas = apiFetcher.fetch("/handmade-api");

        data.put("foodApi", productBehavior.getProductRepo().mapStringToCategory(apiFetcher.fetch("/food-api")));
        data.put("electronicApi", productBehavior.getProductRepo().mapStringToCategory(apiFetcher.fetch("/electronic-api")));
        data.put("handmadeApi", productBehavior.getProductRepo().mapStringToCategory(apiFetcher.fetch("/handmade-api")));

        return data;
    }
}

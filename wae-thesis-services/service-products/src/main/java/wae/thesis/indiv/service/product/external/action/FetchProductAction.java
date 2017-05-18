package wae.thesis.indiv.service.product.external.action;

import wae.thesis.indiv.api.behavior.ActionHandler;
import wae.thesis.indiv.api.model.ServiceInfo;
import wae.thesis.indiv.service.product.external.ProductRepository;
import wae.thesis.indiv.service.product.internal.model.ProductApiType;

/**
 * Created by Nguyen Tan Dat.
 */
public class FetchProductAction implements ActionHandler {

    private final ProductRepository productRepo;

    public FetchProductAction(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Object handle(ServiceInfo serviceInfo) {
        return productRepo.fetchProductApi("/food-api", ProductApiType.FOOD_API);
    }
}

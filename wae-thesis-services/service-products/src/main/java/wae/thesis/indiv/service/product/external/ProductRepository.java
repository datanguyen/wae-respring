package wae.thesis.indiv.service.product.external;

import wae.thesis.indiv.api.ApiFetcher;
import wae.thesis.indiv.service.product.internal.model.CategoryDFO;
import wae.thesis.indiv.service.product.internal.model.ProductApiType;

/**
 * Created by Nguyen Tan Dat.
 */
public interface ProductRepository {

    CategoryDFO fetchProductApi(String url, ProductApiType type);
}

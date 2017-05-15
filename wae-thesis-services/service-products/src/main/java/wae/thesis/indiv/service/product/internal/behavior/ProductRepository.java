package wae.thesis.indiv.service.product.internal.behavior;

import wae.thesis.indiv.api.ApiFetcher;
import wae.thesis.indiv.service.product.internal.model.CategoryDFO;

/**
 * Created by Nguyen Tan Dat.
 */
public interface ProductRepository {

    CategoryDFO mapStringToCategory(String value);
}

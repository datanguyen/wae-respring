package wae.thesis.indiv.service.product.internal.behavior;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.skife.jdbi.v2.DBI;
import wae.thesis.indiv.api.ApiFetcher;
import wae.thesis.indiv.service.product.external.ProductRepository;
import wae.thesis.indiv.service.product.internal.dao.ProductDAO;
import wae.thesis.indiv.service.product.internal.model.ProductApiType;
import wae.thesis.indiv.service.product.internal.model.Supplier;
import wae.thesis.indiv.service.product.internal.model.CategoryDFO;

import java.io.IOException;
import java.util.List;

/**
 * Created by Nguyen Tan Dat.
 */
public class ProductRepositoryImpl implements ProductRepository {

    private final ObjectMapper jacksonMapper = new ObjectMapper();
    private final ApiFetcher apiFetcher;
    private final DBI dbi;

    private ProductDAO productDAO;

    public ProductRepositoryImpl(ApiFetcher apiFetcher, DBI dbi) {
        this.apiFetcher = apiFetcher;
        this.dbi = dbi;
        this.productDAO = this.dbi.onDemand(ProductDAO.class);
    }

    @Override
    public CategoryDFO fetchProductApi(String url, ProductApiType type) {
        String rawApi = apiFetcher.fetch(url);
        Supplier supplier = productDAO.findSupplierByType(String.valueOf(type));

        if (rawApi == null) {
            rawApi = supplier.getProducts();
        }

        if (rawApi != null && !rawApi.equals(supplier.getProducts())) {
            productDAO.updateProducts(supplier.getId(), rawApi);
        }

        try {
            return jacksonMapper.readValue(rawApi, CategoryDFO.class);
        } catch (IOException e) {
            return null;
        }
    }
}

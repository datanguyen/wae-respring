package wae.thesis.indiv.service.product.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import wae.thesis.indiv.service.product.internal.behavior.ProductRepository;
import wae.thesis.indiv.service.product.internal.model.CategoryDFO;

import java.io.IOException;

/**
 * Created by Nguyen Tan Dat.
 */
public class ProductRepositoryImpl implements ProductRepository {

    private final ObjectMapper jacksonMapper = new ObjectMapper();

    @Override
    public CategoryDFO mapStringToCategory(String value) {
        try {
            return value == null ? null : jacksonMapper.readValue(value, CategoryDFO.class);
        } catch (IOException e) {
            return null;
        }
    }
}

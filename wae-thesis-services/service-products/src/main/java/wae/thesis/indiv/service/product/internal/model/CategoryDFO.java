package wae.thesis.indiv.service.product.internal.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by Nguyen Tan Dat.
 */

@Data
@AllArgsConstructor
public class CategoryDFO {
    private String category;
    private List<ProductDFO> products;
}

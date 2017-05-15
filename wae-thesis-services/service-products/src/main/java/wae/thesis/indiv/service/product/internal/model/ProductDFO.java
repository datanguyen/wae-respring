package wae.thesis.indiv.service.product.internal.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Nguyen Tan Dat.
 */

@AllArgsConstructor
@Data
public class ProductDFO {
    private String productName;
    private double productPrice;
    private String productImgUrl;
}

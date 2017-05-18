package wae.thesis.indiv.service.product.internal.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Nguyen Tan Dat.
 */

@AllArgsConstructor
@Data
public class Supplier {
    private String id;
    private String name;
    private String products;
    private String type;
}

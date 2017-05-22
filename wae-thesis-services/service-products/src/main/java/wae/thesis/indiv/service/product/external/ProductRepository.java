package wae.thesis.indiv.service.product.external;

import wae.thesis.indiv.service.product.internal.model.CategoryDFO;
import wae.thesis.indiv.service.product.internal.model.Order;
import wae.thesis.indiv.service.product.internal.model.OrderDTO;
import wae.thesis.indiv.service.product.internal.model.ProductApiType;

/**
 * Created by Nguyen Tan Dat.
 */
public interface ProductRepository {

    CategoryDFO fetchProductApi(String url, ProductApiType type);

    OrderDTO mapStringToDto(String data);

    String getNewOrderId();

    String findUserIdByUsername(String username);

    void addNewOrder(OrderDTO orderDTO);
}

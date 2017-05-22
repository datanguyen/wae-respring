package wae.thesis.indiv.service.product.internal.behavior;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.skife.jdbi.v2.DBI;
import wae.thesis.indiv.api.util.ApiFetcher;
import wae.thesis.indiv.service.product.external.ProductRepository;
import wae.thesis.indiv.service.product.internal.dao.OrderDAO;
import wae.thesis.indiv.service.product.internal.dao.ProductDAO;
import wae.thesis.indiv.service.product.internal.model.*;

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
    private OrderDAO orderDAO;

    public ProductRepositoryImpl(ApiFetcher apiFetcher, DBI dbi) {
        this.apiFetcher = apiFetcher;
        this.dbi = dbi;
        this.productDAO = this.dbi.onDemand(ProductDAO.class);
        this.orderDAO = this.dbi.onDemand(OrderDAO.class);
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

    @Override
    public OrderDTO mapStringToDto(String data) {
        try {
            return jacksonMapper.readValue(data, OrderDTO.class);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public String getNewOrderId() {
        List<Order> orders = orderDAO.getAllOrder();

        if (orders.size() == 0) {
            return "1";
        }

        int currentId = Integer.valueOf(orders.get(orders.size() - 1).getId());
        return String.valueOf(currentId + 1);
    }

    @Override
    public String findUserIdByUsername(String username) {
        return orderDAO.findUserIdByUsername(username);
    }

    @Override
    public void addNewOrder(OrderDTO orderDTO) {
        orderDAO.addOrder(
                getNewOrderId(),
                findUserIdByUsername(orderDTO.getUsername()),
                orderDTO.getTotalPrice(),
                orderDTO.getEmail(),
                orderDTO.getNumber() + ", " + orderDTO.getWard() + ", " + orderDTO.getDistrict() + ", " + orderDTO.getCity(),
                orderDTO.getPayment(),
                orderDTO.getAtmBank() + "-" + orderDTO.getAtmNumber(),
                orderDTO.getFullname(),
                orderDTO.getPhone()
        );
    }
}

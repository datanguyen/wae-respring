package wae.thesis.indiv.service.product.internal.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Nguyen Tan Dat.
 */

@AllArgsConstructor
@Data
public class Order {
    private String id;
    private String userid;
    private double totalPrice;
    private String email;
    private String shipaddress;
    private String payment;
    private String info;
    private String fullname;
    private String phone;
}

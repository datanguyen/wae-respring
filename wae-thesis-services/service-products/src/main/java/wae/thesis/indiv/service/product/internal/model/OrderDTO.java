package wae.thesis.indiv.service.product.internal.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Nguyen Tan Dat.
 */

@AllArgsConstructor
@Data
public class OrderDTO {
    private String fullname;
    private String email;
    private String city;
    private String district;
    private String ward;
    private String number;
    private String phone;
    private String payment;
    private double totalPrice;
    private String username;
    private String atmNumber;
    private String atmBank;
}

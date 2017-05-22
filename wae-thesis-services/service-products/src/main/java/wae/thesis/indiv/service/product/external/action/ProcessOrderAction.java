package wae.thesis.indiv.service.product.external.action;

import wae.thesis.indiv.api.behavior.ActionHandler;
import wae.thesis.indiv.api.model.ServiceInfo;
import wae.thesis.indiv.api.util.MailService;
import wae.thesis.indiv.service.product.external.ProductRepository;
import wae.thesis.indiv.service.product.internal.model.OrderDTO;

/**
 * Created by Nguyen Tan Dat.
 */
public class ProcessOrderAction implements ActionHandler {

    private final ProductRepository productRepo;

    public ProcessOrderAction(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Object handle(ServiceInfo serviceInfo) {
        String checkoutData = serviceInfo.getParam("checkoutData", "");
        OrderDTO orderDTO = productRepo.mapStringToDto(checkoutData);

        productRepo.addNewOrder(orderDTO);
        MailService.sendMail(orderDTO.getEmail(), createContent(orderDTO.getFullname(), orderDTO.getNumber() + " "
                + orderDTO.getWard() + " " + orderDTO.getDistrict() + " " + orderDTO.getCity(), orderDTO.getTotalPrice()));
        return orderDTO;
    }

    private String createContent(String fullname, String shippingAddress, double totalPrice) {
        return "Dear " + fullname + ","
                + "\n\nThank for your checkout to our webiste."
                + " Your checkout process has been finished successfully."
                + "Congratulations!!!\n\n"
                + "Shipping Address: " + shippingAddress
                + "\n\nTotal Price: " + totalPrice
                + "\n\nBest regards, \nE-Commerce Team.";
    }
}

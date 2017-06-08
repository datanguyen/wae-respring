package wae.thesis.indiv.service.product.internal;

import wae.thesis.indiv.api.ServiceBinder;
import wae.thesis.indiv.api.item.ActionType;
import wae.thesis.indiv.api.item.UserRole;
import wae.thesis.indiv.api.model.Action;
import wae.thesis.indiv.api.model.ServiceDef;
import wae.thesis.indiv.service.product.external.action.FetchProductAction;
import wae.thesis.indiv.service.product.external.ProductBehavior;
import wae.thesis.indiv.service.product.external.action.ProcessOrderAction;
import wae.thesis.indiv.service.product.external.action.VerifyCodeAction;

/**
 * Created by Nguyen Tan Dat.
 */

@ServiceBinder(className = ProductServiceDef.class)
public class ProductServiceDef extends ServiceDef {

    public ProductServiceDef(ProductBehavior productBehavior) {
        super("product", "Products", new ProductInitializer(productBehavior));

        addSubService(new ServiceDef("food", "Food Products")
              .addActionHandler(
                    new Action("view-food-products", ActionType.READ, UserRole.GUEST),
                    new FetchProductAction(productBehavior.getProductRepo())
              )
        );

        addSubService(new ServiceDef("electronic", "Electronic Products")
              .addActionHandler(
                    new Action("view-electronic-products", ActionType.READ, UserRole.GUEST),
                    new FetchProductAction(productBehavior.getProductRepo())
              )
        );

        addSubService(new ServiceDef("handmade", "Handmade Products")
              .addActionHandler(
                    new Action("view-handmade-products", ActionType.READ, UserRole.GUEST),
                    new FetchProductAction(productBehavior.getProductRepo())
              )
        );

        addSubService(new ServiceDef("process-order", "Process Order")
                .addAllowedRoles(UserRole.GUEST)
                .addActionHandler(
                        new Action("process-order", ActionType.CREATE, UserRole.GUEST),
                        new ProcessOrderAction(productBehavior.getProductRepo())
                )
        );

        addSubService(new ServiceDef("verify-number", "Verify Number")
        .addAllowedRoles(UserRole.GUEST)
                .addActionHandler(
                        new Action("verify-number", ActionType.CREATE, UserRole.GUEST),
                        new VerifyCodeAction()
                )
        );
    }
}

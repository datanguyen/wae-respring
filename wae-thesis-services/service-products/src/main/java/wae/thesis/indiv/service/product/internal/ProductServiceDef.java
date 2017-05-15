package wae.thesis.indiv.service.product.internal;

import wae.thesis.indiv.api.ApiFetcher;
import wae.thesis.indiv.api.ServiceBinder;
import wae.thesis.indiv.api.item.ActionType;
import wae.thesis.indiv.api.item.UserRole;
import wae.thesis.indiv.api.model.Action;
import wae.thesis.indiv.api.model.ServiceDef;
import wae.thesis.indiv.service.product.external.action.FetchProductAction;
import wae.thesis.indiv.service.product.internal.behavior.ProductBehavior;

/**
 * Created by Nguyen Tan Dat.
 */

@ServiceBinder(className = ProductServiceDef.class)
public class ProductServiceDef extends ServiceDef {

    public ProductServiceDef(ProductBehavior productBehavior, ApiFetcher apiFetcher) {
        super("product", "Products", new ProductInitializer(productBehavior, apiFetcher));

        addUnallowedRoles(UserRole.GUEST);

        addSubService(new ServiceDef("food", "Food Products")
              .addUnallowedRoles(UserRole.GUEST)
              .addActionHandler(
                    new Action("view-food-products", ActionType.READ, UserRole.ADMINISTRATORS),
                    new FetchProductAction()
              )
        );

        addSubService(new ServiceDef("electronic", "Electronic Products")
              .addUnallowedRoles(UserRole.GUEST)
              .addActionHandler(
                    new Action("view-electronic-products", ActionType.READ, UserRole.ADMINISTRATORS),
                    new FetchProductAction()
              )
        );

        addSubService(new ServiceDef("handmade", "Handmade Products")
              .addUnallowedRoles(UserRole.GUEST)
              .addActionHandler(
                    new Action("view-handmade-products", ActionType.READ, UserRole.ADMINISTRATORS),
                    new FetchProductAction()
              )
        );
    }
}

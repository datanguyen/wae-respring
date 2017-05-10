package wae.thesis.indiv.service.supplier.internal;

import wae.thesis.indiv.api.ServiceBinder;
import wae.thesis.indiv.api.item.ActionType;
import wae.thesis.indiv.api.item.UserRole;
import wae.thesis.indiv.api.model.Action;
import wae.thesis.indiv.api.model.ServiceDef;
import wae.thesis.indiv.service.supplier.external.action.SupplierViewAction;
import wae.thesis.indiv.service.supplier.internal.behavior.SupplierBehavior;

/**
 * Created by Nguyen Tan Dat.
 */

@ServiceBinder(className = SupplierServiceDef.class)
public class SupplierServiceDef extends ServiceDef {

    public SupplierServiceDef(SupplierBehavior supplierBehavior) {
        super("supplier", "Suppliers", new SupplierInitializer());

        addAllowedRoles(UserRole.SUPPLIERS);

        addSubService(new ServiceDef("view", "View")
              .addAllowedRoles(UserRole.SUPPLIERS)
              .addActionHandler(
                    new Action("view", ActionType.READ),
                    new SupplierViewAction()
              )
        );
    }
}

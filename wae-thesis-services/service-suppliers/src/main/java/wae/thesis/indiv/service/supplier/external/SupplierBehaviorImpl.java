package wae.thesis.indiv.service.supplier.external;

import wae.thesis.indiv.service.supplier.internal.behavior.SupplierBehavior;
import wae.thesis.indiv.service.supplier.internal.behavior.SupplierRepository;

/**
 * Created by Nguyen Tan Dat.
 */
public class SupplierBehaviorImpl implements SupplierBehavior {

    @Override
    public SupplierRepository getSupplierRepo() {
        return new SupplierRepositoryImpl();
    }
}

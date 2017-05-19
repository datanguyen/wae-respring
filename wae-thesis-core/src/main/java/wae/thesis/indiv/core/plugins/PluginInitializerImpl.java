package wae.thesis.indiv.core.plugins;

import lombok.Getter;
import org.skife.jdbi.v2.DBI;
import wae.thesis.indiv.api.ApiFetcher;
import wae.thesis.indiv.api.ServiceBinder;
import wae.thesis.indiv.api.behavior.ServiceBehavior;
import wae.thesis.indiv.api.model.ServiceDef;
import wae.thesis.indiv.api.storage.DataSource;
import wae.thesis.indiv.core.Constant;
import wae.thesis.indiv.core.CoreMessages;
import wae.thesis.indiv.core.ReflectionUtils;
import wae.thesis.indiv.core.ServiceBehaviorImpl;
import wae.thesis.indiv.service.product.internal.behavior.ProductBehaviorImpl;
import wae.thesis.indiv.service.product.internal.ProductServiceDef;
import wae.thesis.indiv.service.supplier.external.SupplierBehaviorImpl;
import wae.thesis.indiv.service.supplier.internal.SupplierServiceDef;
import wae.thesis.indiv.service.user.internal.behavior.UserBehaviorImpl;
import wae.thesis.indiv.service.user.internal.UserServiceDef;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nguyen Tan Dat.
 */

@Getter
public class PluginInitializerImpl implements PluginInitializer {

    private final CoreMessages coreMessages = new CoreMessages(Constant.CORE_PROPERTY_FILE);
    private final List<String> serviceDefPatterns = new ArrayList<>();

    private final ServiceBehavior serviceBehavior;
    private final DataSource dataSource;

    private ApiFetcher apiFetcher;

    PluginInitializerImpl(String serviceDefPatterns, String dataSourceUrl,
                          String dataSourceUsername, String dataSourcePassword) {
        initialize();
        this.serviceDefPatterns.addAll(Arrays.asList(serviceDefPatterns.split(",")));
        this.dataSource = new DataSource(dataSourceUrl, dataSourceUsername, dataSourcePassword);
        this.serviceBehavior = new ServiceBehaviorImpl(initServiceDefs(this.serviceDefPatterns), coreMessages);
    }

    private void initialize() {
        this.apiFetcher = new ApiFetcher(coreMessages.getMessage("api.port"));
    }

    @Override
    public List<ServiceDef> initServiceDefs(List<String> serviceDefNames) {

        List<Class> classes = serviceDefNames.stream()
              .map(ReflectionUtils::getAnnotationsByClassName)
              .map(annotations -> ReflectionUtils.getWantedAnnotation(annotations, ServiceBinder.class))
              .map(annotation -> (ServiceBinder) annotation)
              .map(ServiceBinder::className)
              .collect(Collectors.toList());

        return classes.stream()
              .map(this::initService)
              .collect(Collectors.toList());
    }

    @Override
    public ServiceBehavior getServiceBehaviors() {
        return getServiceBehavior();
    }

    @Override
    public DataSource getDataSources() {
        return getDataSource();
    }

    private ServiceDef initService(Class expectedClass) {
        DBI dbi = getDataSource().getDbi();
        ApiFetcher apiFetcher = getApiFetcher();

        String name = expectedClass.getSimpleName();
        ServiceDef serviceDef = null;

        switch (name) {
            case Constant.PRODUCT_SERVICE:
                serviceDef = new ProductServiceDef(new ProductBehaviorImpl(apiFetcher, dbi));
                break;

            case Constant.USER_SERVICE:
                serviceDef = new UserServiceDef(new UserBehaviorImpl(), dbi);
                break;

            case Constant.SUPPLIER_SERVICE:
                serviceDef = new SupplierServiceDef(new SupplierBehaviorImpl());
                break;

            default:
                break;
        }

        return serviceDef;
    }
}

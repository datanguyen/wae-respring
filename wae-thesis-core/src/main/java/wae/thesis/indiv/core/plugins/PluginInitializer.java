package wae.thesis.indiv.core.plugins;

import wae.thesis.indiv.api.behavior.ServiceBehavior;
import wae.thesis.indiv.api.model.ServiceDef;
import wae.thesis.indiv.api.storage.DataSource;

import java.util.List;

/**
 * Created by Nguyen Tan Dat.
 */

public interface PluginInitializer {

    List<ServiceDef> initServiceDefs(List<String> serviceDefNames);

    ServiceBehavior getServiceBehaviors();

    DataSource getDataSources();
}

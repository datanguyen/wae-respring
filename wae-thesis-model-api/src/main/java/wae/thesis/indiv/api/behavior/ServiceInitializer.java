package wae.thesis.indiv.api.behavior;

import wae.thesis.indiv.api.model.ServiceInfo;

import java.util.Map;

/**
 * Created by Nguyen Tan Dat.
 */

@FunctionalInterface
public interface ServiceInitializer {
    Map<String, Object> initialize(ServiceInfo serviceInfo);
}

package wae.thesis.indiv.api.behavior;

import wae.thesis.indiv.api.model.ServiceInfo;

/**
 * Created by Nguyen Tan Dat.
 */

@FunctionalInterface
public interface ActionHandler {
    Object handle(ServiceInfo serviceInfo);
}

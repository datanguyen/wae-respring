package wae.thesis.indiv.api.behavior;

import wae.thesis.indiv.api.dto.ServiceDto;
import wae.thesis.indiv.api.item.UserRole;
import wae.thesis.indiv.api.model.ServiceInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Nguyen Tan Dat.
 */


public interface ServiceBehavior {

    String buildPath(String ...pathSegments);

    List<ServiceDto> getServices(UserRole userRole, String ...parentPathSegments);

    Map initService(ServiceInfo serviceInfo);

    Object handlerAction(UserRole userRole, ServiceInfo serviceInfo);
}

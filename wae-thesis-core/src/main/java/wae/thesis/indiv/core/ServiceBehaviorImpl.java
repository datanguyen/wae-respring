package wae.thesis.indiv.core;

import org.apache.commons.lang3.StringUtils;
import wae.thesis.indiv.api.behavior.ActionHandler;
import wae.thesis.indiv.api.behavior.ServiceBehavior;
import wae.thesis.indiv.api.behavior.ServiceInitializer;
import wae.thesis.indiv.api.dto.ServiceDto;
import wae.thesis.indiv.api.exception.*;
import wae.thesis.indiv.api.item.ActionType;
import wae.thesis.indiv.api.item.ErrorCode;
import wae.thesis.indiv.api.item.UserRole;
import wae.thesis.indiv.api.model.Action;
import wae.thesis.indiv.api.model.ServiceDef;
import wae.thesis.indiv.api.model.ServiceInfo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static wae.thesis.indiv.api.model.ServiceDef.PATH_SEPARATOR;

/**
 * Created by Nguyen Tan Dat.
 */

public class ServiceBehaviorImpl implements ServiceBehavior {
    private final ServiceDef rootService = ServiceDef.createRootService();
    private final Map<String, ServiceDef> servicesByPath = new HashMap<>();
    private final CoreMessages coreMessages;

    public ServiceBehaviorImpl(List<ServiceDef> serviceDefs, CoreMessages coreMessages) {
        this.coreMessages = coreMessages;

        rootService.addSubServices(serviceDefs);
        buildServicesByPath(rootService, servicesByPath);
    }

    @Override
    public String buildPath(String... pathSegments) {
        String path = PATH_SEPARATOR;

        path += Stream.of(pathSegments)
              .map(pathSegment -> StringUtils.isNotEmpty(pathSegment) ? pathSegment : "")
              .collect(Collectors.joining(PATH_SEPARATOR));

        while (path.length() > PATH_SEPARATOR.length() && path.endsWith(PATH_SEPARATOR)) {
            path = path.substring(0, path.length() - 1);
        }

        return path;
    }

    @Override
    public List<ServiceDto> getServices(UserRole userRole, String... parentPathSegments) {
        String parentPath = buildPath(parentPathSegments);
        ServiceDef parentService = servicesByPath.get(parentPath);
        List<ServiceDto> serviceDtos = new ArrayList<>();

        if (parentService == null) {
            return Collections.emptyList();
        }

        parentService.getSubServices()
              .forEach(serviceDef -> {
                  List<ServiceDto> subServices = new ArrayList<>();
                  if (serviceDef.getSubServices() != null) {
                      String path = parentPath.length() >= PATH_SEPARATOR.length()
                            ? parentPath.substring(0, parentPath.length()) : "";
                      if (serviceDef.isAcceptedRole(userRole)) {
                          subServices = StringUtils.isNoneBlank(path)
                                ? getServices(userRole, path, serviceDef.getKey())
                                : getServices(userRole, serviceDef.getKey());

                          serviceDtos.add(new ServiceDto(serviceDef, subServices));
                      }
                  }
              });

        return serviceDtos;
    }

    @Override
    public Map<String, Object> initService(ServiceInfo serviceInfo) {
        ServiceDef serviceDef = getServiceDef(serviceInfo.getPath());

        if (serviceDef == null) {
            throw new ServiceNotFoundException(
                  coreMessages.noServiceForPath(serviceInfo.getPath()),
                  ErrorCode.SERVICE_NOT_FOUND);
        }

        ServiceInitializer initializer = serviceDef.getServiceInitializer();
        while (initializer == null && serviceDef != null) {
            serviceDef = serviceDef.getParentService();

            if (serviceDef != null) {
                initializer = serviceDef.getServiceInitializer();
            }
        }

        if (serviceDef == null) {
            return Collections.emptyMap();
        }

        return initializer.initialize(serviceInfo);
    }

    //TODO: Fix bug can access url without in app
    @Override
    public Object handlerAction(UserRole userRole, ServiceInfo serviceInfo) {

        ServiceDef serviceDef = getServiceDef(serviceInfo.getPath());
        if (serviceDef == null) {
            throw new ServiceNotFoundException(
                  coreMessages.noServiceForPath(serviceInfo.getPath()),
                  ErrorCode.SERVICE_NOT_FOUND);
        }

        if (!serviceDef.isAcceptedRole(userRole)) {
            throw new UnauthorizedException(
                  coreMessages.unauthorizedPermissionForRole(UserRole.GUEST),
                  ErrorCode.UNAUTHORIZED_PERMISSION);
        }

        Action action = getActionFromPath(serviceInfo.getPath(), serviceInfo.getType());
        if (action == null) {
            throw new ActionNotFoundException(
                  coreMessages.noActionForPath(serviceInfo.getPath()),
                  ErrorCode.ACTION_NOT_FOUND);
        }

        if (!action.isAcceptedRole(userRole)) {
            throw new UnauthorizedException(
                  coreMessages.unauthorizedPermissionForRole(UserRole.GUEST),
                  ErrorCode.UNAUTHORIZED_PERMISSION);
        }

        ActionHandler actionHandler = serviceDef.getActionHandler(action);
        if (actionHandler == null) {
            throw new HandlerNotFoundException(
                  coreMessages.noHandlerForAction(action.getName()),
                  ErrorCode.NO_ACTION_HANDLER);
        }

        return actionHandler.handle(serviceInfo);
    }

    private ServiceDef getServiceDef(String servicePath) {
        ServiceDef serviceDef = servicesByPath.get(servicePath);

        if (serviceDef == null) {
            int index = servicePath.lastIndexOf(PATH_SEPARATOR);
            if (index > 0) {
                String parentPath = servicePath.substring(0, index);
                serviceDef = servicesByPath.get(parentPath);
            }
        }

        return serviceDef;
    }

    private Action getActionFromPath(String servicePath, ActionType type) {
        ServiceDef serviceDef = servicesByPath.get(servicePath);

        if (serviceDef == null) {
            return Action.newDefaultAction();
        }

        int index = servicePath.lastIndexOf(PATH_SEPARATOR);
        if (index > 0) {
            String actionName = servicePath.substring(index + 1);
            return new Action(actionName, type);
        }

        return null;
    }

    private static void buildServicesByPath(ServiceDef serviceDef, Map<String, ServiceDef> output) {
        if (serviceDef == null) {
            return;
        }

        output.put(serviceDef.getPath(), serviceDef);
        serviceDef.getSubServices()
              .forEach(subService -> buildServicesByPath(subService, output));
    }
}

package wae.thesis.indiv.api.model;

import lombok.*;
import wae.thesis.indiv.api.ApiMessages;
import wae.thesis.indiv.api.behavior.ActionHandler;
import wae.thesis.indiv.api.behavior.ServiceInitializer;
import wae.thesis.indiv.api.exception.CoreException;
import wae.thesis.indiv.api.item.ErrorCode;
import wae.thesis.indiv.api.item.UserRole;

import java.util.*;

/**
 * Created by Nguyen Tan Dat.
 */

@Data
@EqualsAndHashCode(of = "key")
@ToString(of = {"key", "text"})
public class ServiceDef {
    private static final String SERVICE_KEY_PATTERN = "^[a-z0-9\\-]{3,}$";
    private static final String ROOT_KEY = "wae-service::root-key";

    public static final String PATH_SEPARATOR = "/";

    private boolean isShow;
    private final String key;
    private final String text;
    private final ServiceInitializer serviceInitializer;
    private final List<ServiceDef> subServices = new ArrayList<>();
    private final Set<UserRole> allowedRoles = new HashSet<>();
    private final Set<UserRole> unallowedRoles = new HashSet<>();

    @Getter(AccessLevel.NONE)
    private final Map<Action, ActionHandler> handlersByAction = new HashMap<>();

    private ServiceDef parentService;

    private ServiceDef(String key){
        this(key, null, true, null);
    }

    public ServiceDef(String key, String text) {
        this(key, text, null);
    }

    public ServiceDef(String key, String text, boolean isShow) {
        this(key, text, isShow, null);
    }

    public ServiceDef(String key, String text, ServiceInitializer serviceInitializer) {
        this(key, text, true, serviceInitializer);
    }

    public ServiceDef(String key, String text, ServiceInitializer serviceInitializer, UserRole ...allowedRoles) {
        this(key, text, true, serviceInitializer, allowedRoles);
    }

    public ServiceDef(String key, String text, boolean isShow,
                      ServiceInitializer serviceInitializer, UserRole ...allowedRoles) {
        if (!ROOT_KEY.equals(key) && !key.matches(SERVICE_KEY_PATTERN)) {
            throw new CoreException(
                  ApiMessages.invalidServiceDefKey(key),
                  ErrorCode.CORE_EXCEPTION);
        }

        this.key = key;
        this.text = text;
        this.isShow = isShow;
        this.serviceInitializer = serviceInitializer;
        this.allowedRoles.addAll(Arrays.asList(allowedRoles));
    }

    public static ServiceDef createRootService() {
        return new ServiceDef(ROOT_KEY);
    }

    public ServiceDef addActionHandler(ActionHandler handler) {
        return addActionHandler(Action.newDefaultAction(), handler);
    }

    public ServiceDef addActionHandler(Action action, ActionHandler handler) {
        if (handler == null) {
            throw new CoreException(
                  ApiMessages.actionHandlerNotFound(action.getName()),
                  ErrorCode.NO_ACTION_HANDLER);
        }

        action.setOwnerService(this);
        this.handlersByAction.put(action, handler);

        return this;
    }

    public ActionHandler getActionHandler(Action action) {
        ActionHandler actionHandler = handlersByAction.get(action);

        if (actionHandler == null) {
            actionHandler = handlersByAction.get(Action.newDefaultAction());
        }

        return actionHandler;
    }

    public ServiceDef addSubService(ServiceDef subServiceDef) {
        subServiceDef.setParentService(this);
        subServices.add(subServiceDef);

        return this;
    }

    public ServiceDef addSubServices(List<ServiceDef> subServicesDef) {
        subServicesDef.forEach(this::addSubService);

        return this;
    }

    public ServiceDef addAllowedRoles(UserRole ...allowedRole) {
        this.allowedRoles.addAll(Arrays.asList(allowedRole));

        return this;
    }

    public ServiceDef addUnallowedRoles(UserRole ...unallowedRole) {
        this.unallowedRoles.addAll(Arrays.asList(unallowedRole));

        return this;
    }

    protected boolean isShow() {
        return this.isShow;
    }

    protected void setShow(boolean isShow) {
        this.isShow = isShow;
    }

    public String getPath() {
        if (ROOT_KEY.equals(key)) {
            return PATH_SEPARATOR;
        }

        if (ROOT_KEY.equals(parentService.key)) {
            return PATH_SEPARATOR + key;
        }

        return parentService.getPath() + PATH_SEPARATOR + key;
    }

    public boolean isAcceptedRole(UserRole userRole) {
        if (userRole == null || unallowedRoles.contains(userRole)) {
            return false;
        }

        if (allowedRoles.contains(userRole)) {
            return true;
        }

        return allowedRoles.isEmpty() && (parentService == null || parentService.isAcceptedRole(userRole));
    }

}

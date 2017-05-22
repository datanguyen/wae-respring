package wae.thesis.indiv.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import wae.thesis.indiv.api.util.ApiMessages;
import wae.thesis.indiv.api.exception.CoreException;
import wae.thesis.indiv.api.item.ActionType;
import wae.thesis.indiv.api.item.ErrorCode;
import wae.thesis.indiv.api.item.UserRole;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nguyen Tan Dat.
 */
@Data
@EqualsAndHashCode(of = {"name", "type"})
public class Action {
    private static final String ACTION_NAME_PATTERN = "^[a-zA-Z0-9\\-]+$";
    private static final String DEFAULT_ACTION_NAME = "wae::default_action";

    private final String name;
    private final ActionType type;
    private final Set<UserRole> allowedRoles = new HashSet<>();
    private ServiceDef ownerService;

    public Action(String name, ActionType type, UserRole ...allowedRoles) {
        if (StringUtils.isEmpty(name) || (!name.matches(ACTION_NAME_PATTERN) && !DEFAULT_ACTION_NAME.equals(name))) {
            throw new CoreException(
                  ApiMessages.invalidActionName(name),
                  ErrorCode.CORE_EXCEPTION);
        }

        this.name = name.toLowerCase();
        this.type = (type != null) ? type : ActionType.READ;

        if (allowedRoles.length > 0) {
            this.allowedRoles.addAll(Arrays.asList(allowedRoles));
        }
    }

    public static Action newDefaultAction() {
        return new Action(DEFAULT_ACTION_NAME, ActionType.READ);
    }

    public boolean isAcceptedRole(UserRole role) {
        return allowedRoles.contains(role)
              || (allowedRoles.isEmpty() && (this.ownerService == null || this.ownerService.isAcceptedRole(role)));
    }

    public void addUserRole(UserRole role) {
        allowedRoles.add(role);
    }

}

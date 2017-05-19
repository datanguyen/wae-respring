package wae.thesis.indiv.service.user.internal;

import org.skife.jdbi.v2.DBI;
import wae.thesis.indiv.api.ServiceBinder;
import wae.thesis.indiv.api.item.ActionType;
import wae.thesis.indiv.api.item.UserRole;
import wae.thesis.indiv.api.model.Action;
import wae.thesis.indiv.api.model.ServiceDef;
import wae.thesis.indiv.service.user.external.action.UserSignInAction;
import wae.thesis.indiv.service.user.external.action.UserSignUpAction;
import wae.thesis.indiv.service.user.external.UserBehavior;

/**
 * Created by Nguyen Tan Dat.
 */

@ServiceBinder(className = UserServiceDef.class)
public class UserServiceDef extends ServiceDef {

    public UserServiceDef(UserBehavior userBehavior, DBI dbi) {
        super("user", "Users", new UserInitializer());

        addSubService(new ServiceDef("sign-in", "Sign In")
              .addAllowedRoles(UserRole.GUEST)
              .addActionHandler(
                    new Action("sign-in", ActionType.READ, UserRole.GUEST),
                    new UserSignInAction())
        );

        addSubService(new ServiceDef("sign-up", "Sign Up")
              .addAllowedRoles(UserRole.GUEST)
              .addActionHandler(
                    new Action("sign-up", ActionType.CREATE, UserRole.GUEST),
                    new UserSignUpAction(dbi))
        );
    }
}

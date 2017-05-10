package wae.thesis.indiv.service.user.external;

import wae.thesis.indiv.service.user.internal.behavior.UserBehavior;
import wae.thesis.indiv.service.user.internal.behavior.UserRepository;

/**
 * Created by Nguyen Tan Dat.
 */
public class UserBehaviorImpl implements UserBehavior {

    @Override
    public UserRepository getUserRepo() {
        return new UserRepositoryImpl();
    }
}

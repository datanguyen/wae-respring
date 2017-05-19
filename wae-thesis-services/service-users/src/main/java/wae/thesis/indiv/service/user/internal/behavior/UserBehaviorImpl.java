package wae.thesis.indiv.service.user.internal.behavior;

import wae.thesis.indiv.service.user.external.UserBehavior;
import wae.thesis.indiv.service.user.external.UserRepository;

/**
 * Created by Nguyen Tan Dat.
 */
public class UserBehaviorImpl implements UserBehavior {

    @Override
    public UserRepository getUserRepo() {
        return new UserRepositoryImpl();
    }
}

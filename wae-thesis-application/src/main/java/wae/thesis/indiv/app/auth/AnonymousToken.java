package wae.thesis.indiv.app.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import wae.thesis.indiv.app.auth.jwt.JwtAuthenticationToken;
import wae.thesis.indiv.app.user.UserContext;

import java.util.Collection;

/**
 * Created by Nguyen Tan Dat.
 */
public class AnonymousToken extends JwtAuthenticationToken {

    private final UserContext anonymousUser = new UserContext("anonymousUser", null);

    public AnonymousToken(UserContext userContext) {
        super(userContext);
        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return anonymousUser;
    }

    public static AnonymousToken getAnonymousToken() {
        return new AnonymousToken(new UserContext("anonymousUser", null));
    }
}

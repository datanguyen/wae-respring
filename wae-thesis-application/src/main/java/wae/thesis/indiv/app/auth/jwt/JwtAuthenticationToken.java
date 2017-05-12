package wae.thesis.indiv.app.auth.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import wae.thesis.indiv.api.token.RawAccessToken;
import wae.thesis.indiv.app.user.UserContext;

/**
 * Created by Nguyen Tan Dat.
 */

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private RawAccessToken rawAccessToken;
    private UserContext userContext;

    public JwtAuthenticationToken(RawAccessToken unsafeToken) {
        super(null);
        this.rawAccessToken = unsafeToken;
        super.setAuthenticated(false);
    }

    public JwtAuthenticationToken(UserContext userContext) {
        super(userContext.getAuthorities());
        this.eraseCredentials();
        this.userContext = userContext;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.rawAccessToken;
    }

    @Override
    public Object getPrincipal() {
        return this.userContext;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.rawAccessToken = null;
    }
}

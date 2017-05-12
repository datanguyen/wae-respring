package wae.thesis.indiv.app.auth.account;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Created by Nguyen Tan Dat.
 */

public class AccountAuthenticationToken extends AbstractAuthenticationToken {
    private String username;
    private String password;

    public AccountAuthenticationToken(String username, String password) {
        super(null);
        this.username = username;
        this.password = password;
        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return this.username;
    }

    @Override
    public Object getPrincipal() {
        return this.password;
    }
}

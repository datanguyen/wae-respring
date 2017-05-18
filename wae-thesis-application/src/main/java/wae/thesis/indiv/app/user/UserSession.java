package wae.thesis.indiv.app.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;
import wae.thesis.indiv.app.auth.AnonymousToken;

/**
 * Created by Nguyen Tan Dat.
 */

@AllArgsConstructor
@Data
public class UserSession {
    private boolean isAuthenticated = false;
    private UserContext user;

    public UserSession() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        user = principal.equals("anonymousUser")
                ? new UserContext("anonymousUser", null)
                : (UserContext) principal;

        if (!user.getUsername().equals("anonymousUser") && user.getAuthorities() != null) {
            isAuthenticated = true;
        }
    }
}

package wae.thesis.indiv.app.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * Created by Nguyen Tan Dat.
 */

@Data
@AllArgsConstructor
public class UserContext {
    private final String username;
    private final List<GrantedAuthority> authorities;
}

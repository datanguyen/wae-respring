package wae.thesis.indiv.app.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import wae.thesis.indiv.api.token.RawAccessToken;
import wae.thesis.indiv.app.config.JwtConfig;
import wae.thesis.indiv.app.user.UserContext;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nguyen Tan Dat.
 */
@Component
@SuppressWarnings("unchecked")
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtConfig jwtConfig;

    @Autowired
    public JwtAuthenticationProvider(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessToken rawAccessToken = (RawAccessToken) authentication.getCredentials();

        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtConfig.getTokenSigningKey());
        String subject = jwsClaims.getBody().getSubject();
        List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
        List<GrantedAuthority> authorities = scopes.stream()
              .map(SimpleGrantedAuthority::new)
              .collect(Collectors.toList());

        return new JwtAuthenticationToken(new UserContext(subject, authorities));
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (JwtAuthenticationToken.class.isAssignableFrom(aClass));
    }
}

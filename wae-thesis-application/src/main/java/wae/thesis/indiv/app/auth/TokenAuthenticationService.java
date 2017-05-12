package wae.thesis.indiv.app.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import wae.thesis.indiv.api.item.Scopes;
import wae.thesis.indiv.api.token.AccessToken;
import wae.thesis.indiv.api.token.JwtToken;
import wae.thesis.indiv.app.config.JwtConfig;
import wae.thesis.indiv.app.user.UserContext;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Nguyen Tan Dat.
 */

@AllArgsConstructor
public class TokenAuthenticationService {
    private final JwtConfig jwtConfig;

    public AccessToken createTokenByUserContext(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getUsername())) {
            return null;
        }

        if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty()) {
            return null;
        }
        LocalDateTime currentTime = LocalDateTime.now();

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        claims.put("scopes",
              userContext.getAuthorities().stream()
                    .map(GrantedAuthority::toString)
                    .collect(Collectors.toList()));

        String token = Jwts.builder()
              .setClaims(claims)
              .setIssuer(jwtConfig.getTokenIssuer())
              .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
              .setExpiration(Date.from(
                    currentTime
                          .plusMinutes(jwtConfig.getTokenExpirationTime())
                          .atZone(ZoneId.systemDefault())
                          .toInstant()))
              .signWith(SignatureAlgorithm.HS512, jwtConfig.getTokenSigningKey())
              .compact();

        return new AccessToken(token, claims);
    }

    public JwtToken createRefreshToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getUsername())) {
            return null;
        }
        LocalDateTime currentTime = LocalDateTime.now();

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        claims.put("scopes", claims.put("scopes", Collections.singletonList(Scopes.REFRESH_TOKEN.authority())));

        String token = Jwts.builder()
              .setClaims(claims)
              .setIssuer(jwtConfig.getTokenIssuer())
              .setId(UUID.randomUUID().toString())
              .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
              .setExpiration(Date.from(
                    currentTime
                          .plusMinutes(jwtConfig.getRefreshTokenExpTime())
                          .atZone(ZoneId.systemDefault())
                          .toInstant()))
              .signWith(SignatureAlgorithm.HS512, jwtConfig.getTokenSigningKey())
              .compact();

        return new AccessToken(token, claims);
    }

    public String extract(String payload) {
        if (StringUtils.isBlank(payload)) {
            throw new AuthenticationServiceException("Header cannot be blank!");
        }

        if (payload.length() < jwtConfig.getTokenHeaderPrefix().length()) {
            throw new AuthenticationServiceException("Invalid authorization header size.");
        }

        return payload.substring(jwtConfig.getTokenHeaderPrefix().length() + 1, payload.length());
    }
}

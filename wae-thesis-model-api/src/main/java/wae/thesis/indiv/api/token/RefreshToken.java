package wae.thesis.indiv.api.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.AllArgsConstructor;
import lombok.Getter;
import wae.thesis.indiv.api.item.Scopes;

import java.util.List;
import java.util.Optional;

/**
 * Created by Nguyen Tan Dat.
 */

@SuppressWarnings("unchecked")
@AllArgsConstructor
public class RefreshToken implements JwtToken{

    @Getter
    private Jws<Claims> claims;

    @Override
    public String getToken() {
        return null;
    }

    public String getJti() {
        return claims.getBody().getId();
    }

    public String getSubject() {
        return claims.getBody().getSubject();
    }

    public static Optional<RefreshToken> create(RawAccessToken token, String signingKey) {
        Jws<Claims> claims = token.parseClaims(signingKey);
        List<String> scopes= claims.getBody().get("scopes", List.class);

        if (scopes == null || scopes.isEmpty()
              || scopes.stream()
              .noneMatch(scope -> Scopes.REFRESH_TOKEN.authority().equals(scope))) {
            return Optional.empty();
        }

        return Optional.of(new RefreshToken(claims));
    }
}

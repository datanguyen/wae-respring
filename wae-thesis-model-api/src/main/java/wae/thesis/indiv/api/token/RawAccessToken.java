package wae.thesis.indiv.api.token;

import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import wae.thesis.indiv.api.exception.TokenExpiredException;
import wae.thesis.indiv.api.item.ErrorCode;
import wae.thesis.indiv.api.item.Message;

/**
 * Created by Nguyen Tan Dat.
 */

@AllArgsConstructor
public class RawAccessToken implements JwtToken {
    private String token;

    public Jws<Claims> parseClaims(String signingKey) {
        try {
            return Jwts.parser()
                  .setSigningKey(signingKey)
                  .parseClaimsJws(this.token);
        } catch (UnsupportedJwtException | MalformedJwtException |
              IllegalArgumentException | SignatureException | ExpiredJwtException e) {
            throw new TokenExpiredException(Message.TOKEN_EXPIRED.getMessage(), ErrorCode.TOKEN_EXPIRED);
        }
    }

    @Override
    public String getToken() {
        return token;
    }
}

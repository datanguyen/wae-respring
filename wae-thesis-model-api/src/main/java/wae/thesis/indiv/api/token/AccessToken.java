package wae.thesis.indiv.api.token;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Nguyen Tan Dat.
 */

@Data
@AllArgsConstructor
public class AccessToken implements JwtToken {

    private final String rawToken;
    @JsonIgnore private Claims claims;

    @Override
    public String getToken() {
        return this.rawToken;
    }
}

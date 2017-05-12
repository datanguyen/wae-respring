package wae.thesis.indiv.app.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Nguyen Tan Dat.
 */

@Configuration
@NoArgsConstructor
@Data
public class JwtConfig {

    @Value("${jwt.config.tokenExpiredTime}")
    private int tokenExpirationTime;

    @Value("${jwt.config.tokenIssuer}")
    private String tokenIssuer;

    @Value("${jwt.config.tokenSigningKey}")
    private String tokenSigningKey;

    @Value("${jwt.config.refreshTokenExpiredTime}")
    private int refreshTokenExpTime;

    @Value("${jwt.config.tokenHeaderPrefix}")
    private String tokenHeaderPrefix;
}

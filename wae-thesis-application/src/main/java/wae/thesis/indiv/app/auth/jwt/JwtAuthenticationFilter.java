package wae.thesis.indiv.app.auth.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import wae.thesis.indiv.api.exception.AuthenticationFailedException;
import wae.thesis.indiv.api.exception.TokenNotFound;
import wae.thesis.indiv.api.item.ErrorCode;
import wae.thesis.indiv.api.item.Message;
import wae.thesis.indiv.api.token.RawAccessToken;
import wae.thesis.indiv.app.auth.TokenAuthenticationService;
import wae.thesis.indiv.app.config.JwtConfig;
import wae.thesis.indiv.app.config.SecurityConfig;
import wae.thesis.indiv.core.Constant;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Nguyen Tan Dat.
 */
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final TokenAuthenticationService tokenAuthenticationService;

    public JwtAuthenticationFilter(AuthenticationManager authManager, JwtConfig jwtConfig) {
        super(new AntPathRequestMatcher(Constant.FILTER_REQUEST_PATH));
        this.tokenAuthenticationService = new TokenAuthenticationService(jwtConfig);
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
          throws AuthenticationException, IOException, ServletException {
        String tokenPayload = request.getHeader(SecurityConfig.JWT_HEADER_PARAM);

        if (tokenPayload == null) {
            throw new TokenNotFound(Message.TOKEN_NOT_FOUND.getMessage(), ErrorCode.TOKEN_NOT_FOUND);
        }
        RawAccessToken rawAccessToken = new RawAccessToken(tokenAuthenticationService.extract(tokenPayload));

        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(rawAccessToken));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authResult);
        SecurityContextHolder.setContext(securityContext);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        throw new AuthenticationFailedException(Message.AUTHENTICATION_FAILED.getMessage(),
              ErrorCode.AUTHENTICATION_FAILED);
    }
}

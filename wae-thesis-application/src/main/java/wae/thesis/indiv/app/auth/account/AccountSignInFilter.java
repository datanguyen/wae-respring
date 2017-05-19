package wae.thesis.indiv.app.auth.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import wae.thesis.indiv.api.exception.AuthenticationFailedException;
import wae.thesis.indiv.api.item.ErrorCode;
import wae.thesis.indiv.api.item.Message;
import wae.thesis.indiv.api.token.JwtToken;
import wae.thesis.indiv.app.auth.TokenAuthenticationService;
import wae.thesis.indiv.app.config.JwtConfig;
import wae.thesis.indiv.app.config.SecurityConfig;
import wae.thesis.indiv.app.user.AccountDto;
import wae.thesis.indiv.app.user.UserContext;
import wae.thesis.indiv.core.Constant;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nguyen Tan Dat.
 */
public class AccountSignInFilter extends AbstractAuthenticationProcessingFilter {

    private final TokenAuthenticationService tokenAuthenticationService;
    private final ObjectMapper mapper = new ObjectMapper();

    public AccountSignInFilter(AuthenticationManager authManager, JwtConfig jwtConfig) {
        super(new AntPathRequestMatcher(Constant.SIGNIN_REQUEST_PATH));
        this.tokenAuthenticationService = new TokenAuthenticationService(jwtConfig);
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
          throws AuthenticationException, IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        String dto = request.getHeader(SecurityConfig.ACCOUNT_HEADER_PARAM);

        if (dto == null) {
            throw new BadCredentialsException("USERNAME_OR_PASSWORD_NOT_FOUND");
        }

        AccountDto accountDto = objectMapper.readValue(dto, AccountDto.class);
        return getAuthenticationManager()
              .authenticate(new AccountAuthenticationToken(accountDto.getUsername(), accountDto.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        UserContext userContext = (UserContext) authResult.getPrincipal();
        JwtToken accessToken = tokenAuthenticationService.createTokenByUserContext(userContext);
        JwtToken refreshToken = tokenAuthenticationService.createRefreshToken(userContext);

        Map<String, String> tokenByName = new HashMap<>();
        tokenByName.put("token", accessToken.getToken());
        tokenByName.put("refreshToken", refreshToken.getToken());

        response.setStatus(HttpStatus.OK.value());
        response.addHeader(SecurityConfig.JWT_HEADER_PARAM, mapper.writeValueAsString(tokenByName));

        HttpSession session = request.getSession(false);
        if (session == null) return;
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        response.getWriter().write("WAE_WARNING_UNAUTHORIZED_PERMISSION");
    }
}

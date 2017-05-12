package wae.thesis.indiv.app.auth.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import wae.thesis.indiv.api.exception.AuthenticationFailedException;
import wae.thesis.indiv.api.item.ErrorCode;
import wae.thesis.indiv.api.item.Message;
import wae.thesis.indiv.app.auth.jwt.JwtAuthenticationToken;
import wae.thesis.indiv.app.user.Account;
import wae.thesis.indiv.app.user.AccountRepository;
import wae.thesis.indiv.app.user.UserContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Nguyen Tan Dat.
 */

@Component
@SuppressWarnings("unchecked")
public class AccountAuthenticationProvider implements AuthenticationProvider {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AccountRepository accountRepository;

    @Autowired
    public AccountAuthenticationProvider(BCryptPasswordEncoder bCryptPasswordEncoder, AccountRepository accountRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.accountRepository = accountRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getCredentials();
        String password = (String) authentication.getPrincipal();
        Account account = accountRepository.findAccountByUsername(username);

        if (account == null || !bCryptPasswordEncoder.matches(password, account.getPassword())) {
            throw new AuthenticationFailedException(Message.AUTHENTICATION_FAILED.getMessage(),
                  ErrorCode.AUTHENTICATION_FAILED);
        }

        List<GrantedAuthority> authorities = Stream.of(account.getRoles().split(" "))
              .map(SimpleGrantedAuthority::new)
              .collect(Collectors.toList());

        return new JwtAuthenticationToken(new UserContext(username, authorities));
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (AccountAuthenticationToken.class.isAssignableFrom(aClass));
    }
}

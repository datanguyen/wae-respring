package wae.thesis.indiv.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import wae.thesis.indiv.api.item.UserRole;
import wae.thesis.indiv.app.auth.account.AccountAuthenticationProvider;
import wae.thesis.indiv.app.auth.account.AccountSignInFilter;
import wae.thesis.indiv.app.auth.jwt.JwtAuthenticationFilter;
import wae.thesis.indiv.app.auth.jwt.JwtAuthenticationProvider;

/**
 * Created by Nguyen Tan Dat.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String ACCOUNT_HEADER_PARAM = "Account_Header_Param";
    public static final String JWT_HEADER_PARAM = "Authorization";

    private final AccountAuthenticationProvider accountAuthenticationProvider;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final JwtConfig jwtConfig;

    @Autowired
    public SecurityConfig(AccountAuthenticationProvider accountAuthenticationProvider,
                          JwtAuthenticationProvider jwtAuthenticationProvider, JwtConfig jwtConfig) {
        this.accountAuthenticationProvider = accountAuthenticationProvider;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(accountAuthenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
        auth.inMemoryAuthentication()
              .withUser("wae")
              .password("eaw")
              .roles(String.valueOf(UserRole.GUEST));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
              .csrf().disable()
              .authorizeRequests()
              .antMatchers("/signin").authenticated()
              .antMatchers("/account").authenticated()
              .anyRequest().permitAll()
              .and()
              .addFilterBefore(new AccountSignInFilter(authenticationManager(), jwtConfig),
                    UsernamePasswordAuthenticationFilter.class)
              .addFilterBefore(new JwtAuthenticationFilter(authenticationManager(), jwtConfig),
                    UsernamePasswordAuthenticationFilter.class);
    }
}

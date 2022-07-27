package hac.ex4.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * security with spring boot
 */
@Configuration
@EnableWebSecurity
public class ApplicationConfig extends WebSecurityConfigurerAdapter {

    /**
     * configure security
     * @param auth - authentication manager
     * @throws Exception - exception if error
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        /**
         * admin and user details are stored in memory
         */
        PasswordEncoder encoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
                .inMemoryAuthentication()
                .withUser("admin").password(encoder.encode("password")).roles("ADMIN")
                .and()
                .withUser("user1").password(encoder.encode("user")).roles("USER")
                .and()
                .withUser("user2").password(encoder.encode("user")).roles("USER")
                .and()
                .withUser("user3").password(encoder.encode("user")).roles("USER");
    }

    /**
     * function to set permissions for users and admin
     *
     * @param http - http security object to set permissions
     * @throws Exception - exception if error
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .formLogin()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/shopping-cart").hasRole("USER")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403.html");
    }
}

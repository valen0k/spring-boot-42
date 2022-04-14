package edu.school21.springboot42.config;

import edu.school21.springboot42.models.Role;
import edu.school21.springboot42.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler authSuccessHandler() {
        return new AuthSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthFailureHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**", "/profile").hasRole(String.valueOf(Role.ADMIN))
                .antMatchers("/films", "/sessions", "/films/**", "/sessions/**").hasAnyRole(
                        String.valueOf(Role.ADMIN),
                        String.valueOf(Role.USER)
                )
                .antMatchers("/confirm/*", "/sign*", "/css/sign*", "/js/sign*", "/img/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().usernameParameter("email").loginPage("/signIn")
                .successHandler(authSuccessHandler())
                .failureHandler(authenticationFailureHandler())
                .and()
                .logout().deleteCookies("JSESSIONID").permitAll()
                .and()
                .rememberMe().key("uniqueAndSecret")
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }

}

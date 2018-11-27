package org.dougllasfps.application.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Criado por dougllas.sousa em 09/10/2018.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService( userDetailsService );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable();
        http
            .authorizeRequests()
            .antMatchers("/javax.faces.resource/**").permitAll()
            .anyRequest().authenticated()
        .and()
            .formLogin()
            .loginPage("/pages/login.xhtml")
            .permitAll()
            .failureUrl("/pages/login.xhtml?auth=failure")
            .permitAll()
            .defaultSuccessUrl("/pages/controleacesso/permissao/permissao-search.xhtml?faces-redirect=true")
        .and()
            .logout()
            .invalidateHttpSession(true)
            .logoutSuccessUrl("/pages/login.xhtml?faces-redirect=true");
    }
}

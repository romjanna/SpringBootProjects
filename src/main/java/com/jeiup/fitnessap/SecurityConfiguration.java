package com.jeiup.fitnessap;

import com.jeiup.fitnessap.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService detailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .and()
            .authorizeRequests()
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/admin").hasAnyRole("ADMIN", "USER")
                .antMatchers("/").permitAll()
            .and()
                .formLogin()
                .permitAll()
            .and()
                .logout()
                .permitAll()
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
            .and()
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
            .and().and()
                .headers()
                .frameOptions().deny()
                .xssProtection()
                .and()
                .contentSecurityPolicy("script-src 'self'");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

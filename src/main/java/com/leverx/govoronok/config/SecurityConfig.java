package com.leverx.govoronok.config;

import com.leverx.govoronok.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig (UserService userService, PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/games/**","/traders/**","/users/**").authenticated()
                .antMatchers("/games","/traders/**","/users/**","/my").authenticated()
                .antMatchers("/administration/**").hasAuthority("ADMINISTRATOR")
                //.anyRequest().authenticated()
                 .antMatchers("/games/*/**").hasAnyAuthority("ADMINISTRATOR","TRADER")
                .and()
                .formLogin()
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .invalidateHttpSession(true);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
}

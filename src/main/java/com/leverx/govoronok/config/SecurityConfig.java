package com.leverx.govoronok.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/games/**","/traders/**","/my/**").authenticated()
                .antMatchers("/administration/**").hasRole("ADMINISTRATOR")
                .and()
                .formLogin()//можно указать свою форму
                .and()
                .logout();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.security;

import com.zer0time.pfebackend.service.UserService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



/**
 *
 * @author saad
 */

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
    private final UserService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
     
        http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST,SecurityConstants.SIGN_UP_URL,"/users/sign")
                
                .permitAll().antMatchers(HttpMethod.GET,"/users/downloadFile/**").permitAll()
                .antMatchers(HttpMethod.POST,"/taches/").permitAll()
                  .antMatchers(HttpMethod.POST,"/users/updateimage").permitAll()
                .anyRequest().authenticated().and()
                .addFilter(getAuthenticationFilter())//authentication filter
                .addFilter(new AuthorizationFilter(authenticationManager()))//authorization filter
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        
        
        
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
    
    public AuthenticationFilter getAuthenticationFilter () throws Exception {
        final AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager());
        authenticationFilter.setFilterProcessesUrl("/users/login");
        return authenticationFilter;
    }
    
}

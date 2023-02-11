/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.security;

import com.zer0time.pfebackend.SpringApplicationContext;
import com.zer0time.pfebackend.service.UserService;
import com.zer0time.pfebackend.shared.dto.UserDto;
import com.zer0time.pfebackend.ui.model.request.UserLoginRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author saad
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter  {
    private final AuthenticationManager authenticationManager ;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
       
        try {
            UserLoginRequestModel cred   = new ObjectMapper()
                    .readValue(request.getInputStream(), UserLoginRequestModel.class);
            
            return authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            cred.getEmail(), cred.getPassword(), new ArrayList<>()) );
        } catch (IOException ex) {
        throw new RuntimeException();
        }
        
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
      String userName  = ((User) authResult.getPrincipal()).getUsername();
      String token = Jwts.builder()
              .setSubject(userName)
              .setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
              .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
              .compact();
      
      UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
      UserDto userDto =  userService.getUser(userName);
      response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX +token);
      response.addHeader("UserID", userDto.getUserId());
  
              
    }
                
    
    
}
    

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.security;

import com.zer0time.pfebackend.SpringApplicationContext;

/**
 *
 * @author saad
 */
public class SecurityConstants {
    public static final long EXPIRATION_TIME = 864000000L ; //10 jours    miliseconde
    public static final String TOKEN_PREFIX = "Bearer "; 
    public static final String HEADER_STRING = "Authorization"; 
    public static final String SIGN_UP_URL= "/users"; 
  //  public static final String TOKEN_SECRET = ""; 
    
    
    public static String getTokenSecret(){
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
        return  appProperties.getTokenSecret();
   
    }
}

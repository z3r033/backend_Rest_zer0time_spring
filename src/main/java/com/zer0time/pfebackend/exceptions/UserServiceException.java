/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.exceptions;

/**
 *
 * @author saad
 */
public class UserServiceException extends RuntimeException{
    private static final Long serialVersionUID =1L;
    
    public UserServiceException(String message){
        super(message);
    }
}

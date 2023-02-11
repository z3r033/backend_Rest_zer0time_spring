/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.ui.model.response;

import java.util.Date;

/**
 *
 * @author saad
 */
public class ErrorMessage {
    
    private Date timestamp; 
    private String message;
    public ErrorMessage(){}
    public ErrorMessage(Date timestamp , String message){
        this.timestamp = timestamp ; 
        this.message= message; 
        
    }
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}

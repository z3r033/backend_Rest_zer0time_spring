/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.ui.model.response;

/**
 *
 * @author saad
 */
public enum ErrorMessages {
    
    
    MISSING_REQUIRED_FILED("Missing required field. Please check documentation for required fields "),
    RECORD_ALREADY_EXISTS("record already exists"),
    INTERNAL_SERVER_ERROR("internal server error"),
    NO_RECORD_FOUND("record with provided id not found"),
    AUTHENTICATION_FAILED("Authentication failed"),
    COULD_NOT_UPDATE_RECORD("could not update rercord"),
    COULD_NOT_DELETE_RECORD("could not delete record"),
    EMAIL_ADDRESS_NOT_VERIFIED("email address not verified");
    
    private String errorMessage;
    ErrorMessages(String errorMessage){
        this.errorMessage=errorMessage; 
    }
    public String getErrorMessage(){
        return errorMessage;
    }
    public void setErrorMessage(){
        this.errorMessage = errorMessage;
    }
}

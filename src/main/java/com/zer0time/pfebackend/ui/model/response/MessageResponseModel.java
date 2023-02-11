/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.ui.model.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author saad
 */
public class MessageResponseModel {

    private String senderId ; 
    private String receiverId ; 

    private String message ; 
    private String sendid;
    private String recid;
    private LocalDate publicationDate;
   
    private LocalTime publicationTime;
    public String getSendid() {
        return sendid;
    }

    public void setSendid(String sendid) {
        this.sendid = sendid;
    }

    public String getRecid() {
        return recid;
    }

    public void setRecid(String recid) {
        this.recid = recid;
    }


  
    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public LocalTime getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(LocalTime publicationTime) {
        this.publicationTime = publicationTime;
    }
    
    
    
}

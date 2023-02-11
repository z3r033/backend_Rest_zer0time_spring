/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.io.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

/**
 *
 * @author saad
 */
@Entity(name="messages")
public class MessageEntity implements Serializable{
  private static final Long serialVersionUID= 1L; 
  
    
   // 
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageid;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senderId", referencedColumnName = "id")
    private UserEntity sender;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiverId", referencedColumnName = "id")
    private UserEntity receiver;
    
    @Column(nullable=false)
     private String sendid ; 
   
     @Column(nullable=false)
    private String recid ;  

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
  
    
    @Column(nullable=false)
    private String message ; 
    
    @Column(nullable=false)
    @CreatedDate
    private LocalDate publicationDate ;
    
    @Column(nullable=false)
    @CreationTimestamp
    private LocalTime publicationTime;

    public Long getMessageid() {
        return messageid;
    }

    public void setMessageid(Long Messageid) {
        this.messageid = Messageid;
    }

    

    public UserEntity getSender() {
        return sender;
    }

    public void setSender(UserEntity sender) {
        this.sender = sender;
    }

    public UserEntity getReceiver() {
        return receiver;
    }

    public void setReceiver(UserEntity receiver) {
        this.receiver = receiver;
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



    
    
    
 
    
    
    
    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) {
            return true;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        return messageid != null && messageid.equals(((MessageEntity) obj).messageid);
    }
    @Override
    public int hashCode() {
        return 2021;
    }

	 
}

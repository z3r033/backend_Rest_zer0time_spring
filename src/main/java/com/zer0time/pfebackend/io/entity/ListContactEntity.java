/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.io.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author saad
 */
@Entity(name="listcontacts")
public class ListContactEntity implements Serializable {
    
    
     private static final Long serialVersionUID= 1L; 
  
    
   
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id", referencedColumnName = "id")
    private UserEntity utilisateur;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_contact_id", referencedColumnName = "id")
    private UserEntity utilisateur_contact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UserEntity utilisateur) {
        this.utilisateur = utilisateur;
    }

    public UserEntity getUtilisateur_contact() {
        return utilisateur_contact;
    }

    public void setUtilisateur_contact(UserEntity utilisateur_contact) {
        this.utilisateur_contact = utilisateur_contact;
    }
    
    
}

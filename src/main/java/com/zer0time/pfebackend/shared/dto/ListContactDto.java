/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.shared.dto;

import com.zer0time.pfebackend.io.entity.UserEntity;
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
public class ListContactDto {

    private Long id;
    private String utilisateur_id;

    private String utilisateur_contact_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(String utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public String getUtilisateur_contact_id() {
        return utilisateur_contact_id;
    }

    public void setUtilisateur_contact_id(String utilisateur_contact_id) {
        this.utilisateur_contact_id = utilisateur_contact_id;
    }

}

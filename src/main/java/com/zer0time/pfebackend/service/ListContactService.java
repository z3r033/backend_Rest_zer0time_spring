/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.service;

import com.zer0time.pfebackend.shared.dto.UserDto;

import java.util.List;

/**
 *
 * @author saad
 */
public interface ListContactService {

    public String ajouterContact(String utilisateur_id, String utilisateur_contact_id);

    public List<UserDto> getContacts(String utilisateur_id);

    public String deleteContact(String utilisateur_id, String utilisateur_contact_id);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.service.impl;

import com.zer0time.pfebackend.io.entity.ListContactEntity;
import com.zer0time.pfebackend.io.entity.MessageEntity;
import com.zer0time.pfebackend.io.entity.UserEntity;
import com.zer0time.pfebackend.io.repository.ListContactRepository;
import com.zer0time.pfebackend.io.repository.UserRepository;
import com.zer0time.pfebackend.service.ListContactService;
import com.zer0time.pfebackend.shared.dto.MessageDto;
import com.zer0time.pfebackend.shared.dto.UserDto;
import com.zer0time.pfebackend.ui.model.response.UserRest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author saad
 */
@Service
public class ListContactServiceImpl implements ListContactService{
 @Autowired
      ListContactService listContactService ;
      @Autowired
      ListContactRepository listContactRepository ;
          @Autowired
    UserRepository userRepository; 
    @Override
    public String ajouterContact(String utilisateur_id, String utilisateur_contact_id) {
        ListContactEntity contactEntity = new ListContactEntity();
     ListContactEntity contactEntity2 = new ListContactEntity();
     
            UserEntity user = userRepository.findByUserId(utilisateur_id.toString());
            UserEntity contact = userRepository.findByUserId(utilisateur_contact_id.toString());
                contactEntity.setUtilisateur(user);
            contactEntity.setUtilisateur_contact(contact);
          //  messageEntity.setRecid((String)messageDto.getReceiverId());
             // messageEntity.setSendid((String)messageDto.getSenderId() );
            listContactRepository.save(contactEntity);
             contactEntity2.setUtilisateur(contact);
            contactEntity2.setUtilisateur_contact(user);
          //  messageEntity.setRecid((String)messageDto.getReceiverId());
             // messageEntity.setSendid((String)messageDto.getSenderId() );
            listContactRepository.save(contactEntity2);
            return "success";
    }
    @Transactional
    @Override
    public List<UserDto> getContacts(String utilisateur_id) {
        
        UserEntity user  = userRepository.findByUserId(utilisateur_id);
           List <ListContactEntity>contacts = listContactRepository.fetchContacts(user.getId().toString());
   
               List <UserDto> returnedValue = new ArrayList<>();    
     for(ListContactEntity conentity : contacts ) {
         
            UserDto userdto = new UserDto();
            UserEntity userr =conentity.getUtilisateur_contact();
         
            BeanUtils.copyProperties(userr, userdto);
            
            returnedValue.add(userdto);
     }
             //  BeanUtils.copyProperties(messages, returnedValue);
               return returnedValue;
    }

    @Override
    public String deleteContact(String utilisateur_id, String utilisateur_contact_id) {
        ListContactEntity contactEntity = new ListContactEntity();
     ListContactEntity contactEntity2 = new ListContactEntity();
     
            UserEntity user = userRepository.findByUserId(utilisateur_id.toString());
            UserEntity contact = userRepository.findByUserId(utilisateur_contact_id.toString());
            
            
             listContactRepository.deleteByUtilisateur_id(user.getId(),contact.getId());
          
            
       
            return "success";
    }
     
    
    
}

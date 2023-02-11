/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.ui.controller;

import com.zer0time.pfebackend.io.repository.ListContactRepository;
import com.zer0time.pfebackend.service.ListContactService;
import com.zer0time.pfebackend.service.UserService;
import com.zer0time.pfebackend.shared.dto.UserDto;
import com.zer0time.pfebackend.ui.model.response.UserRest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author saad
 */
@RestController
@RequestMapping("contacts")
public class ListContactsController {
       @Autowired
      ListContactService listContactService ;
      @Autowired
      ListContactRepository listContactRepository ;
        @Autowired
    UserService userService ;
      
       @PostMapping
    public String ajouterContact(@RequestParam(value="utilisateur_id") String utilisateur_id , @RequestParam(value="utilisateur_contact_id") String utilisateur_contact_id) throws Exception{
 
 
        String createdcontact = listContactService.ajouterContact(utilisateur_id,utilisateur_contact_id);
        
        return createdcontact ; 
 
    }
        @GetMapping
    public   List<UserRest> getContacts(@RequestParam(value="utilisateur_id") String utilisateur_id
        ) {
   
     List <UserRest> returnedValue = new ArrayList<>();
     List<UserDto> users  =  listContactService.getContacts(utilisateur_id);
     
     for(UserDto listcon : users ) {
         
            UserRest userrest = new UserRest();
            BeanUtils.copyProperties(listcon, userrest);
            returnedValue.add(userrest);
     }
  //  BeanUtils.copyProperties(messagedto, returnedValue);
      //  List <MessageEntity>messages = messageRepository.fetchMessages(sender_id,receiver_id);
    return returnedValue; 
    }
    
    
         @GetMapping("/notin")
    public  List<UserRest> getUsersNotInProject( @RequestParam(value="utilisateur_id") String utilisateur_id ) {
        
   
     List <UserRest> returnedValue = new ArrayList<>();
     List<UserDto> users  =  userService.getUsersNotInContact(utilisateur_id);
     
     for(UserDto userdto : users ) {
         
            UserRest userrest = new UserRest();
            BeanUtils.copyProperties(userdto, userrest);
            returnedValue.add(userrest);
     }
    return returnedValue; 
    }
    
        @PostMapping("/delete")
    public String deleteContact(@RequestParam(value="utilisateur_id") String utilisateur_id, @RequestParam(value="utilisateur_contact_id") String utilisateur_contact_id) {
         String createdcontact = listContactService.deleteContact(utilisateur_id,utilisateur_contact_id);
        
        return createdcontact ; 
    }
    
      
}

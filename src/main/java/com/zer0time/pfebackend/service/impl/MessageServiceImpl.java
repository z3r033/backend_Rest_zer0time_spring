/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.service.impl;

import com.zer0time.pfebackend.io.entity.MessageEntity;
import com.zer0time.pfebackend.io.entity.UserEntity;
import com.zer0time.pfebackend.io.repository.MessageRepository;
import com.zer0time.pfebackend.io.repository.UserRepository;
import com.zer0time.pfebackend.service.MessageService;
import com.zer0time.pfebackend.shared.dto.MessageDto;
import com.zer0time.pfebackend.ui.model.response.MessageResponseModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author saad
 */
@Service
public class MessageServiceImpl implements  MessageService {

    @Autowired
    MessageRepository messageRepository; 
    @Autowired
    UserRepository userRepository; 
    @Override
    public MessageDto ajouterMessage(MessageDto messageDto) {
       
    //    if(userRepository.findUserByEmail(userDto.getEmail())!=null) throw new RuntimeException("email already exists");
            
            
            
            MessageEntity messageEntity = new MessageEntity();
            
            UserEntity sender_id = userRepository.findByUserId(messageDto.getSenderId());
            UserEntity receiver_id = userRepository.findByUserId(messageDto.getReceiverId());
            BeanUtils.copyProperties(messageDto, messageEntity);
             
            messageEntity.setSender(sender_id);
            messageEntity.setReceiver(receiver_id);
          //  messageEntity.setRecid((String)messageDto.getReceiverId());
             // messageEntity.setSendid((String)messageDto.getSenderId() );
            MessageEntity storedMessageDetail = messageRepository.save(messageEntity);
            MessageDto returnValue = new MessageDto();
            BeanUtils.copyProperties(storedMessageDetail, returnValue);
            return returnValue ;
    }

    @Override
    public  List<MessageDto> getMessages(String sender_id, String receiver_id) {
     //    MessageDto returnedValue = new MessageDto();
       List <MessageEntity>messages = messageRepository.fetchMessages(sender_id,receiver_id);
      // if(messages == null) throw new UsernameNotFoundException(sender_id);
               List <MessageDto> returnedValue = new ArrayList<>();    
     for(MessageEntity messageentity : messages ) {
         
            MessageDto messagedto = new MessageDto();
            BeanUtils.copyProperties(messageentity, messagedto);
            returnedValue.add(messagedto);
     }
             //  BeanUtils.copyProperties(messages, returnedValue);
               return returnedValue;

    }
    
}

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.ui.controller;

import com.zer0time.pfebackend.io.entity.MessageEntity;
import com.zer0time.pfebackend.io.repository.MessageRepository;
import com.zer0time.pfebackend.service.MessageService;
import com.zer0time.pfebackend.shared.dto.MessageDto;
import com.zer0time.pfebackend.shared.dto.UserDto;
import com.zer0time.pfebackend.ui.model.request.GetMessagesRequestModel;
import com.zer0time.pfebackend.ui.model.request.MessageRequestModel;
import com.zer0time.pfebackend.ui.model.request.UserDetailsRequestModel;
import com.zer0time.pfebackend.ui.model.response.MessageResponseModel;
import com.zer0time.pfebackend.ui.model.response.UserRest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author saad
 */
@RestController
@RequestMapping("messages")
public class MessageController {
      @Autowired
      MessageService messageService ;
      @Autowired
      MessageRepository messageRepository ;
    @PostMapping
    public MessageResponseModel ajouterMessages(@RequestBody MessageRequestModel messages) throws Exception{
 
    MessageResponseModel returnedValue = new MessageResponseModel(); 
 // if(userDetails.getFirstName().isEmpty()) throw new UserServiceException (ErrorMessages.MISSING_REQUIRED_FILED.getErrorMessage());

        MessageDto messageDto = new MessageDto();
        BeanUtils.copyProperties(messages,messageDto);
  
        messageDto.setPublicationDate(LocalDate.now());
        messageDto.setPublicationTime(LocalTime.now());
        messageDto.setSendid(messages.getSenderId().toString());
        messageDto.setRecid(messages.getReceiverId().toString());
        MessageDto createdMessage = messageService.ajouterMessage(messageDto);
        BeanUtils.copyProperties(createdMessage, returnedValue);
        return returnedValue ; 
 
    }
    @GetMapping
    public   List<MessageResponseModel> getMessages(@RequestParam(value="senderid") String sender_id,
         @RequestParam(value="receiverid") String receiver_id  ) {
    //  public   List<MessageResponseModel> getMessages(@RequestParam(value="page" ,defaultValue="1") String sender_id,
             //     @RequestParam(value="limit" ,defaultValue="26") String receiver_id  ) {
  // public   List<MessageResponseModel> getMessages(@RequestParam Map<String,String> requestParams) {
    //      String sender_id=requestParams.get("sender_id");
      //       String receiver_id=requestParams.get("receiver_id");
     List <MessageResponseModel> returnedValue = new ArrayList<>();
     List<MessageDto> messages  =  messageService.getMessages(sender_id,receiver_id);
     
     for(MessageDto messagedto : messages ) {
         
            MessageResponseModel userMessageResponseModel = new MessageResponseModel();
            BeanUtils.copyProperties(messagedto, userMessageResponseModel);
            returnedValue.add(userMessageResponseModel);
     }
  //  BeanUtils.copyProperties(messagedto, returnedValue);
      //  List <MessageEntity>messages = messageRepository.fetchMessages(sender_id,receiver_id);
    return returnedValue; 
    }
   
    
}

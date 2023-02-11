/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.service;

import com.zer0time.pfebackend.io.entity.MessageEntity;
import com.zer0time.pfebackend.shared.dto.MessageDto;
import java.util.List;

/**
 *
 * @author saad
 */
public interface MessageService {

     MessageDto ajouterMessage(MessageDto messageDto);

     List<MessageDto> getMessages(String sender_id, String receiver_id);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.io.repository;

import com.zer0time.pfebackend.io.entity.MessageEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author saad
 */
@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, Long>{
       @Query(value="SELECT * FROM messages u WHERE (u.sendid =:sender_id AND u.recid=:receiver_id) OR  (u.sendid =:receiver_id AND u.recid=:sender_id)",nativeQuery = true)
       List<MessageEntity> fetchMessages(@Param("sender_id") String sender_id, @Param("receiver_id") String receiver_id);
} 

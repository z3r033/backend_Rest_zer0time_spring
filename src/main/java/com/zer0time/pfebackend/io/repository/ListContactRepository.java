/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.io.repository;

import com.zer0time.pfebackend.io.entity.ListContactEntity;
import com.zer0time.pfebackend.io.entity.MessageEntity;
import com.zer0time.pfebackend.io.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author saad
 */
@Repository
public interface ListContactRepository extends CrudRepository<ListContactEntity, Long>{
   @Query(value="SELECT * FROM listcontacts u WHERE u.utilisateur_id=:utilisateur_id",nativeQuery = true)
    public List<ListContactEntity> fetchContacts(String utilisateur_id);
    @Transactional 
    @Modifying
    @Query(value="delete FROM listcontacts  WHERE (utilisateur_id=:id and utilisateur_contact_id=:userId) or (utilisateur_id=:userId and utilisateur_contact_id=:id)",nativeQuery = true)
    public void deleteByUtilisateur_id(Long id, Long userId);
    
      
    
}

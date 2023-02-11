/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.io.repository;


import com.zer0time.pfebackend.io.entity.TacheEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author saad
 */
@Repository
public interface TacheRepository extends CrudRepository<TacheEntity, Long> {

    public TacheEntity findProjectById(Long tache_id);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.service.impl;

import com.zer0time.pfebackend.io.entity.ProjectEntity;
import com.zer0time.pfebackend.io.entity.TacheEntity;
import com.zer0time.pfebackend.io.entity.UserEntity;
import com.zer0time.pfebackend.io.repository.ProjectRepository;
import com.zer0time.pfebackend.io.repository.TacheRepository;
import com.zer0time.pfebackend.io.repository.UserRepository;
import com.zer0time.pfebackend.service.TacheService;
import com.zer0time.pfebackend.shared.dto.ProjectDto;
import com.zer0time.pfebackend.shared.dto.TacheDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author saad
 */
@Service
public class TacheServiceImpl implements TacheService {
  @Autowired
    ProjectRepository projectRepository; 
  
    @Autowired
    TacheRepository tacheRepository; 
      @Autowired
    UserRepository userRepository; 
    
    @Transactional
    @Override
    public TacheDto ajouterTache(TacheDto tacheDto) {
          ProjectEntity  projectEntity = new ProjectEntity();
            
            UserEntity creator_id = userRepository.findByUserId(tacheDto.getCreatortache_id());
   
            
          
            ProjectEntity project = projectRepository.findProjectById(tacheDto.getProjecttache_id());
            TacheEntity tacheEntity = new TacheEntity();
            
            BeanUtils.copyProperties(tacheDto, tacheEntity);
            tacheEntity.setCreatorTache(creator_id);
             TacheEntity storedTacheDetail = tacheRepository.save(tacheEntity);
            project.addTache(storedTacheDetail);
       
       
            projectRepository.save(project);
            TacheDto returnValue = new TacheDto();
            BeanUtils.copyProperties(storedTacheDetail, returnValue);
            
            returnValue.setCreatortache_id(tacheDto.getCreatortache_id());
            returnValue.setProjecttache_id(tacheDto.getProjecttache_id());
            return returnValue;
    }
    @Transactional
    @Override
    public List<TacheDto> getTacheByProject(Long project_id) {
          ProjectEntity projectEntity = projectRepository.findProjectById(project_id);
     List<TacheEntity> taches=  projectEntity.getProjecttaches();
           List <TacheDto> returnedValue = new ArrayList<>();    
     for(TacheEntity tacheentity : taches ) {
         
            TacheDto tachedto = new TacheDto();
            BeanUtils.copyProperties(tacheentity, tachedto);
            UserEntity creator = tacheentity.getCreatorTache();
            ProjectEntity project = tacheentity.getProjecttache();
            tachedto.setCreatortache_id(creator.getUserId());
            tachedto.setProjecttache_id(project.getId().longValue());
            returnedValue.add(tachedto);
     }
               return returnedValue;
    }

    
}

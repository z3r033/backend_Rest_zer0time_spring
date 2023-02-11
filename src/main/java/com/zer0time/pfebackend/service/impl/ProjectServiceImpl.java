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
import com.zer0time.pfebackend.service.ProjectService;
import com.zer0time.pfebackend.shared.dto.ProjectDto;
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
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    ProjectRepository projectRepository; 
      @Autowired
    TacheRepository tacheRepository; 
    @Autowired
    UserRepository userRepository; 
    @Transactional
    @Override
    public ProjectDto ajouterProject(ProjectDto projectDto) {
        ProjectEntity  projectEntity = new ProjectEntity();
            
            UserEntity creator_id = userRepository.findByUserId(projectDto.getCreator_id());
            BeanUtils.copyProperties(projectDto, projectEntity);
            projectEntity.setCreator(creator_id);
       
           ProjectEntity storedProjectDetail = projectRepository.save(projectEntity);
           creator_id.addProject(storedProjectDetail);
             
             userRepository.save(creator_id);
            ProjectDto returnValue = new ProjectDto();
            BeanUtils.copyProperties(storedProjectDetail, returnValue);
            returnValue.setCreator_id(projectDto.getCreator_id());
            return returnValue ;
    }

    @Override
    public List<ProjectDto> getProjectsByUser(String creator_id) {
         List <ProjectEntity>projects = projectRepository.fetchProjectsById(creator_id);
  
        List <ProjectDto> returnedValue = new ArrayList<>();    
     for(ProjectEntity projectentity : projects ) {
         
            ProjectDto projectdto = new ProjectDto();
            BeanUtils.copyProperties(projectentity, projectdto);
            projectdto.setCreator_id(creator_id);
            returnedValue.add(projectdto);
     }
               return returnedValue;
    }

    @Override
    public List<ProjectDto> getProjectAll() {
     return null; 
    }

    @Override
    public String ajouterUserToProject(String project_id, String user_id) {
     return "ok";
    }
    @Transactional
    @Override
    public void deleteProject(Long id) {
        ProjectEntity projectEntity = projectRepository.findProjectById(id);
        UserEntity user = userRepository.findByUserId(projectEntity.getCreator().getUserId());
        user.removeProject(projectEntity);
        userRepository.save(user);
      //  projectRepository.delete(projectEntity);
    }
    
    @Transactional
    @Override
    public String deleteProjectToUser(Long project_id, Long tache_id) {
  
     ProjectEntity project = projectRepository.findProjectById(project_id);
        TacheEntity tache = tacheRepository.findProjectById(tache_id);
     project.removeTache(tache);
     
     projectRepository.save(project);
         
         return "cet tache a ete supprimer";
    }

  
    
}

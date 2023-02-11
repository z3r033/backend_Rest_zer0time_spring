/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.ui.controller;

import com.zer0time.pfebackend.service.ProjectService;
import com.zer0time.pfebackend.shared.dto.MessageDto;
import com.zer0time.pfebackend.shared.dto.ProjectDto;
import com.zer0time.pfebackend.ui.model.request.ProjectDetailsRequestModel;
import com.zer0time.pfebackend.ui.model.response.MessageResponseModel;
import com.zer0time.pfebackend.ui.model.response.OperStatusModel;
import com.zer0time.pfebackend.ui.model.response.ProjectRest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("projects")
public class ProjectController {
      @Autowired
      ProjectService projectService ;
    
    @GetMapping
    public  List<ProjectRest> getProjectsById(@RequestParam(value="creator_id") String creator_id  ) {
   
     List <ProjectRest> returnedValue = new ArrayList<>();
     List<ProjectDto> projects  =  projectService.getProjectsByUser(creator_id);
     
     for(ProjectDto projectdto : projects ) {
         
            ProjectRest projectrest = new ProjectRest();
            BeanUtils.copyProperties(projectdto, projectrest);
            returnedValue.add(projectrest);
     }
    return returnedValue; 
    }

    @PostMapping
public ProjectRest ajouterProject(@RequestBody ProjectDetailsRequestModel project) {
 
         ProjectRest returnedValue = new ProjectRest(); 

        ProjectDto projectDto = new ProjectDto();
        BeanUtils.copyProperties(project,projectDto);
  
        ProjectDto createdproject = projectService.ajouterProject(projectDto);
        BeanUtils.copyProperties(createdproject, returnedValue);
      
    return returnedValue; 
    
}

    @DeleteMapping(path = "/{id}")
public OperStatusModel deleteProject(@PathVariable Long id) {
OperStatusModel returnedValue =new OperStatusModel();
returnedValue.setOperName("delete");

projectService.deleteProject(id);
returnedValue.setOperResult("success");
return returnedValue;
}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.ui.controller;

import com.zer0time.pfebackend.exceptions.UserServiceException;
import com.zer0time.pfebackend.io.repository.ProjectRepository;
import com.zer0time.pfebackend.io.repository.UserRepository;
import com.zer0time.pfebackend.service.ProjectService;
import com.zer0time.pfebackend.service.UserService;
import com.zer0time.pfebackend.shared.dto.ProjectDto;
import com.zer0time.pfebackend.shared.dto.UserDto;
import com.zer0time.pfebackend.ui.model.request.UserDetailsRequestModel;
import com.zer0time.pfebackend.ui.model.response.ErrorMessages;
import com.zer0time.pfebackend.ui.model.response.ProjectRest;
import com.zer0time.pfebackend.ui.model.response.UserRest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@RequestMapping("project_users")
public class ProjectUsersController {
    
       @Autowired
       UserService userService ;
         @Autowired
         UserRepository userRepository ;
           @Autowired
           ProjectService projectService ; 
         
@PostMapping 
public String createUser (@RequestParam(value="project_id") Long project_id, @RequestParam(value="user_id") String user_id) throws Exception {
    
    
  String returnedValue = userService.ajouterProjectToUser(project_id,user_id);

 return returnedValue ; 
}
@PostMapping ("delete")
public String deleteUser (@RequestParam(value="project_id") Long project_id, @RequestParam(value="user_id") String user_id) throws Exception {
    
    
  String returnedValue = userService.deleteProjectToUser(project_id,user_id);

 return returnedValue ; 
}

@PostMapping ("deletetache")
public String deletetache(@RequestParam(value="project_id") Long project_id, @RequestParam(value="tache_id") Long tache_id) throws Exception {
    
    
  String returnedValue = projectService.deleteProjectToUser(project_id,tache_id);

 return returnedValue ; 
}
   @GetMapping
    public  List<ProjectRest> getProjectsById(@RequestParam(value="user_id") String user_id  ) {
   
     List <ProjectRest> returnedValue = new ArrayList<>();
     List<ProjectDto> projects  =  userService.getProjectsByUser(user_id);
     
     for(ProjectDto projectdto : projects ) {
         
            ProjectRest projectrest = new ProjectRest();
            BeanUtils.copyProperties(projectdto, projectrest);
            returnedValue.add(projectrest);
     }
    return returnedValue; 
    }

    
}

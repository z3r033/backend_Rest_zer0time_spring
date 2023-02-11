/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.ui.controller;

import com.zer0time.pfebackend.io.repository.UserRepository;
import com.zer0time.pfebackend.service.UserService;
import com.zer0time.pfebackend.shared.dto.ProjectDto;
import com.zer0time.pfebackend.shared.dto.UserDto;
import com.zer0time.pfebackend.ui.model.response.ProjectRest;
import com.zer0time.pfebackend.ui.model.response.UserRest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author saad
 */
@RestController
@RequestMapping("usersproject")
public class UsersNotInProjectController {
        @Autowired
       UserService userService ;
         @Autowired
         UserRepository userRepository ;
         
          @GetMapping("/notin")
    public  List<UserRest> getUsersNotInProject( @RequestParam(value="project_id") Long project_id ) {
        
   
     List <UserRest> returnedValue = new ArrayList<>();
     List<UserDto> users  =  userService.getUsersInProject(project_id);
     
     for(UserDto userdto : users ) {
         
            UserRest userrest = new UserRest();
            BeanUtils.copyProperties(userdto, userrest);
            returnedValue.add(userrest);
     }
    return returnedValue; 
    }
     
          @GetMapping("in")
    public  List<UserRest> getUserInProject( @RequestParam(value="project_id") Long project_id ) {
        
   
     List <UserRest> returnedValue = new ArrayList<>();
     List<UserDto> users  =  userService.getUsersNotInProject(project_id);
     
     for(UserDto userdto : users ) {
         
            UserRest userrest = new UserRest();
            BeanUtils.copyProperties(userdto, userrest);
            returnedValue.add(userrest);
     }
    return returnedValue; 
    }

    
}

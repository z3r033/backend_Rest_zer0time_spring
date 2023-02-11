/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.service;

import com.zer0time.pfebackend.shared.dto.ProjectDto;
import com.zer0time.pfebackend.shared.dto.UserDto;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author saad
 */

public interface UserService extends UserDetailsService{
    UserDto createUser(UserDto userDto);
    UserDto getUser(String email);
    UserDto getUserById(String id);

    public String ajouterProjectToUser(Long project_id , String user_id);

   UserDto updateUser(String id, UserDto userDto);
    void deleteUser(String userId);

    public List<ProjectDto> getProjectsByUser(String user_id);

    public List<UserDto> getUsersNotInProject(Long project_id);

     public List<UserDto> getUsersNotInContact(String user_id);
    
    public List<UserDto> getUsersInProject(Long project_id);

  //  public String storeFile(MultipartFile file, String firstName, String lastName, String email, String telephone, String password);

    public UserDto updateUser(UserDto userDto);

    public UserDto updatePassword(UserDto userDto);

    public UserDto updateImage(UserDto userDto);

    public String deleteProjectToUser(Long project_id, String user_id);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.service.impl;


import com.zer0time.pfebackend.exceptions.FileStorageException;
import com.zer0time.pfebackend.exceptions.MyFileNotFoundException;
import com.zer0time.pfebackend.io.entity.ListContactEntity;
import com.zer0time.pfebackend.io.entity.ProjectEntity;
import com.zer0time.pfebackend.io.entity.UserEntity;
import com.zer0time.pfebackend.io.repository.ListContactRepository;
import com.zer0time.pfebackend.io.repository.ProjectRepository;
import com.zer0time.pfebackend.io.repository.UserRepository;
import com.zer0time.pfebackend.service.UserService;
import com.zer0time.pfebackend.shared.FileStorageProperties;
import com.zer0time.pfebackend.shared.Utils;
import com.zer0time.pfebackend.shared.dto.ProjectDto;
import com.zer0time.pfebackend.shared.dto.UserDto;
import com.zer0time.pfebackend.ui.model.response.ErrorMessages;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
/**
 *
 * @author saad
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository; 
    @Autowired
    ProjectRepository projectRepository; 
    @Autowired
    Utils utils; 
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
     @Autowired
      ListContactRepository listContactRepository ;
  /*  private final Path fileStorageLocation;
    @Autowired
    public UserServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
         //   throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }*/
    @Override
    public UserDto createUser(UserDto userDto) {
        
        
        if(userRepository.findUserByEmail(userDto.getEmail())!=null) throw new RuntimeException("email already exists");
            
            
            
            UserEntity user = new UserEntity();
            BeanUtils.copyProperties(userDto, user);
             
           String publicUserId = utils.generateUserId(30);
            
            user.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            user.setUserId(publicUserId);
            UserEntity storedUserDetail = userRepository.save(user);
            UserDto returnValue = new UserDto();
            BeanUtils.copyProperties(storedUserDetail, returnValue);
            return returnValue ;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        
       UserEntity userEntity =  userRepository.findUserByEmail(email);
       if(userEntity == null) throw new UsernameNotFoundException(email);
      return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),new ArrayList<>());
       
    }

    @Override
    public UserDto getUser(String email) {
       UserEntity userEntity = userRepository.findUserByEmail(email);
         if(userEntity == null) throw new UsernameNotFoundException(email);
       UserDto returnValue = new UserDto();
       
       BeanUtils.copyProperties(userEntity, returnValue);
       return returnValue;
    }

    @Override
    public UserDto getUserById(String userId) {
        UserDto returnedValue = new UserDto();
       UserEntity userEntity = userRepository.findByUserId(userId);
       if(userEntity == null) throw new UsernameNotFoundException(userId);
               
               BeanUtils.copyProperties(userEntity, returnedValue);
               return returnedValue;
    }

    @Override
    public UserDto updateUser(String id, UserDto userDto) {
        UserDto returnedValue = new UserDto();
       UserEntity userEntity = userRepository.findByUserId(id);
        if(userEntity == null) throw new UsernameNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setTelephone(userDto.getTelephone());
        UserEntity updatedUserReturn = userRepository.save(userEntity);
        
        BeanUtils.copyProperties(updatedUserReturn, returnedValue);
        return returnedValue;
    }

    @Override
    public void deleteUser(String userId) {
       UserEntity userEntity = userRepository.findByUserId(userId);
       
        if(userEntity == null) throw new UsernameNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        userRepository.delete(userEntity);
    }

    @Override
    @Transactional
    public String ajouterProjectToUser(Long project_id, String user_id) {
     UserEntity userEntity = userRepository.findByUserId(user_id);
     ProjectEntity project = projectRepository.findProjectById(project_id);
     userEntity.addProject(project);
     
     userRepository.save(userEntity);
         
         return "cet utilisateur a ete ajoutes";
    }
    @Transactional
    @Override
    public List<ProjectDto> getProjectsByUser(String user_id) {
       UserEntity userEntity = userRepository.findByUserId(user_id);
    //   Long id = userEntity.getId();
        Set<ProjectEntity> projects=  userEntity.getProjects();
           List <ProjectDto> returnedValue = new ArrayList<>();    
     for(ProjectEntity projectentity : projects ) {
         
            ProjectDto projectdto = new ProjectDto();
            BeanUtils.copyProperties(projectentity, projectdto);
            UserEntity creator = projectentity.getCreator();
            
            projectdto.setCreator_id(creator.getUserId());
            returnedValue.add(projectdto);
     }
               return returnedValue;
    }
    @Transactional
    @Override
    public List<UserDto> getUsersNotInProject(Long project_id) {
         ProjectEntity projectEntity = projectRepository.findProjectById(project_id);
          Set<UserEntity> users=  projectEntity.getUsers();
             Collection <Long> userslong=  new ArrayList<Long>();
         for(UserEntity userentity : users ) {
         
           Long id =userentity.getId();
            userslong.add(id);
     }  
          
           List<UserEntity> usersNotIn = userRepository.findByIdNotIn(userslong);
          
              List <UserDto> returnedValue = new ArrayList<>();    
     for(UserEntity userentity : usersNotIn ) {
         
           UserDto userdto = new UserDto();
            BeanUtils.copyProperties(userentity, userdto);
          
            returnedValue.add(userdto);
     }
               return returnedValue;
    }
@Transactional
    @Override
    public List<UserDto> getUsersInProject(Long project_id) {
       ProjectEntity projectEntity = projectRepository.findProjectById(project_id);
          Set<UserEntity> users=  projectEntity.getUsers();
          
          
              List <UserDto> returnedValue = new ArrayList<>();    
     for(UserEntity userentity : users ) {
         
           UserDto userdto = new UserDto();
            BeanUtils.copyProperties(userentity, userdto);
          
            returnedValue.add(userdto);
     }
               return returnedValue;
    }

 /*   @Override
    public String storeFile(MultipartFile file, String firstName, String lastName, String email, String telephone, String password) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }

    }
     public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }*/
    @Transactional
    @Override
    public List<UserDto> getUsersNotInContact(String user_id) {
      UserEntity user  = userRepository.findByUserId(user_id);
           List <ListContactEntity>contacts = listContactRepository.fetchContacts(user.getId().toString());
   
                 
           
           
               List <UserEntity> returnedValue = new ArrayList<>();    
     for(ListContactEntity conentity : contacts ) {
         
          //  UserDto userdto = new UserDto();
            UserEntity userr =conentity.getUtilisateur_contact();
         
        //   BeanUtils.copyProperties(userr, userdto);
            
            returnedValue.add(userr);
     }
     
       Collection <Long> userslong=  new ArrayList<Long>();
         for(UserEntity userentity : returnedValue ) {
         
           Long id =userentity.getId();
            userslong.add(id);
     }  
              List <UserDto> returnedValue2 = new ArrayList<>();    
     if(!userslong.isEmpty()){
         
         //  BeanUtils.copyProperties(messages, returnedValue);
         
         List<UserEntity> usersNotIn = userRepository.findByIdNotIn(userslong);
         
         
         for(UserEntity userentity : usersNotIn ) {
                          UserDto userdto = new UserDto();
             BeanUtils.copyProperties(userentity, userdto);
             
             returnedValue2.add(userdto);
         }
     }else{
         
         
         List<UserEntity> usersNotIn =   userRepository.fetchall(user_id);
         
         
         
         for(UserEntity userentity : usersNotIn ) {
             
             UserDto userdto = new UserDto();
             BeanUtils.copyProperties(userentity, userdto);
             
             returnedValue2.add(userdto);
         }
     }
               return returnedValue2;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        UserDto returnedValue = new UserDto();
       UserEntity userEntity = userRepository.findUserByEmail(userDto.getEmail());
        if(userEntity == null) throw new UsernameNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setTelephone(userDto.getTelephone());
        UserEntity updatedUserReturn = userRepository.save(userEntity);
        BeanUtils.copyProperties(updatedUserReturn, returnedValue);
        return returnedValue;
    
    }

    @Override
    public UserDto updatePassword(UserDto userDto) {
     UserDto returnedValue = new UserDto();
       UserEntity userEntity = userRepository.findUserByEmail(userDto.getEmail().toString());
        if(userEntity == null) throw new UsernameNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
     //   userEntity.setFirstName(userDto.getFirstName());
       // userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
      //  userEntity.setEmail(userDto.getTelephone());
        UserEntity updatedUserReturn = userRepository.save(userEntity);
        BeanUtils.copyProperties(updatedUserReturn, returnedValue);
        return returnedValue;
    }

    @Override
    public UserDto updateImage(UserDto userDto) {
         UserDto returnedValue = new UserDto();
       UserEntity userEntity = userRepository.findUserByEmail(userDto.getEmail());
        if(userEntity == null) throw new UsernameNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
     //   userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword().toString()));
        userEntity.setTelephone(userDto.getTelephone());
        userEntity.setImage_profile(userDto.getImage_profile());
        UserEntity updatedUserReturn = userRepository.save(userEntity);
        BeanUtils.copyProperties(updatedUserReturn, returnedValue);
        return returnedValue;
    }
    @Transactional
    @Override
    public String deleteProjectToUser(Long project_id, String user_id) {
     UserEntity userEntity = userRepository.findByUserId(user_id);
     ProjectEntity project = projectRepository.findProjectById(project_id);
     userEntity.removeProject(project);
     
     userRepository.save(userEntity);
         
         return "cet utilisateur a ete supprimer";
    }
    
}

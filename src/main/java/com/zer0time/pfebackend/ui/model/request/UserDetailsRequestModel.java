/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.ui.model.request;

/**
 *
 * @author saad
 */
public class UserDetailsRequestModel {
    private String firstName; 
    private String lastName; 
    private String password ; 
    private String email ; 
    private String telephone; 
    private String image_profile;

    public UserDetailsRequestModel(String firstName, String lastName, String email, String telephone,String image_profile) {
     this.firstName=firstName;
      this.lastName=lastName;
      this.image_profile=image_profile;
      this.email=email;
      this.telephone=telephone;
    }

    public UserDetailsRequestModel(String firstName, String lastName, String email, String telephone) {
      this.firstName=firstName;
      this.lastName=lastName;
      this.email=email;
      this.telephone=telephone;
    }

    public UserDetailsRequestModel(String email, String password) {
      this.email=email;
      this.password=password;
    }

    public String getImage_profile() {
        return image_profile;
    }

    public void setImage_profile(String image_profile) {
        this.image_profile = image_profile;
    }

  
    public UserDetailsRequestModel(String firstName, String lastName, String password, String email, String telephone, String profile_image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.image_profile = profile_image;
    }
    

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}

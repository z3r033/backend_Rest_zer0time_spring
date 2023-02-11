/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.ui.model.response;

/**
 *
 * @author saad
 */
public class ProjectRest {
 private Long id;
     
    private String project_name; 
    
    private String creator_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    
    
    
    
}

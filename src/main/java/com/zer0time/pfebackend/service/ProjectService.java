/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.service;

import com.zer0time.pfebackend.shared.dto.ProjectDto;
import java.util.List;
/**
 *
 * @author saad
 */
public interface ProjectService {

    public ProjectDto ajouterProject(ProjectDto projectDto);
    public String ajouterUserToProject(String project_id , String user_id);
    public List<ProjectDto> getProjectsByUser(String creator_id);
    public List<ProjectDto> getProjectAll();

    public void deleteProject(Long id);
   public String deleteProjectToUser(Long project_id, Long tache_id);
    
}

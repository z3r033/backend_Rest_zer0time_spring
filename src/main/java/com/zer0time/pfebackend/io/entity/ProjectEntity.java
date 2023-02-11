/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.io.entity;

import java.awt.print.Book;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javassist.bytecode.Descriptor.Iterator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author saad
 */
@Entity(name="projects")
public class ProjectEntity  implements Serializable  {
      private static final Long serialVersionUID= 1L; 
      
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     
     
    @Column(nullable=false ,length = 50)
    private String project_name; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", referencedColumnName = "userId")
    private UserEntity creator;
    
    @ManyToMany( mappedBy = "projects")
    private Set<UserEntity> users = new HashSet<>();

    @Override
    public String toString() {
        return "ProjectEntity{" + "id=" + id + ", project_name=" + project_name + ", creator=" + creator + ", users=" + users + ", projecttaches=" + projecttaches + '}';
    }
    
    
      @OneToMany(cascade = CascadeType.ALL,
      mappedBy ="projecttache" , orphanRemoval = true)
      private List<TacheEntity> projecttaches = new ArrayList<>();
      
      
        public void addTache(TacheEntity tache) {
        this.projecttaches.add(tache);
        tache.setProjecttache(this);
    }

    public void removeTache(TacheEntity tache) {
        tache.setProjecttache(null);
        this.projecttaches.remove(tache);
    }

    public void removeTaches() {
       
        
          java.util.Iterator<TacheEntity> iterator = this.projecttaches.iterator();

        while (iterator.hasNext()) {
            TacheEntity tache = iterator.next();

            tache.setProjecttache(null);
            iterator.remove();
        }
    }

    public List<TacheEntity> getProjecttaches() {
        return projecttaches;
    }

    public void setProjecttaches(List<TacheEntity> projecttaches) {
        this.projecttaches = projecttaches;
    }
      
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

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
      
    
     @Override
    public boolean equals(Object obj) {
               
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        return id != null && id.equals(((ProjectEntity) obj).id);
    }

    @Override
    public int hashCode() {
        return 2021;
    }

}

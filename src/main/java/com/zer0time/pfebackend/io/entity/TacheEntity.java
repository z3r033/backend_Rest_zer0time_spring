/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.io.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author saad
 */
@Entity(name="taches")
public class TacheEntity  implements Serializable {
      private static final Long serialVersionUID= 1L; 
      
         @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     
         
  //   public static final String TABLE_NAME = "TachDoo";
    //    public static final String _ID = BaseColumns._ID;
    //    public static final String COLUMN_HEADING = "heading";
      //  public static final String COLUMN_MESSAGE = "message";
      //  public static final String COLUMN_DATE = "date";
      //  public static final String COLUMN_TIME = "time";
     //   public static final String COLUMN_NOTIFICATION = "notification";
      //  public static final String COLUMN_IMPO = "impo";
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", referencedColumnName = "userId")
    private UserEntity creatorTache; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private ProjectEntity projecttache; 

    public UserEntity getCreatorTache() {
        return creatorTache;
    }

    public void setCreatorTache(UserEntity creatorTache) {
        this.creatorTache = creatorTache;
    }

    public ProjectEntity getProjecttache() {
        return projecttache;
    }

    public void setProjecttache(ProjectEntity projecttache) {
        this.projecttache = projecttache;
    }
    
    
            
    @Column(nullable=false ,length = 50)
    private String heading; 
    @Column(nullable=false ,length = 200)
    private String message; 
    
    
    @Column(nullable=false ,length = 50)
    private String date; 
    
    
    @Column(nullable=false ,length = 50)
    private String time; 
    
 
    @Column(nullable=false ,length = 50)
    private String impo; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImpo() {
        return impo;
    }

    public void setImpo(String impo) {
        this.impo = impo;
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
        
        return id != null && id.equals(((TacheEntity) obj).id);
    }

    @Override
    public int hashCode() {
        return 2021;
    }
    
}

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
public class TacheRest {
        private Long id;
    private String creatortache_id; 
    private Long projecttache_id; 
    private String heading; 
    private String message; 
        private String date; 
    private String time; 
    private String impo;

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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatortache_id() {
        return creatortache_id;
    }

    public void setCreatortache_id(String creatortache_id) {
        this.creatortache_id = creatortache_id;
    }

    public Long getProjecttache_id() {
        return projecttache_id;
    }

    public void setProjecttache_id(Long projecttache_id) {
        this.projecttache_id = projecttache_id;
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

    
    
}

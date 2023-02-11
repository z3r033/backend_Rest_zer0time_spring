/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.ui.controller;

import com.zer0time.pfebackend.service.TacheService;
import com.zer0time.pfebackend.shared.dto.ProjectDto;
import com.zer0time.pfebackend.shared.dto.TacheDto;
import com.zer0time.pfebackend.ui.model.request.ProjectDetailsRequestModel;
import com.zer0time.pfebackend.ui.model.request.TacheRequestModel;
import com.zer0time.pfebackend.ui.model.response.ProjectRest;
import com.zer0time.pfebackend.ui.model.response.TacheRest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("taches")
public class TacheController {
       @Autowired
      TacheService tacheService ;
               
      
          @PostMapping
public TacheRest ajouterTache(@RequestBody TacheRequestModel tache) {
 
         TacheRest returnedValue = new TacheRest(); 

              TacheDto tacheDto = new TacheDto();
        BeanUtils.copyProperties(tache,tacheDto);
  
        TacheDto createdtache = tacheService.ajouterTache(tacheDto);
        BeanUtils.copyProperties(createdtache, returnedValue);
      
       return returnedValue; 
    
}

  @GetMapping
    public  List<TacheRest> getTacheById(@RequestParam(value="project_id") Long project_id  ) {
   
     List <TacheRest> returnedValue = new ArrayList<>();
     List<TacheDto> taches  =  tacheService.getTacheByProject(project_id);
     
     for(TacheDto tachedto : taches ) {
         
            TacheRest tacherest = new TacheRest();
            BeanUtils.copyProperties(tachedto, tacherest);
            returnedValue.add(tacherest);
     }
    return returnedValue; 
    }
    
}

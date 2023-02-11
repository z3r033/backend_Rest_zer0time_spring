/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend.service;

import com.zer0time.pfebackend.shared.dto.ProjectDto;
import com.zer0time.pfebackend.shared.dto.TacheDto;
import java.util.List;

/**
 *
 * @author saad
 */
public interface TacheService {

    public TacheDto ajouterTache(TacheDto tacheDto);
      public List<TacheDto> getTacheByProject(Long project_id);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.service;

import com.fpmislata.domain.Acto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author alumno
 */
@Local
public interface ActoServiceLocal {

    List listActos();

    void addActo(Acto acto);

    Acto findActoById(Acto acto);

    void updateActo(Acto acto);

    void deleteActo(Acto acto);
    
}

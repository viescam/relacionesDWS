/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.service;

import com.fpmislata.domain.Partitura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author alumno
 */
@Local
public interface PartituraServiceLocal {

    List listPartituras();

    void addPartitura(Partitura partitura);

    Partitura findPartituraById(Partitura partitura);

    void updatePartitura(Partitura partitura);

    void deletePartitura(Partitura partitura);
    
}

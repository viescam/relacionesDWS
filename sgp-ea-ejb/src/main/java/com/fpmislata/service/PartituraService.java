/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.service;

import com.fpmislata.domain.Partitura;
import com.fpmislata.repository.PartituraDaoLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author alumno
 */
@Stateless
public class PartituraService implements PartituraServiceLocal {

    @EJB
    private PartituraDaoLocal partituraDao;
    
    @Override
    public List listPartituras() {
        return partituraDao.listPartituras();
    }

    @Override
    public void addPartitura(Partitura partitura) {
        partituraDao.addPartitura(partitura);
    }

    @Override
    public Partitura findPartituraById(Partitura partitura) {
        return partituraDao.findPartituraById(partitura);
    }

    @Override
    public void updatePartitura(Partitura partitura) {
        partituraDao.updatePartitura(partitura);
    }

    @Override
    public void deletePartitura(Partitura partitura) {
        partituraDao.deletePartitura(partitura);
    }
    
    
    
}


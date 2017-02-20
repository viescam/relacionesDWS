/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.service;

import com.fpmislata.domain.Acto;
import com.fpmislata.repository.ActoDAOLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author alumno
 */
@Stateless
public class ActoService implements ActoServiceLocal {

    @EJB
    private ActoDAOLocal actoDAO;

    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List listActos() {
        return actoDAO.listActos();
    }

    @Override
    public void addActo(Acto acto) {
        actoDAO.addActo(acto);
    }

    @Override
    public Acto findActoById(Acto acto) {
        return actoDAO.findActoById(acto);
    }

    @Override
    public void updateActo(Acto acto) {
        actoDAO.updateActo(acto);
    }

    @Override
    public void deleteActo(Acto acto) {
        actoDAO.deleteActo(acto);
    }
    
    
    
    
}

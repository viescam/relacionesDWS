/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.repository;

import com.fpmislata.domain.Acto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alumno
 */
@Stateless
public class ActoDAO implements ActoDAOLocal {
    
    @PersistenceContext(unitName = "ProyectoPU")
    EntityManager em;
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List listActos() {
        return em.createNamedQuery("Acto.findAll").getResultList();
    }

    @Override
    public void addActo(Acto acto) {
        em.persist(acto);
    }

    @Override
    public Acto findActoById(Acto acto) {
        return em.find(Acto.class,acto.getId());
    }

    @Override
    public void updateActo(Acto acto) {
        em.merge(acto);
    }

    @Override
    public void deleteActo(Acto acto) {
        acto = findActoById(acto);
        em.remove(acto);
    }
    
    
    
    
}

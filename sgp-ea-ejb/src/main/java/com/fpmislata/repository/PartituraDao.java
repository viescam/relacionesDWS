/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.repository;

import com.fpmislata.domain.Partitura;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alumno
 */
@Stateless
public class PartituraDao implements PartituraDaoLocal {
    
    @PersistenceContext(unitName = "ProyectoPU")
    EntityManager em;

    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List listPartituras() {
        return em.createNamedQuery("Partitura.findAll").getResultList();
    }

    @Override
    public void addPartitura(Partitura partitura) {
        em.persist(partitura);
    }

    @Override
    public Partitura findPartituraById(Partitura partitura) {
        return em.find(Partitura.class, partitura.getId());
    }

    @Override
    public void updatePartitura(Partitura partitura) {
        em.merge(partitura);
    }

    @Override
    public void deletePartitura(Partitura partitura) {
        partitura = findPartituraById(partitura);
        em.remove(partitura);
    }
    
    
    
    
    
    
    
}

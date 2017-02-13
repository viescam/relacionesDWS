/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.repository;

import com.fpmislata.domain.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author borja
 */
@Stateless
public class PersonaDaoImp implements PersonaDaoImpLocal {

    @PersistenceContext(unitName = "ProyectoPU")
    EntityManager em;

    @Override
    public List listPersonas() {
        return em.createNamedQuery("Persona.findAll").getResultList();
    }

    @Override
    public void addPersona(Persona persona) {
        em.persist(persona);
    }

    @Override
    public void updatePersona(Persona persona) {
        em.merge(persona);
    }

    @Override
    public Persona findPersonaById(Persona persona) {
        return em.find(Persona.class, persona.getId());
    }

    @Override
    public Persona findPersonaByEmail(Persona persona) {
        Query query = em.createQuery("from Persona p where p.email = :email");
        query.setParameter("email", persona.getEmail());
        try {
            return (Persona) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } catch (NonUniqueResultException nur) {
            return (Persona) query.getResultList().get(0);
        }
    }

    @Override
    public void deletePersona(Persona persona) {
        persona = findPersonaById(persona);
        em.remove(persona);
    }

}

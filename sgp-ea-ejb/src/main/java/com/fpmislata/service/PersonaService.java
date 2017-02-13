/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.service;

import com.fpmislata.domain.Persona;
import com.fpmislata.repository.PersonaDaoImpLocal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class PersonaService implements PersonaServiceLocal {

    @EJB
    private PersonaDaoImpLocal personaDaoImp;

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")  



    @Override
    public void addPersona(Persona persona) {
        Persona p = personaDaoImp.findPersonaByEmail(persona);
        if ( p == null ){
            personaDaoImp.addPersona(persona);
        }
    }

    @Override
    public void updatePersona(Persona persona) {
        personaDaoImp.updatePersona(persona);
    }

    @Override
    public Persona findPersonaById(Persona persona) {

        return personaDaoImp.findPersonaById(persona);
    }

    @Override
    public void deletePersona(Persona persona) {
         personaDaoImp.deletePersona(persona);
    }

    @Override
    public List listPersonas() {
        return personaDaoImp.listPersonas();
    }
    
    
    
}

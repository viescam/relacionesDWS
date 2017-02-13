/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.repository;

import com.fpmislata.domain.Persona;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author borja
 */
@Local
public interface PersonaDaoImpLocal {

    List listPersonas();

    void addPersona(Persona persona);

    void updatePersona(Persona persona);

    Persona findPersonaById(Persona persona);

    Persona findPersonaByEmail(Persona persona);

    void deletePersona(Persona persona);

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.service;

import com.fpmislata.domain.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lodiade
 */
@Local
public interface PersonaServiceLocal {


    void addPersona(Persona persona);

    void updatePersona(Persona persona);

    Persona findPersonaById(Persona persona);

    void deletePersona(Persona persona);

    List listPersonas();
    
}

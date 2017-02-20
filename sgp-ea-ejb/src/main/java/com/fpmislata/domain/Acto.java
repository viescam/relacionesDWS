/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author alumno
 */

@Entity
@NamedQueries({@NamedQuery(name = "Acto.findAll",query="SELECT a FROM Acto a ORDER BY a.id")})
@Table(name = "actos")
public class Acto implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_acto")
    private int id;
    
    @Column(nullable = false, length = 45)
    private String tipo;
    
    @Column(nullable = false)
    private Float presupuesto;
    
    @Column(nullable = false, length = 100)
    private String lugar;
    
    @ManyToMany(cascade={CascadeType.ALL},mappedBy = "personas")
    private Set<Persona> personas;

    public Acto() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Float presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Set<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(Set<Persona> personas) {
        this.personas = personas;
    }
    
    
}

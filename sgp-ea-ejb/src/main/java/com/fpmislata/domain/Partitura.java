/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author alumno
 */
@Entity
@NamedQueries({@NamedQuery(name="Partitura.findAll",query = "SELECT p FROM Partitura p ORDER BY p.id")})
@Table(name = "partituras")
public class Partitura implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partitura")
    private int id;
    
    @Column(nullable = false, length = 45)
    private String tipo;
    
    @Column(nullable = false, length = 45)
    private String compositor;
     
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @ManyToOne
    @JoinColumn(name="persona")
    private Persona persona;

    public Partitura() {
    }

    public Partitura(String tipo, String compositor, String nombre) {
        this.tipo = tipo;
        this.compositor = compositor;
        this.nombre = nombre;
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

    public String getCompositor() {
        return compositor;
    }

    public void setCompositor(String compositor) {
        this.compositor = compositor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    
    
}

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
 * @author lodiade
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Persona.findAll", query= "SELECT p from Persona p ORDER BY p.id")})
@Table( name = "personas" )
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_persona")
    private int id;
    
    @Column(nullable=false, length = 45)
    private String nombre;
    
    @Column(length=45)
    private String email;
    
    @Column(length=20)
    private String telefono;   
    
    @OneToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Instrumento instrumento;
    
    
//    //RELACION 1 a 1 con tabla documentos
//    @OneToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
//    @PrimaryKeyJoinColumn
//    private Documento documento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Instrumento getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(Instrumento instrumento) {
        this.instrumento = instrumento;
    }
    
    

    public Persona() {
    }

    public Persona(int id, String nombre, String email, String telefono, Documento documento) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }    
    


}

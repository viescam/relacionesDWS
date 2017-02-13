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
@NamedQueries({@NamedQuery(name = "Instrumento.findAll",query="SELECT p FROM Instrumento p ORDER BY p.id")})
@Table(name = "instrumentos")
public class Instrumento implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instrumento")
    private int id;
    
    @Column(nullable = false, length = 45)
    private String nombre;
    
    @Column(nullable = false, length = 45)
    private String cuerda;
    
    @Column(nullable = false, length = 45)
    private String marca;
    
    @OneToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Persona persona;

    public Instrumento(String nombre, String cuerda, String marca) {
        this.nombre = nombre;
        this.cuerda = cuerda;
        this.marca = marca;
    }

    public Instrumento() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuerda() {
        return cuerda;
    }

    public void setCuerda(String cuerda) {
        this.cuerda = cuerda;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    

    
    
    
    
    
    
}

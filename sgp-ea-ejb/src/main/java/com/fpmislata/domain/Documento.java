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
 * @author borja
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Documento.findAll", query = "SELECT p FROM Documento p "
            + "ORDER BY p.id")})
@Table(name = "documentos")
public class Documento implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private int id;
    
    @Column(nullable = false, length = 9 )
    private String dni;

    public Documento() {
    }

    public Documento(String dni) {
        this.dni = dni;
    }

    public Documento(int id, String dni) {
        this.id = id;
        this.dni = dni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Documento[ id="+id+" , dni= "+ dni +"]"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) obj;
        if (this.id != other.id){
            return false;
        }
        return true;
        
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }
    
    
}

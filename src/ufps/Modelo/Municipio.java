/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.Modelo;

import ufps.util.colecciones_seed.ColaP;
import ufps.util.colecciones_seed.ListaCD;

/**
 *
 * @author MADARME
 */
public class Municipio {
    
    private int codigo;
    private String nombre;
    ColaP<Persona> personas=new ColaP();
 

    public Municipio() {
    }

    public Municipio(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ColaP<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(ColaP<Persona> personas) {
        this.personas = personas;
    }

    

    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Municipio other = (Municipio) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Municipio{" + "codigo=" + codigo + ", nombre=" + nombre + ", personas=" + personas.toString() + '}';
    }
    
    
    
    
    
    
    
}

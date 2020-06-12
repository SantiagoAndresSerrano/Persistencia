/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.Modelo;

import ufps.util.colecciones_seed.ListaCD;

/**
 *
 * @author MADARME
 */
public class Departamento {
    
    private int codigo;
    private String nombre;
    ListaCD<Municipio> municipios=new ListaCD();

    public Departamento() {
    }

    public Departamento(int codigo, String nombre) {
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

    public ListaCD<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(ListaCD<Municipio> municipios) {
        this.municipios = municipios;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.codigo;
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
        final Departamento other = (Departamento) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Departamento{" + "codigo=" + codigo + ", nombre=" + nombre + ", municipios=\n" + municipios.toString() + '}';
    }
    
    
    
}

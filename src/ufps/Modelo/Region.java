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
public class Region {
    
    private int codigo; //Se va a generar
    private String nombre;
    private ListaCD<Departamento> dptos=new ListaCD();

    public Region() {
    }

    public Region(int codigo, String nombre) {
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

    public ListaCD<Departamento> getDptos() {
        return dptos;
    }

    public void setDptos(ListaCD<Departamento> dptos) {
        this.dptos = dptos;
    }

    @Override
    public String toString() {
        return "Region{" + "codigo=" + codigo + ", nombre=" + nombre + ", dptos=\n" + dptos.toString() + '}';
    }
    
    
    public Departamento buscarDpto(Departamento d)
    {
        //Reduncia:
        //Por definición:
        if(this.dptos.esVacia())
            return null;
    
        for(Departamento dpto:this.dptos)
        {
            if(dpto.equals(d))
                return dpto;
        }
        return null;
    }
    
}

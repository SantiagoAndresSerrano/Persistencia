/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.Modelo;

import java.util.Objects;
import ufps.util.colecciones_seed.ListaCD;

/**
 *
 * @author MADARME
 */
public class Region {

    private int codigo; //Se va a generar
    private String nombre;
    private ListaCD<Departamento> dptos = new ListaCD();
    private int cantidadBeneficiarios = 0;
    private int subsidiosActuales = 0;

    public Region() {
    }

    public Region(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCantidadBeneficiarios() {
        return cantidadBeneficiarios;
    }

    public void setCantidadBeneficiarios(int cantidadBeneficiarios) {
        this.cantidadBeneficiarios = cantidadBeneficiarios;
    }

    public int getSubsidiosActuales() {
        return subsidiosActuales;
    }

    public void setSubsidiosActuales(int subsidiosActuales) {
        this.subsidiosActuales = subsidiosActuales;
    }

    public int cantidadBeneficiados() {
        
        int cantidad = 0;
        for (Departamento d : dptos) {
            
             int de=d.cantidadBenefiados();
             
             System.out.println("DEPARTAMENTO "+d.getNombre()+" SUBSIDIOS RECIBIDOS "+de);
            
            cantidad += de;
        }
        return cantidad;
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
        return "Region{" + ", nombre=" + nombre;//", dptos=\n" + dptos.toString() + '}';
    }

    public Departamento buscarDpto(Departamento d) {
        //Reduncia:
        //Por definición:
        if (this.dptos.esVacia()) {
            return null;
        }

        for (Departamento dpto : this.dptos) {
            if (dpto.equals(d)) {
                return dpto;
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Region other = (Region) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

}

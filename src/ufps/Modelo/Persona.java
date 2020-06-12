/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.Modelo;

import java.util.Date;

/**
 * Habitante
 * @author MADARME
 */
public class Persona {
    
    private long cedula;
    private Date fechaNacimiento;
    private String nombres;
    private String email;
    private String direccion;
    private boolean genero;

    public Persona(long cedula, Date fechaNacimiento, String nombres, String email, String direccion, boolean genero) {
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.nombres = nombres;
        this.email = email;
        this.direccion = direccion;
        this.genero = genero;
    }

    public Persona() {
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Persona{" + "cedula=" + cedula + ", fechaNacimiento=" + fechaNacimiento + ", nombres=" + nombres + ", email=" + email + ", direccion=" + direccion + ", genero=" + genero + '}';
    }
    
    
    
    
    
    
    
}

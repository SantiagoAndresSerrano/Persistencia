/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.Modelo;

import java.time.LocalDate;
import java.util.Date;

/**
 *  Para manejar las fechas: https://devs4j.com/2018/10/30/java-8-manejo-de-fechas-y-tiempo-localdate-localtime-y-localdatetime/
 * @author MADARME
 */
public class Persona {
    
    private long cedula;
    private LocalDate fechaNacimiento;
    private String nombres;
    private String email;
    private String direccion;
    private boolean genero;

    public Persona() {
    }

    public Persona(long cedula, LocalDate fechaNacimiento, String nombres, String email, String direccion, boolean genero) {
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.nombres = nombres;
        this.email = email;
        this.direccion = direccion;
        this.genero = genero;
    }

    
    
    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
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
    
    

    
    
    
    
    
    
    
    
}

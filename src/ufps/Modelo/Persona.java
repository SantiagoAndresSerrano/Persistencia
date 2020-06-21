/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.Modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

/**
 *  Para manejar las fechas: https://devs4j.com/2018/10/30/java-8-manejo-de-fechas-y-tiempo-localdate-localtime-y-localdatetime/
 * @author MADARME
 */
public class Persona {
    
    private Long cedula;
    private LocalDate fechaNacimiento;
    private String nombres;
    private String email;
    private String direccion;
    private boolean genero;
    private boolean siEsBeneficiario=false;

    public Persona() {
    }

    public Persona(Long cedula, LocalDate fechaNacimiento, String nombres, String email, String direccion, boolean genero) {
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

    public void setCedula(Long cedula) {
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

    public boolean isSiEsBeneficiario() {
        return siEsBeneficiario;
    }

    public void setSiEsBeneficiario(boolean siEsBeneficiario) {
        this.siEsBeneficiario = siEsBeneficiario;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (int) (this.cedula ^ (this.cedula >>> 32));
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
        final Persona other = (Persona) obj;
        return Objects.equals(this.cedula, other.cedula);
    }
    
    
    public int getEdad()
    {
        
         LocalDate hoy=LocalDate.now();
         return (int)(ChronoUnit.YEARS.between(this.fechaNacimiento, hoy)); 
        
    }

    @Override
    public String toString() {
        return "" + "fechaNacimiento=" + fechaNacimiento + ", nombres=" + nombres + ", siEsBeneficiario=" + siEsBeneficiario +"";
    }
    
    
    
    
    
    
    
    
    
    
}

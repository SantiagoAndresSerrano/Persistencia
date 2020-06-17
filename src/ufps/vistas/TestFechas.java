/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.vistas;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * Para manejar las fechas: https://devs4j.com/2018/10/30/java-8-manejo-de-fechas-y-tiempo-localdate-localtime-y-localdatetime/
 * @author MADARME
 */
public class TestFechas {
    
    
    public static void main(String[] args) {
        LocalDate hoy=LocalDate.now();
        System.out.println(hoy.toString());
        LocalDate cumple=LocalDate.parse("1980-03-05");
        System.out.println(cumple.toString());
        
        long edad=ChronoUnit.YEARS.between(cumple, hoy); 
        System.out.println("Su edad es:"+edad);
        
        Period periodo = Period.between(cumple, hoy);
        System.out.printf("Tu edad es: %s años, %s meses y %s días",
                    periodo.getYears(), periodo.getMonths(), periodo.getDays());
        
        LocalDate cumple2=LocalDate.parse("1980-03-06");
        //Util para la prioridad de edades:
        int comparador=cumple.compareTo(cumple2);
        System.out.println("\n Cumple1:"+cumple.toString());
        System.out.println("\n Cumple2:"+cumple2.toString());
        System.out.println("\ncumple - cumple2:"+comparador);
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.vistas;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import ufps.Negocio.Dane;

/**
 *
 * @author santi
 */
public class TestPersonas {

    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws ParseException {

        try {
            String urlRegiones = "http://ufps30.madarme.co/dptoDane.csv";
            String urlSubsidios = "http://ufps30.madarme.co/persistencia/subsidioregion.txt";
            String urlPersonas = "http://ufps30.madarme.co/persistencia/dane_personas.txt";
            String urlPersona ="https://drive.google.com/file/d/1iq5UJ-ZiB9CYhB4co-qGRrbN7kyzVSXG/view?usp=sharing";
            Dane dane = new Dane(urlRegiones);

            dane.cargarSubsidioRegion(urlSubsidios);
            dane.cargarPersonas(urlPersonas);
           
            System.out.println("===================SUBSIDIOS==========================");
            System.out.println(dane.procesarSubsidios());
  
//            System.out.println("=========PERSONAS SIN SUBSIDIO===============");             
//            System.out.println(dane.getPersonasNoSubsidio().toString());
//            System.out.println("=============================================");
//            
//            System.out.println("SUBSIDIOS TOTALES= "+dane.getCantidadSubsidioDepartamentos());
//            System.out.println("=============================================");
//            System.out.println("BUSCAR PERSONA:");
//            System.out.println(dane.getDatosPersona(4068079));
//            System.out.println("=============================================");
//            System.out.println("Subsidios De cada Region: "+dane.getSubisidiosRegion().toString());
//            
//             
//            System.out.println("*******************************************");
//            System.out.println(dane.getCantidadSubsidioMunicipio(68001)+" subsidios");
//            System.out.println("Existe codigo: "+dane.existeCodigoMunicipio(54001));
             System.out.println("PERSONAS CON DATOS ERRONEOS");
            System.out.println(dane.getDatosErroneos(urlPersona).toString());
            
            //68001  54001
        } catch (ParseException e) {

        }
    }

}

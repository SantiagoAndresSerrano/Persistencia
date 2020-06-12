/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.vistas;

import ufps.Ejercicio.AnalizadorExpresiones;

/**
 *
 * @author MADARME
 */
public class Test_AnalizadorExpresion {
    
    public static void main(String[] args) {
        
        String cadena="XyZyzyx";
        AnalizadorExpresiones analiza=new AnalizadorExpresiones(cadena.toLowerCase());
        System.out.println(analiza.toString());
        System.out.println("Es palindroma:"+analiza.esPalindrome());
        
        String cadena2="(a(b))(ccc(d))";
        AnalizadorExpresiones analiza2=new AnalizadorExpresiones(cadena2.toLowerCase());
        System.out.println(analiza2.toString());
        System.out.println("Comprobar agrupamiento:"+analiza2.comprobarSignosAgrupamiento());
        
    }
    
}

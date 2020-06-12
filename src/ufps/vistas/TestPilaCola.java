/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.vistas;

import ufps.util.colecciones_seed.Cola;
import ufps.util.colecciones_seed.Pila;

/**
 *
 * @author MADARME
 */
public class TestPilaCola {
    public static void main(String[] args) {
        Pila<String> cadena1=new Pila();
        Cola<String> cadena2=new Cola();
        
        cadena1.apilar("U");
        cadena1.apilar("F");
        cadena1.apilar("P");
        cadena1.apilar("S");
        
        //Recorrer una pila:
        while(!cadena1.esVacia())
        {
            String dato=cadena1.desapilar();
            System.out.println(dato);
        }
        
        System.out.println("Cantidad de elementos:"+cadena1.getTamanio());
        
        Pila<Integer> p=new Pila<Integer>();
        for(int i=1;i<10;i++)
            p.apilar(i);
        
        while(!p.esVacia())
        {
            int dato=p.desapilar();
            System.out.println(dato);
        }
        
        System.out.println("Cantidad de elementos Enteros:"+p.getTamanio());
        
      
        
        Cola<Integer> c=new Cola<Integer>();
        for(int i=1;i<10;i++)
            c.enColar(i);
        
        System.out.println("Cantidad de elementos Enteros Cola antes de su proceso:"+c.getTamanio());
        while(!c.esVacia())
        {
            int dato=c.deColar();
            System.out.println(dato);
        }
        
        System.out.println("Cantidad de elementos Enteros después Cola:"+c.getTamanio());
        
        
    }
    
}

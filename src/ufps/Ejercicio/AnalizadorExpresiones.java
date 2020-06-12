/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.Ejercicio;

import ufps.util.colecciones_seed.Cola;
import ufps.util.colecciones_seed.ListaCD;
import ufps.util.colecciones_seed.Pila;

/**
 *
 * @author MADARME
 */
public class AnalizadorExpresiones {
    
    private ListaCD<Character> cadena=new ListaCD<Character>();

    public AnalizadorExpresiones() {
    }
    
    
    public AnalizadorExpresiones(String cadena) {
        
     for(int i=0;i<cadena.length();i++)
         this.cadena.insertarAlFinal(cadena.charAt(i));
        
    }

    /**
     *  Averigua si una cadena es palindroma: Un palíndromo es una palabra que se escribe 
     * igual al derecho y al revés. ... 
     * Podemos pensar en un palíndromo como cualquier 
     * secuencia de letras que se lee igual hacia adelante y 
     * hacia atrás, como xyzyzyx. Su proceso lo realiza usando pilas y colas
     * @return 
     */
    public boolean esPalindrome()
    {
    // :(
        Cola<Character> c=new Cola();
        Pila<Character> p=new Pila();
        
        for(Character letra:this.cadena)
        {
                c.enColar(letra);
                p.apilar(letra);
        }
        
        return this.sonIguales(c, p);
    }
    
    
    /**
     * Método para comprobar una cola y pila tienen elementos iguales
     * @param c es una cola
     * @param p pila
     * @return true si son iguales o false en caso contrario
     */
    private boolean sonIguales(Cola<Character> c, Pila<Character> p)
    {
    if(c.getTamanio()!=p.getTamanio())
        return false;
        
    //acá los tamaños son iguales
    while(!c.esVacia())
    {
        char c1=c.deColar();
        char c2=p.desapilar();
        if(c1!=c2)
            return false;
    }
    return true;
    }
    
    
    
    /**
     * Comprueba si una expresión tiene parentesis balanceados
     * Usando pilas y colas
     * Ejemplo: (a+b(ab) --> false
     * Ejemplo: (a+b(ab)) --> true
     * @return true si tiene parantesis balanceados o false en caso contrario
     */
    public boolean comprobarSignosAgrupamiento()
    {
        return false;
    }
    
    
    @Override
    public String toString() {
        //Invocando el método toString() de la clase ListaCD
        return this.cadena.toString();
    }
    
    
    
    
    
}

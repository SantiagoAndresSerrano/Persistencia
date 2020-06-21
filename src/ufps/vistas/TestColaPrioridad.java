/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.vistas;

import ufps.util.colecciones_seed.ColaP;

/**
 *
 * @author MADARME
 */
public class TestColaPrioridad {
    
    public static void main(String[] args) {
        
        ColaP<String> nombres=new ColaP();
        
        nombres.enColar("madarme", 0);
        nombres.enColar("Juan", 10);
        nombres.enColar("Javier", 5);
        nombres.enColar("Genesis", 3);
        nombres.enColar("Cristian",0);
        
        while(!nombres.esVacia())
            System.out.println(nombres.deColar());
        /*
            Juan --> 10
            Cristian-->10
            Javier -->5
            Genesis-->3
            madarme-->0
        */
        
        
    }
    
}

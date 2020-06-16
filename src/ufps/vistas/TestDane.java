/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.vistas;

import ufps.Negocio.Dane;

/**
 *
 * @author MADARME
 */
public class TestDane {
    public static void main(String[] args) {
        String url="http://ufps30.madarme.co/dptoDane.csv";
        Dane dane=new Dane(url);
        //System.out.println(dane.toString());
        System.out.println(dane.getListadoRegiones());
        
    }
    
}

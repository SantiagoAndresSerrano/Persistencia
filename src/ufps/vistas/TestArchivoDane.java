/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.vistas;

import ufps.util.colecciones_seed.ListaCD;
import ufps.util.varios.ArchivoLeerTexto;
import ufps.util.varios.ArchivoLeerURL;

/**
 *
 * @author MADARME
 */
public class TestArchivoDane {
    
    public static void main(String[] args) {
       String url="http://ufps30.madarme.co/dptoDane.csv";
        ArchivoLeerURL file=new ArchivoLeerURL(url);
        Object v[]=file.leerArchivo();
        
        for(Object datos:v)
        {
           
            System.out.println(datos.toString());
        }  
           // for(String datos:l)
              //  System.out.println(datos);
            
    }
    
    
}

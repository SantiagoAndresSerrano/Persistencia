/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.Negocio;

import ufps.Modelo.Region;
import ufps.util.colecciones_seed.ListaCD;
import ufps.util.varios.ArchivoLeerTexto;
import ufps.Modelo.*;
import ufps.util.colecciones_seed.Pila;
import ufps.util.varios.ArchivoLeerURL;

/**
 *
 * @author MADARME
 */
public class Dane {
    
    private ListaCD<Region> regiones=new ListaCD();

    public Dane() {
    }

    public Dane(String url)
    {
    this.leerUrLDane(url);
    }
    
    
    public ListaCD<Region> getRegiones() {
        return regiones;
    }

    public void setRegiones(ListaCD<Region> regiones) {
        this.regiones = regiones;
    }
    
    
   
    /*
    
REGION,CÓDIGO DANE DEL DEPARTAMENTO,DEPARTAMENTO,CÓDIGO DANE DEL MUNICIPIO,MUNICIPIO
Región Eje Cafetero - Antioquia,5,Antioquia,5001,Medellín
    
    */
    
    
    private void leerUrLDane(String url)
    {

        ArchivoLeerURL file=new ArchivoLeerURL(url);
        Object v[]=file.leerArchivo();
        int codigoRegion=1;
        for(int i=1;i<v.length;i++)
        {
        
        String registro=v[i].toString();
        //Región Eje Cafetero , 5,Antioquia,5001,Medellín
        String datoRegion[]=registro.split(",");
        /*
            datoRegion[0]=Región Eje Cafetero
            datoRegion[1]=5
            datoRegion[2]= Antioquia
            datoRegion[3]=5001
            datoRegion[4]=Medellín
            
        */
        
        Region nuevaRegion=new Region(codigoRegion,datoRegion[0]);
        Region buscar=this.buscarRegion(nuevaRegion);
        if(buscar==null)
        {
            this.regiones.insertarAlFinal(nuevaRegion);
            codigoRegion++;
            buscar=nuevaRegion;
        }
        this.crearDepartamento(buscar, datoRegion);
        
        
    }
    }
    
    
    private void crearDepartamento(Region r, String datoRegion[])
    {
    /*
            datoRegion[0]=Región Eje Cafetero
            datoRegion[1]=5
            datoRegion[2]= Antioquia
            datoRegion[3]=5001
            datoRegion[4]=Medellín
            
        */
        
        Departamento nuevo=new Departamento(Integer.parseInt(datoRegion[1]),datoRegion[2]);
        Departamento buscado=r.buscarDpto(nuevo);
        if(buscado==null)
        {
            r.getDptos().insertarAlFinal(nuevo);
            buscado=nuevo;
        }
        
        this.crearMunicipio(buscado, datoRegion);
        
    
    }
    
    
    
    
    private void crearMunicipio(Departamento d, String datoRegion[])
    {
    
        /*
            datoRegion[0]=Región Eje Cafetero
            datoRegion[1]=5
            datoRegion[2]= Antioquia
            datoRegion[3]=5001
            datoRegion[4]=Medellín
            
        */
    Municipio nuevo=new Municipio(Integer.parseInt(datoRegion[3]),datoRegion[4]);
    d.getMunicipios().insertarAlFinal(nuevo);
    }
    
    private Region buscarRegion(Region nueva)
    {
    if(this.regiones.esVacia())
        return null;
    for(Region r:this.regiones)
    {
        if(r.equals(nueva))
            return r;
    }
    return null;
    }

    @Override
    public String toString() {
        String msg="";
        for(Region r:this.regiones)
        {
            msg+="\n***********************************************************************\n";
            msg+="Region:"+r.getNombre()+": Código de región:"+r.getCodigo()+"\n";
            for(Departamento d:r.getDptos())
            {
                msg+="Departamento:"+d.getNombre()+" Código dpto:"+d.getCodigo()+"\n";
                
                for(Municipio m:d.getMunicipios())
                {
                    msg+="Municipio:"+m.getNombre()+"\n";
                    
                    //Cuando este proceso se realice , se borra la cola de personas
                    //::::::: ADVERTENCIA  ::::::::::::
                    while(!m.getPersonas().esVacia())
                    {
                    msg+="\n Persona:"+m.getPersonas().deColar().toString();
                    }
                }
                
            }
        }
        return msg;
    }
    
    
    
    public String getListadoRegiones()
    {
    String msg="";
    for(Region r:this.regiones)
        {
            
            msg+="Region:"+r.getNombre()+": Código de región:"+r.getCodigo()+" , Cantidad de subsidios:"+r.getCantidadBeneficiarios()+"\n";
    
        }
    return msg;
    
    }
    
    
    
    //Punto 2. 
    public void cargarSubsidioRegion(String url)
    {
       
        ArchivoLeerURL file=new ArchivoLeerURL(url);
        Object v[]=file.leerArchivo();
        
        for(int i=1;i<v.length;i++)
        {
        
        String registro=v[i].toString();
        /**
         * codigo_region;cantidad de subsidios
            1;3
            2;3
            3;2
            4;3
            5;3
            6;4
         */
        String datoRegion[]=registro.split(";");
        int codRegion=Integer.parseInt(datoRegion[0]);
        int canSub=Integer.parseInt(datoRegion[1]);
        //Suponemos que el archivo NO CONTIENE DATOS ERRADOS
        this.regiones.get(codRegion-1).setCantidadBeneficiarios(canSub);
        
        
        }
    }
    
    //Punto 3.
    public void cargarPersonas(String url)
    {
    
    }
    
    //Punto 4.a
    public int procesarSubsidios()
    {
        // :)
        return 0;
    }
    
    //Punto 4.b
    public int getCantidadSubsidioDepartamentos()
    {
        // :)
        return 0;
    }
    
    //Punto 4.c
    
    public ListaCD<Persona> getPersonasNoSubsidio()
    {
    
        // :)
        return null;
    }
    
    
    //Punto 4.d
    
    public Pila<Region> getSubisidiosRegion()
    {
        // :)
        return null;
    }
    
    
    //Punto 4.e
    public String getDatosPersona(long cedula)
    {
        // :)
        return "";
    }
    
    
    // Punto 4.f
    
    public int getCantidadSubsidioMunicipio(int codMunicipio)
    {
        // :)
        return 0;
    }
    
    //Punto 4.g
    public ListaCD<Persona> getDatosErroneos()
    {
        // :)
        return null;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.Negocio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import ufps.Modelo.Region;
import ufps.util.colecciones_seed.ListaCD;
import ufps.util.varios.ArchivoLeerTexto;
import ufps.Modelo.*;
import ufps.util.colecciones_seed.ColaP;
import ufps.util.colecciones_seed.Pila;
import ufps.util.varios.ArchivoLeerURL;

/**
 *
 * @author MADARME
 */
public class Dane {

    private ListaCD<Region> regiones = new ListaCD();

    public Dane() {
    }

    public Dane(String url) {
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
    private void leerUrLDane(String url) {

        ArchivoLeerURL file = new ArchivoLeerURL(url);
        Object v[] = file.leerArchivo();
        int codigoRegion = 1;
        for (int i = 1; i < v.length; i++) {

            String registro = v[i].toString();
            //Región Eje Cafetero , 5,Antioquia,5001,Medellín
            String datoRegion[] = registro.split(",");
            /*
            datoRegion[0]=Región Eje Cafetero
            datoRegion[1]=5
            datoRegion[2]= Antioquia
            datoRegion[3]=5001
            datoRegion[4]=Medellín
            
             */

            Region nuevaRegion = new Region(codigoRegion, datoRegion[0]);
            Region buscar = this.buscarRegion(nuevaRegion);
            if (buscar == null) {
                this.regiones.insertarAlFinal(nuevaRegion);
                codigoRegion++;
                buscar = nuevaRegion;
            }
            this.crearDepartamento(buscar, datoRegion);

        }
    }

    private void crearDepartamento(Region r, String datoRegion[]) {
        /*
            datoRegion[0]=Región Eje Cafetero
            datoRegion[1]=5
            datoRegion[2]= Antioquia
            datoRegion[3]=5001
            datoRegion[4]=Medellín
            
         */

        Departamento nuevo = new Departamento(Integer.parseInt(datoRegion[1]), datoRegion[2]);
        Departamento buscado = r.buscarDpto(nuevo);
        if (buscado == null) {
            r.getDptos().insertarAlFinal(nuevo);
            buscado = nuevo;
        }

        this.crearMunicipio(buscado, datoRegion);

    }

    private void crearMunicipio(Departamento d, String datoRegion[]) {

        /*
            datoRegion[0]=Región Eje Cafetero
            datoRegion[1]=5
            datoRegion[2]= Antioquia
            datoRegion[3]=5001
            datoRegion[4]=Medellín
            
         */
        Municipio nuevo = new Municipio(Integer.parseInt(datoRegion[3]), datoRegion[4]);
        d.getMunicipios().insertarAlFinal(nuevo);
    }

    private Region buscarRegion(Region nueva) {
        if (this.regiones.esVacia()) {
            return null;
        }
        for (Region r : this.regiones) {
            if (r.equals(nueva)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String msg = "";
        for (Region r : this.regiones) {
            msg += "\n***********************************************************************\n";
            msg += "Region:" + r.getNombre() + ": Código de región:" + r.getCodigo() + "\n";
            for (Departamento d : r.getDptos()) {
                msg += "Departamento:" + d.getNombre() + " Código dpto:" + d.getCodigo() + "\n";

                for (Municipio m : d.getMunicipios()) {
                    msg += "Municipio:" + m.getNombre() + "\n";

                    //Cuando este proceso se realice , se borra la cola de personas
                    //::::::: ADVERTENCIA  ::::::::::::
                    while (!m.getPersonas().esVacia()) {
                        msg += "\n Persona:" + m.getPersonas().deColar().getFechaNacimiento();
                    }
                }

            }
        }
        return msg;
    }

    public String getListadoRegiones() {
        String msg = "";
        for (Region r : this.regiones) {

            msg += "Region:" + r.getNombre() + ": Código de región:" + r.getCodigo() + " , Cantidad de subsidios:" + r.getCantidadBeneficiarios() + "\n";

        }
        return msg;

    }

    //Punto 2. 
    public void cargarSubsidioRegion(String url) {

        ArchivoLeerURL file = new ArchivoLeerURL(url);
        Object v[] = file.leerArchivo();

        for (int i = 1; i < v.length; i++) {

            String registro = v[i].toString();
            /**
             * codigo_region;cantidad de subsidios 1;3 2;3 3;2 4;3 5;3 6;4
             */
            String datoRegion[] = registro.split(";");
            int codRegion = Integer.parseInt(datoRegion[0]);
            int canSub = Integer.parseInt(datoRegion[1]);
            //Suponemos que el archivo NO CONTIENE DATOS ERRADOS
            this.regiones.get(codRegion - 1).setCantidadBeneficiarios(canSub);

        }
    }

    //Punto 3.
    public void cargarPersonas(String url) throws ParseException {

        ArchivoLeerURL persona = new ArchivoLeerURL(url);
        Object[] personas = persona.leerArchivo();
        int contador = 0;
        for (Object p : personas) {

            if (contador > 0) {

                String dato = p.toString();
                String[] datos = dato.split(";");

                Persona pe = cargarDatosPersona(datos);
                String codigoMunicipio = datos[6];

                buscarCodMunicipio(codigoMunicipio, pe);

            }
            contador++;

        }
    }

    public void buscarCodMunicipio(String codigo, Persona per) {
        for (Region r : regiones) {
            recorrerRegiones(r, per, codigo);
        }

    }

    //Punto 4.a
    public int procesarSubsidios() {

        int cantidadS = 0;

        for (Region r : regiones) {
            cantidadS += recorrerRegionesSub(r);
        }

        // :)
        return cantidadS;
    }

    //Punto 4.b
    public int getCantidadSubsidioDepartamentos() {
        // :)
        int subsidios = 0;
        for (Region r : regiones) {

            subsidios += r.cantidadBeneficiados();
        }

        return subsidios;
    }

    //Punto 4.c
    public ListaCD<Persona> getPersonasNoSubsidio() {
        ListaCD<Persona> persona = new ListaCD<>();

        for (Region r : regiones) {
            persona.concatenarListaCD(buscarPersonas(r));
        }

        // :)
        return persona;
    }

    //Punto 4.d
    public Pila<Region> getSubisidiosRegion() {
        // :)

        ColaP<Region> region = new ColaP<>();
        for (Region r : regiones) {
            int auxSubsidios = subsidiosRecibidos(r);
            System.out.println("Region: " + r.getNombre() + " Tiene: " + auxSubsidios + " subsidios");
            region.enColar(r, -1 * auxSubsidios);

        }

        return llenarPila(region);
    }

    //Punto 4.e
    public String getDatosPersona(long cedula) {
        // :)
        String msg = "";
        for (Region r : regiones) {
            msg = buscarPersona(cedula, r.getDptos(), r);
        }

        return msg;
    }

    // Punto 4.f
    public int getCantidadSubsidioMunicipio(int codMunicipio) {

        int cantidadSubsidios = 0;
        for (Region r : regiones) {
            cantidadSubsidios += buscarSubsidios(r.getDptos(), codMunicipio);
        }

        // :)
        return cantidadSubsidios;
    }

    //Punto 4.g
    public ListaCD<Persona> getDatosErroneos(String url) {
        // :)

        ArchivoLeerURL a = new ArchivoLeerURL(url);
        Object v[] = a.leerArchivo();

        ListaCD<Persona> personasDatosE = new ListaCD<>();

        for (int i = 1; i < v.length; i++) {

            String[] datosPersona = v[i].toString().split(";");
            //cedula;fecha;nombre;email;direccion;genero(0=mujer, 1=hombre);codigo_municipio
            // 0    ;  1  ; 2    ;3    ;   4     ;            5            ;        6          
            Persona personaIesima = tieneDatosErrados(datosPersona);
            if (personaIesima!=null) {
                
                personasDatosE.insertarAlFinal(personaIesima);
                
            }
        }

        return personasDatosE;
    }

    public Persona tieneDatosErrados(String[] datosPersona) {
        String cedulaString  = datosPersona[0];
        long cedula = Long.parseLong(datosPersona[0]);
        LocalDate fecha = convertirFecha(datosPersona[1]);
        String nombre = datosPersona[2];
        String email = datosPersona[3];
        String direccion = datosPersona[4];
        Boolean genero = this.calcularGenero(Integer.parseInt(datosPersona[5]));
        String codigoM = datosPersona[6];

        //    public Persona(Long cedula, LocalDate fechaNacimiento, String nombres, String email, String direccion, boolean genero) {
        Persona p = new Persona(cedula,fecha,nombre,email,direccion,genero);
        int gen = Integer.valueOf(datosPersona[5]);
        int codigoMunicipio = Integer.valueOf(codigoM);
        
        if(p.getEdad()>120 || p.getEdad()<=0 ||cedulaString.length() != 7 || nombre.length() == 0 || (gen != 0 && gen != 1) || this.existeCodigoMunicipio(codigoMunicipio))
        {
            return p;
        }
        return null;
             
    }

    private boolean calcularGenero(int parseInt) {

        return parseInt != 0;
    }

    public Boolean existeCodigoMunicipio(int codigo) {

        for (Region r : regiones) {

            for (Departamento d : r.getDptos()) {

                if (d.existeCodigo(codigo)) {
                    return true;
                }

            }

        }

        return false;
    }

    private Persona cargarDatosPersona(String[] datos) {

        long cedula = Long.parseLong(datos[0]);
        LocalDate fecha = convertirFecha(datos[1]);
        String nombre = datos[2];
        String correo = datos[3];
        String direccion = datos[4];
        boolean genero = calcularGenero(Integer.parseInt(datos[5]));

        Persona pe = new Persona();

        pe.setCedula(cedula);
        pe.setDireccion(direccion);
        pe.setEmail(correo);
        pe.setFechaNacimiento(fecha);
        pe.setGenero(genero);
        pe.setNombres(nombre);

        return pe;
    }

    public LocalDate convertirFecha(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String[] partesF = fecha.split("/");

        int anio = (Integer.parseInt(partesF[2]) + 2000);
        String mes = partesF[1];
        String dia = partesF[0];

        if (mes.length() == 1) {
            mes = "0" + mes;
        }

        if (dia.length() == 1) {
            dia = "0" + dia;
        }

        LocalDate dt = LocalDate.parse(anio + mes + dia, formatter);
        return dt;
    }

    private void recorrerRegiones(Region r, Persona p, String cod) {

        for (Departamento d : r.getDptos()) {

            for (Municipio m : d.getMunicipios()) {

                if (m.getCodigo() == Integer.parseInt(cod)) {

                    System.out.println("Agrego Persona " + p.getNombres() + " en municipio " + m.getNombre());
                    m.getPersonas().enColar(p, p.getEdad());
                    return;
                }

            }

        }

    }

    private int recorrerRegionesSub(Region r) {
        int beneficiados = 0;
        for (Departamento d : r.getDptos()) {

            int b = recorrerPersonas(d, r);
            beneficiados += b;

        }
        return beneficiados;
    }

    private int recorrerPersonas(Departamento d, Region r) {
        int cantidad = 0;

        for (Municipio m : d.getMunicipios()) {
            ColaP personas = new ColaP();
            while (!m.getPersonas().esVacia()) {
                Persona p = m.getPersonas().deColar();
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                personas.enColar(p, p.getEdad());
                if ((-1 * p.getEdad()) > 40 && r.getSubsidiosActuales() < r.getCantidadBeneficiarios()) {

                    p.setNombres("+" + p.getNombres());
                    System.out.println("Persona " + p.getNombres() + " edad" + p.getEdad() + " puede recibir beneficio");
                    p.setSiEsBeneficiario(true);
                    r.setSubsidiosActuales(r.getSubsidiosActuales() + 1);
                    cantidad++;

                }

            }
            m.setPersonas(personas);
        }

        return cantidad;
    }

    private ListaCD<Persona> buscarPersonas(Region r) {
        ListaCD<Persona> personas = new ListaCD<>();
        for (Departamento re : r.getDptos()) {

            personas.concatenarListaCD(buscarPersonasMunicipios(re.getMunicipios()));

        }
        return personas;
    }

    private ListaCD<Persona> buscarPersonasMunicipios(ListaCD<Municipio> municipios) {
        ListaCD<Persona> personasR = new ListaCD<>();
        for (Municipio m : municipios) {

            ColaP<Persona> cPersonas = new ColaP<>();

            while (!m.getPersonas().esVacia()) {
                Persona personaI = m.getPersonas().deColar();
                cPersonas.enColar(personaI, personaI.getEdad());
                if (!personaI.isSiEsBeneficiario()) {
                    personasR.insertarAlInicio(personaI);
                }
            }
            m.setPersonas(cPersonas);
        }
        return personasR;
    }

    private int subsidiosRecibidos(Region r) {

        int cantidad = 0;

        for (Departamento d : r.getDptos()) {

            for (Municipio m : d.getMunicipios()) {

                cantidad += cantidadPersonasConBeneficios(m);

            }

        }

        return cantidad;

    }

    private int cantidadPersonasConBeneficios(Municipio m) {

        int cantidad = 0;

        ColaP<Persona> cPersonas = m.getPersonas();
        ColaP<Persona> personas = new ColaP<>();

        while (!cPersonas.esVacia()) {

            Persona p = cPersonas.deColar();
            personas.enColar(p, p.getEdad());

            if (p.isSiEsBeneficiario() == true) {

                cantidad++;

            }

        }
        m.setPersonas(personas);

        return cantidad;
    }

    private Pila<Region> llenarPila(ColaP<Region> region) {

        Pila<Region> pRegion = new Pila<>();

        while (!region.esVacia()) {

            pRegion.apilar(region.deColar());
        }

        return pRegion;
    }

    private String buscarPersona(long cedula, ListaCD<Departamento> d, Region r) {
        String informacion = "";
        for (Departamento de : d) {

            for (Municipio m : de.getMunicipios()) {

                informacion = buscarPersona(m, cedula, de, r);

            }

        }

        return informacion;
    }

    private String buscarPersona(Municipio m, long cedula, Departamento d, Region r) {

        String datos = "";

        ColaP<Persona> copia = new ColaP<>();

        while (!m.getPersonas().esVacia()) {

            Persona p = m.getPersonas().deColar();
            copia.enColar(p, p.getEdad());
            if (p.getCedula() == cedula) {

                datos = "Nombre Region: " + r.getNombre() + " Departamento: " + d.getNombre() + " Municipio:" + m.getNombre();
                System.out.println(datos);
            }

        }

        m.setPersonas(copia);
        return datos;
    }

    private int buscarSubsidios(ListaCD<Departamento> d, int codMunicipio) {

        for (Departamento de : d) {

            for (Municipio m : de.getMunicipios()) {

                if (m.getCodigo() == codMunicipio) {
                    System.out.print("EL municipio:" + m.getNombre() + ", Recibió ");
                    return m.beneficiados();
                }

            }
        }

        return 0;
    }
}

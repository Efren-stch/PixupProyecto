package org.soto.chavez.efren.lugares;

import org.soto.chavez.efren.generalUtil.ManejoArchivos;
import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;

import java.util.ArrayList;

public class Municipio extends Lugar {
    private static Integer idIteracion = 1;
    private static ArrayList<Municipio> listMunicipios = new ArrayList<>();
    private Estado estado;

    private static ManejoArchivos<Municipio> manejoArchivos = new ManejoArchivos<>(Municipio.class);


    private static Municipio manage;
    private Municipio municipioEncontrado;

    private Municipio() {
        super(0);
    }

    public static Municipio getManage() {
        if (manage == null) {
            manage = new Municipio();
        }
        return manage;
    }

    public Municipio(String nombre, Estado estado) {
        super(idIteracion, nombre);
        this.estado = estado;
        idIteracion++;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public static ArrayList<Municipio> getListMunicipios() {
        if (listMunicipios == null) {
            listMunicipios = new ArrayList<>();
        }
        return listMunicipios;
    }

    public Municipio encontrarMunicipio() {
        Lugar elementoBusqueda = buscarElemento(listMunicipios);
        if (elementoBusqueda == null) {
            System.out.println("No hay elementos registrados o el que buscas no fue encontrado");
            return null;
        }
        return (Municipio) elementoBusqueda;
    }
    @Override
    public void leerArchivo() {
        manejoArchivos.leerArchivo();
        listMunicipios = new ArrayList<>(manejoArchivos.getLista());
        System.out.println("Municipios leídos desde archivo.");
    }
    @Override
    public void guardarArchivo() {
        manejoArchivos.setLista(new ArrayList<>(listMunicipios));
        manejoArchivos.guardarArchivo();
        System.out.println("Municipios guardados en archivo.");
    }

    @Override
    public void alta() {
        if(Estado.getListEstados().isEmpty()){
            System.out.println("No hay estados registrados");
            return;
        }
        if (listMunicipios == null){
            listMunicipios = new ArrayList<>();
        }
        String nombreAlta = ReadUtil.read(Salidas.leerNombre);

        System.out.println("Selecciona el id del estado al que pertenece: ");
        Estado estadoLigado = Estado.getManage().encontrarEstado();

        listMunicipios.add(new Municipio(nombreAlta, estadoLigado));
    }

    @Override
    public void baja() {
        municipioEncontrado = encontrarMunicipio();
        if (municipioEncontrado != null) {
            System.out.println("Borrando el municipio: " + municipioEncontrado.getNombre());
            listMunicipios.remove(municipioEncontrado);
        }
    }

    @Override
    public void modificacion() {
        municipioEncontrado = encontrarMunicipio();
        if (municipioEncontrado != null) municipioEncontrado.setNombre(ReadUtil.read(Salidas.nuevoNombre));

    }

    @Override
    public void mostrarVista(){
        vista(listMunicipios);
    }

    /*
    @Override
    public void vista() {
        municipioEncontrado = encontrarMunicipio();
        if (municipioEncontrado != null) System.out.println("Mostrando Municipio: " + municipioEncontrado.getNombre() + ". De id: " + municipioEncontrado.getId() + " en el Estado: " + municipioEncontrado.getEstado().getNombre());

    }

     */
}

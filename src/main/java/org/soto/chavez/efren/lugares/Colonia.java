package org.soto.chavez.efren.lugares;

import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;

import java.util.ArrayList;

public class Colonia extends Lugar{
    private static Integer idIteracion = 1;
    private static ArrayList<Colonia> listColonia;

    private String nombre;
    private Integer cp;
    private Municipio municipio;

    private static Colonia manage;
    private Colonia coloniaEncontrada;

    private Colonia() {
        super(0);
    }
    public static Colonia getManage(){
        if (manage == null){
            manage = new Colonia();
        }
        return manage;
    }

    public Colonia(String nombre, Municipio municipio, Integer cp) {
        super(idIteracion);
        this.nombre = nombre;
        this.municipio = municipio;
        this.cp = cp;

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

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public Colonia encontrarColonia() {
        Lugar elementoBusqueda = buscarElemento(listColonia);
        if (elementoBusqueda == null) {
            System.out.println("No hay elementos registrados o el que buscas no fue encontrado");
            return null;
        }
        return (Colonia) elementoBusqueda;
    }

    @Override
    public void alta() {
        if(Municipio.getListMunicipios().isEmpty()){
            System.out.println("No hay municipios registrados");
            return;
        }
        if (listColonia == null){
            listColonia = new ArrayList<>();
        }
        String nombreAlta = ReadUtil.read(Salidas.leerNombre);
        Integer cpAlta = ReadUtil.readInt(Salidas.leerCP);

        System.out.println("Selecciona el id del estado al que pertenece: ");
        Municipio municipioLigado = Municipio.getManage().encontrarMunicipio();

        listColonia.add(new Colonia(nombreAlta, municipioLigado, cpAlta));

    }

    @Override
    public void baja() {
        coloniaEncontrada = encontrarColonia();
        if (coloniaEncontrada != null) {
            System.out.println("Borrando el municipio: " + coloniaEncontrada.getNombre());
            listColonia.remove(coloniaEncontrada);
        }

    }

    @Override
    public void modificacion() {
        coloniaEncontrada = encontrarColonia();
        if (coloniaEncontrada != null) {
            coloniaEncontrada.setNombre(ReadUtil.read(Salidas.nuevoNombre));
            coloniaEncontrada.setCp(ReadUtil.readInt(Salidas.leerCP));
        }

    }

    @Override
    public void vista() {
        coloniaEncontrada = encontrarColonia();
        if (coloniaEncontrada != null) System.out.println("Mostrando Municipio: " + coloniaEncontrada.getNombre() + ". De id: " + coloniaEncontrada.getId() + " en el municipio: " + coloniaEncontrada.getMunicipio().getNombre());

    }


}

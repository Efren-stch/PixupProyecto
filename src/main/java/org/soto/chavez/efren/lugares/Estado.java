package org.soto.chavez.efren.lugares;

import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;

import java.util.ArrayList;

public class Estado extends Lugar{
    private static Integer idIteracion = 1;
    private static ArrayList<Estado> listEstados;

    private String nombre;

    private static Estado manage;
    private Estado estadoEncontrado;

    private Estado() {
        super(0);
    }
    public static Estado getManage(){
        if (manage == null){
            manage = new Estado();
        }
        return manage;
    }

    public Estado(String nombre) {
        super(idIteracion);
        this.nombre = nombre;

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

    public static ArrayList<Estado> getListEstados() {
        if (listEstados == null) {
            listEstados = new ArrayList<>();
        }
        return listEstados;
    }

    public Estado encontrarEstado() {
        Lugar elementoBusqueda = buscarElemento(listEstados);
        if (elementoBusqueda == null) {
            System.out.println("No hay elementos registrados o el que buscas no fue encontrado");
            return null;
        }
        return (Estado) elementoBusqueda;
    }

    @Override
    public void alta() {
        if (listEstados == null){
            listEstados = new ArrayList<>();
        }
        String nombreAlta = ReadUtil.read(Salidas.leerNombre);

        listEstados.add(new Estado(nombreAlta));
    }

    @Override
    public void baja() {
        estadoEncontrado = encontrarEstado();
        if(estadoEncontrado != null) {
            System.out.println("Borrando el estado: " + estadoEncontrado.getNombre());
            listEstados.remove(estadoEncontrado);
        }
    }

    @Override
    public void modificacion() {
        estadoEncontrado = encontrarEstado();
        if(estadoEncontrado != null) estadoEncontrado.setNombre(ReadUtil.read(Salidas.nuevoNombre));
    }

    @Override
    public void vista() {
        estadoEncontrado = encontrarEstado();
        if (estadoEncontrado != null) System.out.println("Mostrando Estado: " + estadoEncontrado.getNombre() + ". De id: " + estadoEncontrado.getId());
    }
}

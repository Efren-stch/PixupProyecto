package org.soto.chavez.efren.lugares;

import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Lugar implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer id;
    protected String nombre;

    protected Lugar(Integer id){
        this.id = id;
    }

    public Lugar(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    public abstract void leerArchivo();
    public abstract void guardarArchivo();
    public abstract void alta();
    public abstract void baja();
    public abstract void modificacion();
    public abstract void mostrarVista();

    public void vista (ArrayList<? extends Lugar> list) {
        list.forEach((l) -> {
            System.out.println("[ " + l.getNombre() + " de ID = " + l.getId() + " ]");
        });
    }

    public Lugar buscarElemento(ArrayList<? extends Lugar> list) {
        if (list == null || list.isEmpty()) {
            return null;
        } else {
            Integer idBusqueda = ReadUtil.readInt(Salidas.leerId);
            for (Lugar elemento : list) {
                if (elemento.getId().equals(idBusqueda)) {
                    return elemento;
                }
            }
            return null;
        }
    }
}
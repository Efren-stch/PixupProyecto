package org.soto.chavez.efren.model.catalogos;

import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class ClaseCatalogo implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer id;
    protected String nombre;

    protected ClaseCatalogo() {
    }
    protected ClaseCatalogo(Integer id, String nombre) {
        this.nombre = nombre;
        this.id = id;
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

    public void realizarBaja(ArrayList<? extends ClaseCatalogo> list){
        ClaseCatalogo claseCatalogoEncontrado = buscarElemento(list);
        if(claseCatalogoEncontrado != null){
            System.out.printf("Borrando el registro " + claseCatalogoEncontrado.getNombre());
            list.remove(claseCatalogoEncontrado);
        }
    }
    public void mostrarVista(ArrayList<? extends ClaseCatalogo> list){
        list.forEach((l) -> {
            System.out.println(l);
        });
    }

    public ClaseCatalogo buscarElemento(ArrayList<? extends ClaseCatalogo> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("No existen registros aún");
            return null;
        } else {
            Integer idBusqueda = ReadUtil.readInt(Salidas.leerId);
            for (ClaseCatalogo elemento : list) {
                if (elemento.getId().equals(idBusqueda)) {
                    return elemento;
                }
            }
            System.out.println("El registro no fue encontrado");
            return null;
        }
    }

    public ClaseCatalogo buscarElemento(ArrayList<? extends ClaseCatalogo> list, int idBusqueda) {
        if (list == null || list.isEmpty()) {
            System.out.println("No existen registros aún");
            return null;
        } else {
            for (ClaseCatalogo elemento : list) {
                if (elemento.getId().equals(idBusqueda)) {
                    return elemento;
                }
            }
            System.out.println("El registro no fue encontrado");
            return null;
        }
    }

    public abstract void alta();
    public abstract void baja();
    public abstract void modificacion();
    public abstract void vista();
    public abstract void leerArchivo();
    public abstract void guardarArchivo();
    public abstract void leerBaseDatos();
    public abstract void guardarBaseDatos();
}

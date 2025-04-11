package org.soto.chavez.efren.model.catalogos;

import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.generalUtil.sql.CatalogoJdbc;

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

    public void realizarBaja(CatalogoJdbc<? extends ClaseCatalogo> catalogoJdbc) {
        ClaseCatalogo claseCatalogoEncontrado = buscarElemento(catalogoJdbc);
        if (claseCatalogoEncontrado != null) {
            System.out.println("Borrando el registro " + claseCatalogoEncontrado.getNombre());
            boolean eliminado = catalogoJdbc.eliminar(claseCatalogoEncontrado.getId());
            if (eliminado) {
                System.out.println("Elemento eliminado de la base de datos correctamente.");
            } else {
                System.out.println("No se pudo eliminar el elemento de la base de datos.");
            }
        }
    }

    public void mostrarVista(CatalogoJdbc<? extends ClaseCatalogo> catalogoJdbc) {
        ArrayList <? extends ClaseCatalogo>  list = catalogoJdbc.findAll();
        if (list == null || list.isEmpty()) {
            System.out.println("No existen registros aún");
            return;
        }
        list.forEach((l) -> System.out.println(l));
    }

    public ClaseCatalogo buscarElemento(CatalogoJdbc<? extends ClaseCatalogo> catalogoJdbc) {
        ArrayList list = catalogoJdbc.findAll();
        if (list == null || list.isEmpty()) {
            System.out.println("No existen registros aún");
            return null;
        } else {
            Integer idBusqueda = ReadUtil.readInt(Salidas.leerId);
            if(catalogoJdbc.findById(idBusqueda) == null){
                System.out.println("No encontrado");
                return null;
            } else {
                return catalogoJdbc.findById(idBusqueda);
            }
        }
    }

    public ClaseCatalogo buscarElemento(CatalogoJdbc<? extends ClaseCatalogo> catalogoJdbc, int idBusqueda) {
        ArrayList list = catalogoJdbc.findAll();
        if (list == null || list.isEmpty()) {
            System.out.println("No existen registros aún");
            return null;
        } else {
            if(catalogoJdbc.findById(idBusqueda) == null){
                System.out.println("No encontrado");
                return null;
            } else {
                return catalogoJdbc.findById(idBusqueda);
            }
        }
    }

    public boolean estaVacia(CatalogoJdbc<? extends ClaseCatalogo> catalogoJdbc){
        ArrayList list = catalogoJdbc.findAll();
        if (list == null || list.isEmpty()) {
            System.out.println("No existen registros aún");
            return true;
        }
        return false;
    }

    public abstract void baja();

    public abstract void alta();

    public abstract void modificacion();

    public abstract void vista();
    /*
    public abstract void leerArchivo();

    public abstract void guardarArchivo();

    public abstract void leerBaseDatos();

    public abstract void guardarBaseDatos();

     */
}

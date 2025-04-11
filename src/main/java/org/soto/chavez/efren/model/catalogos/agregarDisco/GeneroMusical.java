package org.soto.chavez.efren.model.catalogos.agregarDisco;

import org.soto.chavez.efren.generalUtil.ManejoArchivos;
import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.generalUtil.sql.implementacion.GeneroMusicalJdbcImpl;
import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;

import java.util.ArrayList;

public class GeneroMusical extends ClaseCatalogo {
    private static final long serialVersionUID = 1L;
    private static final ManejoArchivos<GeneroMusical> manejoArchivos = new ManejoArchivos<>(GeneroMusical.class);
    private static Integer idIteracion = 0;
    private static GeneroMusical manage;
    //private String descripcion;
    private GeneroMusical generoMusicalEncontrado;

    public GeneroMusical() {
        super();
    }

    public GeneroMusical(String nombre) {
        super(++idIteracion, nombre);
        //this.descripcion = descripcion;
    }

    public static GeneroMusical getManage() {
        if (manage == null) {
            manage = new GeneroMusical();
        }
        return manage;
    }


    @Override
    public void alta() {
        String nombreAlta = ReadUtil.read(Salidas.leerNombre);
        GeneroMusicalJdbcImpl.getInstance().guardar(new GeneroMusical(nombreAlta));
    }

    @Override
    public void baja() {
        realizarBaja(GeneroMusicalJdbcImpl.getInstance());
    }

    @Override
    public void modificacion() {
        generoMusicalEncontrado = (GeneroMusical) buscarElemento(GeneroMusicalJdbcImpl.getInstance());

        if (generoMusicalEncontrado != null) {
            generoMusicalEncontrado.setNombre(ReadUtil.read(Salidas.nuevoNombre));
            GeneroMusicalJdbcImpl.getInstance().actualizar(generoMusicalEncontrado);
        }
    }

    @Override
    public void vista() {
        mostrarVista(GeneroMusicalJdbcImpl.getInstance());
    }

    @Override
    public String toString() {
        return "GeneroMusical{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }

    /*
    @Override
    public void leerArchivo() {
        manejoArchivos.leerArchivo();
        listGeneroMusical = new ArrayList<>(manejoArchivos.getLista());
        System.out.println("Estados leídos desde archivo.");
    }

    @Override
    public void guardarArchivo() {
        manejoArchivos.setLista(new ArrayList<>(listGeneroMusical));
        manejoArchivos.guardarArchivo();
        System.out.println("Estados guardados en archivo.");
    }

    @Override
    public void leerBaseDatos() {
        listGeneroMusical = GeneroMusicalJdbcImpl.getInstance().findAll();
    }

    @Override
    public void guardarBaseDatos() {
        GeneroMusicalJdbcImpl db = GeneroMusicalJdbcImpl.getInstance();
        for (GeneroMusical e : listGeneroMusical) {
            db.guardar(e);
        }
    }



     */
}

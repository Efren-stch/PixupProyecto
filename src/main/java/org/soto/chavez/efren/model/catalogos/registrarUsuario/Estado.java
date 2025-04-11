package org.soto.chavez.efren.model.catalogos.registrarUsuario;

import org.soto.chavez.efren.generalUtil.ManejoArchivos;
import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.generalUtil.sql.implementacion.EstadoJdbcImpl;
import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;

import java.util.ArrayList;

public class Estado extends ClaseCatalogo {

    private static final long serialVersionUID = 1L;

    private static Integer idIteracion = 0;

    private static ManejoArchivos<Estado> manejoArchivos = new ManejoArchivos<>(Estado.class);


    private static Estado manage;

    private Estado estadoEncontrado;


    public Estado() {
        super();
    }


    public Estado(String nombre) {
        super(++idIteracion, nombre);
    }


    public static Estado getManage() {
        if (manage == null) {
            manage = new Estado();
        }
        return manage;
    }


    @Override
    public void alta() {
        String nombreAlta = ReadUtil.read(Salidas.leerNombre);
        EstadoJdbcImpl.getInstance().guardar(new Estado(nombreAlta));
    }


    @Override
    public void baja() {
        realizarBaja(EstadoJdbcImpl.getInstance());
    }

    @Override
    public void modificacion() {
        estadoEncontrado = (Estado) buscarElemento(EstadoJdbcImpl.getInstance());
        if (estadoEncontrado != null) {
            estadoEncontrado.setNombre(ReadUtil.read(Salidas.nuevoNombre));
            EstadoJdbcImpl.getInstance().actualizar(estadoEncontrado);
        }
    }

    @Override
    public void vista() {
        mostrarVista(EstadoJdbcImpl.getInstance());
    }
    @Override
    public String toString() {
        return "Estado{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }

    /*
    @Override
    public void leerArchivo() {
        manejoArchivos.leerArchivo();
        listEstados = new ArrayList<>(manejoArchivos.getLista());
        System.out.println("Estados leídos desde archivo.");
    }

    @Override
    public void guardarArchivo() {
        manejoArchivos.setLista(new ArrayList<>(listEstados));
        manejoArchivos.guardarArchivo();
        System.out.println("Estados guardados en archivo.");
    }



    @Override
    public void leerBaseDatos() {
        listEstados = EstadoJdbcImpl.getInstance().findAll();
    } //QUITAR

    @Override
    public void guardarBaseDatos() { //QUITAR
        EstadoJdbcImpl db = EstadoJdbcImpl.getInstance();
        for (Estado e : listEstados) {
            db.guardar(e);
        }

    }

     */
}
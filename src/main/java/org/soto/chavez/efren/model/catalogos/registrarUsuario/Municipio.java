package org.soto.chavez.efren.model.catalogos.registrarUsuario;

import org.soto.chavez.efren.generalUtil.ManejoArchivos;
import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.generalUtil.sql.implementacion.EstadoJdbcImpl;
import org.soto.chavez.efren.generalUtil.sql.implementacion.MunicipioJdbcImpl;
import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;

import java.util.ArrayList;

public class Municipio extends ClaseCatalogo {
    private static final long serialVersionUID = 1L;

    private static final ManejoArchivos<Municipio> manejoArchivos = new ManejoArchivos<>(Municipio.class);

    private static Integer idIteracion = 0;


    private static Municipio manage;

    private Estado estado;

    private Municipio municipioEncontrado;


    public Municipio() {

        super();

    }


    public Municipio(String nombre, Estado estado) {

        super(++idIteracion, nombre);

        this.estado = estado;

    }


    public static Municipio getManage() {

        if (manage == null) {

            manage = new Municipio();

        }

        return manage;

    }



    public Estado getEstado() {
        return estado;
    }

    public void setEstado(int id) {
        this.estado = (Estado) buscarElemento(EstadoJdbcImpl.getInstance(), id);
    }

    public void setEstado() {
        this.estado = (Estado) buscarElemento(EstadoJdbcImpl.getInstance());
    }

    @Override
    public void alta() {
        if (estaVacia(EstadoJdbcImpl.getInstance())) {
            System.out.println("No hay estados registrados");
            return;
        }
        String nombreAlta = ReadUtil.read(Salidas.leerNombre);
        System.out.println("Selecciona el id del estado al que pertenece: ");
        Estado estadoSeleccionado = (Estado) buscarElemento(EstadoJdbcImpl.getInstance());
        MunicipioJdbcImpl.getInstance().guardar(new Municipio(nombreAlta, estadoSeleccionado));
    }


    @Override
    public void baja() {
        realizarBaja(MunicipioJdbcImpl.getInstance());
    }


    @Override
    public void modificacion() {
        municipioEncontrado = (Municipio) buscarElemento(MunicipioJdbcImpl.getInstance());
        if (municipioEncontrado != null) {
            municipioEncontrado.setNombre(ReadUtil.read(Salidas.nuevoNombre));
            MunicipioJdbcImpl.getInstance().actualizar(municipioEncontrado);
        }
    }


    @Override
    public void vista() {
        mostrarVista(MunicipioJdbcImpl.getInstance());
    }

    @Override

    public String toString() {

        return "Municipio{" +

                "nombre='" + nombre + '\'' +

                ", id=" + id +

                ", estado=" + estado.getNombre() +

                '}';

    }

    /*
    public void leerArchivo() {

        manejoArchivos.leerArchivo();

        listMunicipios = new ArrayList<>(manejoArchivos.getLista());  // Sincronizar con la lista interna

        System.out.println("Estados leídos desde archivo.");

    }


    @Override

    public void guardarArchivo() {

        manejoArchivos.setLista(new ArrayList<>(listMunicipios));

        manejoArchivos.guardarArchivo();

        System.out.println("Estados guardados en archivo.");

    }





    @Override

    public void leerBaseDatos() {

        listMunicipios = MunicipioJdbcImpl.getInstance().findAll();

    }


    @Override

    public void guardarBaseDatos() {

        MunicipioJdbcImpl db = MunicipioJdbcImpl.getInstance();

        for (Municipio e : listMunicipios) {

            db.guardar(e);

        }

    }

     */
}
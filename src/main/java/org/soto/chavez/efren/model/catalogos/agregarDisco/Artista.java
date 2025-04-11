package org.soto.chavez.efren.model.catalogos.agregarDisco;

import org.soto.chavez.efren.generalUtil.ManejoArchivos;
import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.generalUtil.sql.implementacion.ArtistaJdbcImpl;
import org.soto.chavez.efren.generalUtil.sql.implementacion.ColoniaJdbcImpl;
import org.soto.chavez.efren.generalUtil.sql.implementacion.EstadoJdbcImpl;
import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;
import org.soto.chavez.efren.model.catalogos.registrarUsuario.Colonia;

import java.util.ArrayList;

public class Artista extends ClaseCatalogo {
    private static final long serialVersionUID = 1L;
    private static final ManejoArchivos<Artista> manejoArchivos = new ManejoArchivos<>(Artista.class);
    private static Integer idIteracion = 0;
    private static Artista manage;
    private Artista artistaEncontrado;

    public Artista() {
        super();
    }

    public Artista(String nombre) {
        super(++idIteracion, nombre);
    }

    public static Artista getManage() {
        if (manage == null) {
            manage = new Artista();
        }
        return manage;
    }

    @Override
    public void alta() {
        String nombreAlta = ReadUtil.read(Salidas.leerNombre);
        ArtistaJdbcImpl.getInstance().guardar(new Artista(nombreAlta));
    }

    @Override
    public void baja() {
        realizarBaja(ArtistaJdbcImpl.getInstance());
    }

    @Override
    public void modificacion() {
        artistaEncontrado = (Artista) buscarElemento(ArtistaJdbcImpl.getInstance());

        if (artistaEncontrado != null) {
            artistaEncontrado.setNombre(ReadUtil.read(Salidas.nuevoNombre));
            ArtistaJdbcImpl.getInstance().actualizar(artistaEncontrado);
        }
    }

    @Override
    public void vista() {
        mostrarVista(ArtistaJdbcImpl.getInstance());
    }

    /*

    @Override
    public void leerArchivo() {
        manejoArchivos.leerArchivo();
        listArtista = new ArrayList<>(manejoArchivos.getLista());
        System.out.println("Estados leídos desde archivo.");
    }

    @Override
    public void guardarArchivo() {
        manejoArchivos.setLista(new ArrayList<>(listArtista));
        manejoArchivos.guardarArchivo();
        System.out.println("Estados guardados en archivo.");
    }

    @Override
    public void leerBaseDatos() {
        listArtista = ArtistaJdbcImpl.getInstance().findAll();
    }

    @Override
    public void guardarBaseDatos() {
        ArtistaJdbcImpl db = ArtistaJdbcImpl.getInstance();
        for (Artista e : listArtista) {
            db.guardar(e);
        }
    }

     */


    @Override
    public String toString() {
        return "Artista{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }
}

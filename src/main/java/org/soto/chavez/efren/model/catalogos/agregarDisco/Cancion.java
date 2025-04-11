package org.soto.chavez.efren.model.catalogos.agregarDisco;

import org.soto.chavez.efren.generalUtil.ManejoArchivos;
import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.generalUtil.sql.implementacion.CancionJdbcImpl;
import org.soto.chavez.efren.generalUtil.sql.implementacion.DiscoJdbcImpl;
import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;

import java.util.ArrayList;

public class Cancion extends ClaseCatalogo {
    private static final long serialVersionUID = 1L;
    private static final ManejoArchivos<Cancion> manejoArchivos = new ManejoArchivos<>(Cancion.class);
    private static Integer idIteracion = 0;
    private static Cancion manage;

    private double duracion;
    private Disco disco;
    private Cancion cancionEncontrada;

    public Cancion() {
        super();
    }

    public Cancion(String nombre, double duracion, Disco disco) {
        super(++idIteracion, nombre);
        this.duracion = duracion;
        this.disco = disco;
    }

    public static Cancion getManage() {
        if (manage == null) {
            manage = new Cancion();
        }
        return manage;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public Disco getDisco() {
        return disco;
    }

    public void setDisco(int id) {
        this.disco = (Disco) buscarElemento(DiscoJdbcImpl.getInstance(), id);
    }

    @Override
    public void alta() {
        if (estaVacia(DiscoJdbcImpl.getInstance())) {
            System.out.println("No hay Discos registrados");
            return;
        }

        String nombreAlta = ReadUtil.read(Salidas.leerNombre);
        double duracionAlta = ReadUtil.readDouble("Digite la duración: ");

        System.out.println("Selecciona el id del disco al que pertenece: ");
        Disco discoLigado = (Disco) buscarElemento(DiscoJdbcImpl.getInstance());

        CancionJdbcImpl.getInstance().guardar(new Cancion(nombreAlta, duracionAlta, discoLigado));
    }

    @Override
    public void baja() {
        realizarBaja(CancionJdbcImpl.getInstance());
    }

    @Override
    public void modificacion() {
        cancionEncontrada = (Cancion) buscarElemento(CancionJdbcImpl.getInstance());

        if (cancionEncontrada != null) {
            cancionEncontrada.setNombre(ReadUtil.read(Salidas.nuevoNombre));
            cancionEncontrada.setDuracion(ReadUtil.readDouble("Digite la nueva duración: "));

            System.out.println("Selecciona el id del nuevo disco al que pertenece: ");
            cancionEncontrada.setDisco( ((Disco)buscarElemento(DiscoJdbcImpl.getInstance())).getId() );

            CancionJdbcImpl.getInstance().actualizar(cancionEncontrada);
        }
    }

    @Override
    public void vista() {
        mostrarVista(CancionJdbcImpl.getInstance());
    }

    /*
    @Override
    public void leerArchivo() {
        manejoArchivos.leerArchivo();
        listCancion = new ArrayList<>(manejoArchivos.getLista());
        System.out.println("Canciones leídas desde archivo.");
    }

    @Override
    public void guardarArchivo() {
        manejoArchivos.setLista(new ArrayList<>(listCancion));
        manejoArchivos.guardarArchivo();
        System.out.println("Canciones guardadas en archivo.");
    }

    @Override
    public void leerBaseDatos() {
        listCancion = CancionJdbcImpl.getInstance().findAll();
    }

    @Override
    public void guardarBaseDatos() {
        CancionJdbcImpl db = CancionJdbcImpl.getInstance();
        for (Cancion c : listCancion) {
            db.guardar(c);
        }
    }

     */

    @Override
    public String toString() {
        return "Cancion{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", duracion=" + duracion +
                ", disco=" + (disco != null ? disco.getNombre() : "Sin disco") +
                '}';
    }
}

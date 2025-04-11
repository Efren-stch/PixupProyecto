package org.soto.chavez.efren.model.catalogos.registrarUsuario;

import org.soto.chavez.efren.generalUtil.ManejoArchivos;
import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.generalUtil.sql.implementacion.ColoniaJdbcImpl;
import org.soto.chavez.efren.generalUtil.sql.implementacion.EstadoJdbcImpl;
import org.soto.chavez.efren.generalUtil.sql.implementacion.MunicipioJdbcImpl;
import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;

import java.util.ArrayList;

public class Colonia extends ClaseCatalogo {
    private static final long serialVersionUID = 1L;

    private static final ManejoArchivos<Colonia> manejoArchivos = new ManejoArchivos<>(Colonia.class);

    private static Integer idIteracion = 0;

    private static Colonia manage;

    private Integer cp;

    private Municipio municipio;

    private Colonia coloniaEncontrada;


    public Colonia() {

        super();

    }


    private Colonia(String nombre, Municipio municipio, Integer cp) {

        super(idIteracion++, nombre);

        this.municipio = municipio;

        this.cp = cp;

    }


    public static Colonia getManage() {

        if (manage == null) {

            manage = new Colonia();

        }

        return manage;

    }


    public Integer getCp() {

        return cp;

    }


    public void setCp(Integer cp) {

        this.cp = cp;

    }


    public Municipio getMunicipio() {

        return municipio;

    }


    public void setMunicipio(int id) {

        this.municipio = (Municipio) buscarElemento(MunicipioJdbcImpl.getInstance(), id);

    }


    @Override
    public void alta() {
        if (estaVacia(MunicipioJdbcImpl.getInstance())) {
            System.out.println("No hay estados registrados");
            return;
        }
        String nombreAlta = ReadUtil.read(Salidas.leerNombre);
        Integer cpAlta = ReadUtil.readInt(Salidas.leerCP);

        System.out.println("Selecciona el id del municipio al que pertenece: ");
        Municipio municipioSeleccionado = (Municipio) buscarElemento(MunicipioJdbcImpl.getInstance());

        ColoniaJdbcImpl.getInstance().guardar(new Colonia(nombreAlta, municipioSeleccionado, cpAlta));
    }

    @Override
    public void baja() {
        realizarBaja(ColoniaJdbcImpl.getInstance());
    }

    @Override
    public void modificacion() {
        coloniaEncontrada = (Colonia) buscarElemento(ColoniaJdbcImpl.getInstance());
        if (coloniaEncontrada != null) {
            coloniaEncontrada.setNombre(ReadUtil.read(Salidas.nuevoNombre));
            coloniaEncontrada.setCp(ReadUtil.readInt(Salidas.leerCP));
            ColoniaJdbcImpl.getInstance().actualizar(coloniaEncontrada);
        }
    }

    @Override
    public void vista() {
        mostrarVista(ColoniaJdbcImpl.getInstance());
    }

/*
    public void leerArchivo() {

        manejoArchivos.leerArchivo();

        listColonia = new ArrayList<>(manejoArchivos.getLista());  // Sincronizar con la lista interna

        System.out.println("Estados leídos desde archivo.");

    }


 */
    @Override

    public String toString() {

        return "Colonia {" +

                "nombre ='" + nombre + '\'' +

                ", id =" + id +

                ", cp =" + cp +

                ", municipio =" + municipio.getNombre() +

                '}';

    }

    /*
    @Override

    public void guardarArchivo() {

        manejoArchivos.setLista(new ArrayList<>(listColonia));

        manejoArchivos.guardarArchivo();

        System.out.println("Estados guardados en archivo.");

    }


    @Override

    public void leerBaseDatos() {

        listColonia = ColoniaJdbcImpl.getInstance().findAll();

    }


    @Override

    public void guardarBaseDatos() {

        ColoniaJdbcImpl db = ColoniaJdbcImpl.getInstance();

        for (Colonia e : listColonia) {

            db.guardar(e);

        }

    }




     */
}

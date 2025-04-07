package org.soto.chavez.efren.model.catalogos.agregarDisco;

import org.soto.chavez.efren.generalUtil.ManejoArchivos;
import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.generalUtil.sql.implementacion.ArtistaJdbcImpl;
import org.soto.chavez.efren.generalUtil.sql.implementacion.DisqueraJdbcImpl;
import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;

import java.util.ArrayList;

public class Disquera extends ClaseCatalogo {
    private static final long serialVersionUID = 1L;
    private static Integer idIteracion = 1;
    private static ArrayList<Disquera> listDisquera = new ArrayList<>();
    private static final ManejoArchivos<Disquera> manejoArchivos = new ManejoArchivos<>(Disquera.class);
    private static Disquera manage;
    private Disquera disqueraEncontrada;

    public Disquera() {
        super();
    }
    public static Disquera getManage(){
        if (manage == null){
            manage = new Disquera();
        }
        return manage;
    }
    public Disquera(String nombre) {
        super(++idIteracion, nombre);
    }
    public static ArrayList<Disquera> getListDisquera() {
        if (listDisquera == null) {
            listDisquera = new ArrayList<>();
        }
        return listDisquera;
    }
    @Override
    public void alta() {
        if (listDisquera == null){
            listDisquera = new ArrayList<>();
        }
        String nombreAlta = ReadUtil.read(Salidas.leerNombre);

        listDisquera.add(new Disquera(nombreAlta));
    }
    @Override
    public void baja() {
        realizarBaja(listDisquera);
    }
    @Override
    public void modificacion() {
        disqueraEncontrada = (Disquera) buscarElemento(listDisquera);
        if(disqueraEncontrada != null) disqueraEncontrada.setNombre(ReadUtil.read(Salidas.nuevoNombre));
    }
    @Override
    public void vista(){
        mostrarVista(listDisquera);
    }
    @Override
    public void leerArchivo() {
        manejoArchivos.leerArchivo();
        listDisquera = new ArrayList<>(manejoArchivos.getLista());
        System.out.println("Estados leídos desde archivo.");
    }
    @Override
    public void guardarArchivo() {
        manejoArchivos.setLista(new ArrayList<>(listDisquera));
        manejoArchivos.guardarArchivo();
        System.out.println("Estados guardados en archivo.");
    }
    @Override
    public void leerBaseDatos() {
        listDisquera = DisqueraJdbcImpl.getInstance().findAll();
    }
    @Override
    public void guardarBaseDatos() {
        DisqueraJdbcImpl db = DisqueraJdbcImpl.getInstance();
        for (Disquera e : listDisquera) {
            db.guardar(e);
        }
    }

    @Override
    public String toString() {
        return "Disquera{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }
}

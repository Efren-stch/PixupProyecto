package org.soto.chavez.efren.model.agregarDisco;

import org.soto.chavez.efren.generalUtil.ManejoArchivos;
import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.model.ClaseCatalogo;

import java.util.ArrayList;

public class GeneroMusical extends ClaseCatalogo {
    private static final long serialVersionUID = 1L;
    private static Integer idIteracion = 1;
    private static ArrayList<GeneroMusical> listGeneroMusical = new ArrayList<>();
    private static final ManejoArchivos<GeneroMusical> manejoArchivos = new ManejoArchivos<>(GeneroMusical.class);

    private String descripcion;

    private static GeneroMusical manage;
    private GeneroMusical generoMusicalEncontrado;

    private GeneroMusical() {
        super();
    }
    public static GeneroMusical getManage(){
        if (manage == null){
            manage = new GeneroMusical();
        }
        return manage;
    }

    public GeneroMusical(String nombre, String descripcion) {
        super(idIteracion, nombre);
        this.descripcion = descripcion;
        idIteracion++;
    }

    public static ArrayList<GeneroMusical> getListGeneroMusical() {
        if (listGeneroMusical == null) {
            listGeneroMusical = new ArrayList<>();
        }
        return listGeneroMusical;
    }

    @Override
    public void alta() {
        if (listGeneroMusical == null){
            listGeneroMusical = new ArrayList<>();
        }
        String nombreAlta = ReadUtil.read(Salidas.leerNombre);
        String descripcionAlta = ReadUtil.read("Redacte la descripción: ");

        listGeneroMusical.add(new GeneroMusical(nombreAlta, descripcionAlta));
    }
    @Override
    public void baja() {
        realizarBaja(listGeneroMusical);
    }
    @Override
    public void modificacion() {
        generoMusicalEncontrado = (GeneroMusical) buscarElemento(listGeneroMusical);
        if(generoMusicalEncontrado != null) generoMusicalEncontrado.setNombre(ReadUtil.read(Salidas.nuevoNombre));
    }
    @Override
    public void vista(){
        mostrarVista(listGeneroMusical);
    }
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
    public String toString() {
        return "GeneroMusical{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}

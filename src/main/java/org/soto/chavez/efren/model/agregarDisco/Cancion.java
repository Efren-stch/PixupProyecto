package org.soto.chavez.efren.model.agregarDisco;

import org.soto.chavez.efren.generalUtil.ManejoArchivos;
import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.model.ClaseCatalogo;
import org.soto.chavez.efren.model.registrarUsuario.Municipio;

import java.util.ArrayList;

public class Cancion extends ClaseCatalogo {
    private static final long serialVersionUID = 1L;
    private static Integer idIteracion = 1;
    private static ArrayList<Cancion> listCancion = new ArrayList<>();
    private static final ManejoArchivos<Cancion> manejoArchivos = new ManejoArchivos<>(Cancion.class);

    String duracion;
    Disco disco;
    private static Cancion manage;
    private Cancion cancionEncontrada;

    private Cancion() {
        super();
    }
    public static Cancion getManage(){
        if (manage == null){
            manage = new Cancion();
        }
        return manage;
    }
    public Cancion(String nombre, String duracion, Disco disco) {
        super(idIteracion, nombre);
        this.duracion = duracion;
        this.disco = disco;
        idIteracion++;
    }
    public static ArrayList<Cancion> getListCancion() {
        if (listCancion == null) {
            listCancion = new ArrayList<>();
        }
        return listCancion;
    }
    @Override
    public void alta() {
        if(Disco.getListDisco().isEmpty()){
            System.out.println("No hay Discos registrados");
            return;
        }
        if (listCancion == null){
            listCancion = new ArrayList<>();
        }
        String nombreAlta = ReadUtil.read(Salidas.leerNombre);
        String duracionAlta = ReadUtil.read("Digite la duración: ");

        System.out.println("Selecciona el id del disco al que pertenece: ");
        Disco discoLigado = (Disco) buscarElemento(Disco.getListDisco());

        listCancion.add(new Cancion(nombreAlta, duracionAlta, discoLigado));
    }
    @Override
    public void baja() {
        realizarBaja(listCancion);
    }
    @Override
    public void modificacion() {
        cancionEncontrada = (Cancion) buscarElemento(listCancion);
        if(cancionEncontrada != null) cancionEncontrada.setNombre(ReadUtil.read(Salidas.nuevoNombre));
    }
    @Override
    public void vista(){
        mostrarVista(listCancion);
    }
    @Override
    public void leerArchivo() {
        manejoArchivos.leerArchivo();
        listCancion = new ArrayList<>(manejoArchivos.getLista());
        System.out.println("Estados leídos desde archivo.");
    }
    @Override
    public void guardarArchivo() {
        manejoArchivos.setLista(new ArrayList<>(listCancion));
        manejoArchivos.guardarArchivo();
        System.out.println("Estados guardados en archivo.");
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", duracion='" + duracion + '\'' +
                ", disco=" + disco.getNombre() +
                '}';
    }
}

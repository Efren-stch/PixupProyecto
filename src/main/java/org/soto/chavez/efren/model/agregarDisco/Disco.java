package org.soto.chavez.efren.model.agregarDisco;

import org.soto.chavez.efren.generalUtil.ManejoArchivos;
import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.model.ClaseCatalogo;
import org.soto.chavez.efren.model.registrarUsuario.Municipio;

import java.util.ArrayList;

public class Disco extends ClaseCatalogo {
    private static final long serialVersionUID = 1L;
    private static Integer idIteracion = 1;
    private static ArrayList<Disco> listDisco = new ArrayList<>();
    private static final ManejoArchivos<Disco> manejoArchivos = new ManejoArchivos<>(Disco.class);

    double precio;
    int existencia;
    double descuento;
    String fechaLanzamiento;
    String imagen;

    Disquera disquera;
    Artista artista;
    GeneroMusical generoMusical;

    private static Disco manage;
    private Disco discoEncontrada;

    private Disco() {
        super();
    }
    public static Disco getManage(){
        if (manage == null){
            manage = new Disco();
        }
        return manage;
    }

    public Disco(String nombre, double precio, int existencia, double descuento, String fechaLanzamiento, String imagen, Disquera disquera, Artista artista, GeneroMusical generoMusical) {
        super(idIteracion, nombre);
        this.precio = precio;
        this.existencia = existencia;
        this.descuento = descuento;
        this.fechaLanzamiento = fechaLanzamiento;
        this.imagen = imagen;
        this.disquera = disquera;
        this.artista = artista;
        this.generoMusical = generoMusical;
    }

    public static ArrayList<Disco> getListDisco() {
        if (listDisco == null) {
            listDisco = new ArrayList<>();
        }
        return listDisco;
    }

    @Override
    public void alta() {
        if(Disquera.getListDisquera().isEmpty()){
            System.out.println("No hay Diqueras registradas");
            return;
        }
        if (Artista.getListArtista().isEmpty()){
            System.out.println("No hay Artistas registrados");
            return;
        }
        if (GeneroMusical.getListGeneroMusical().isEmpty()){
            System.out.println("No hay Generos Musicales registrados");
            return;
        }

        if (listDisco == null){
            listDisco = new ArrayList<>();
        }
        String nombreAlta = ReadUtil.read(Salidas.leerNombre);
        double precioAlta = ReadUtil.readDouble("Digita el precio: ");
        int existencia = ReadUtil.readInt("Digita el número de existencias: ");
        double descuento = ReadUtil.readDouble("Digita el descuento: ");
        String fechaLanzamiento = ReadUtil.read("Digita la fecha de lanzamiento: ");
        String imagen = ReadUtil.read("Digita la imagen: ");

        System.out.println("Selecciona el id de la disquera a la que pertenece: ");
        Disquera disqueraLigada = (Disquera) buscarElemento(Disquera.getListDisquera());
        System.out.println("Selecciona el id del Artista al que pertenece: ");
        Artista artistaLigada = (Artista) buscarElemento(Artista.getListArtista());
        System.out.println("Selecciona el id del género musical al que pertenece: ");
        GeneroMusical generoMusicalLigado = (GeneroMusical) buscarElemento(GeneroMusical.getListGeneroMusical());

        listDisco.add(new Disco(nombreAlta, precioAlta, existencia, descuento, fechaLanzamiento, imagen, disqueraLigada, artistaLigada, generoMusicalLigado));
    }
    @Override
    public void baja() {
        realizarBaja(listDisco);
    }
    @Override
    public void modificacion() {
        discoEncontrada = (Disco) buscarElemento(listDisco);
        if(discoEncontrada != null) discoEncontrada.setNombre(ReadUtil.read(Salidas.nuevoNombre));
    }
    @Override
    public void vista(){
        mostrarVista(listDisco);
    }
    @Override
    public void leerArchivo() {
        manejoArchivos.leerArchivo();
        listDisco = new ArrayList<>(manejoArchivos.getLista());
        System.out.println("Estados leídos desde archivo.");
    }
    @Override
    public void guardarArchivo() {
        manejoArchivos.setLista(new ArrayList<>(listDisco));
        manejoArchivos.guardarArchivo();
        System.out.println("Estados guardados en archivo.");
    }

    @Override
    public String toString() {
        return "Disco{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", precio=" + precio +
                ", existencia=" + existencia +
                ", descuento=" + descuento +
                ", fechaLanzamiento='" + fechaLanzamiento + '\'' +
                ", imagen='" + imagen + '\'' +
                ", disquera=" + disquera.getNombre() +
                ", artista=" + artista.getNombre() +
                ", generoMusical=" + generoMusical.getNombre() +
                '}';
    }
}

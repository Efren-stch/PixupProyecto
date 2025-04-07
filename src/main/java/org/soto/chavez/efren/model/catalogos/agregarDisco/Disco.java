package org.soto.chavez.efren.model.catalogos.agregarDisco;

import org.soto.chavez.efren.generalUtil.ManejoArchivos;
import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.generalUtil.sql.implementacion.DiscoJdbcImpl;
import org.soto.chavez.efren.generalUtil.sql.implementacion.GeneroMusicalJdbcImpl;
import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;

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

    public Disco() {
        super();
    }
    public static Disco getManage(){
        if (manage == null){
            manage = new Disco();
        }
        return manage;
    }

    public Disco(String nombre, double precio, int existencia, double descuento, String fechaLanzamiento, String imagen, Disquera disquera, Artista artista, GeneroMusical generoMusical) {
        super(++idIteracion, nombre);
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

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getExistencia() {
        return existencia;
    }
    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }
    public double getDescuento() {
        return descuento;
    }
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }
    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }
    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public void setIdDisquera(int id){
        this.disquera = (Disquera) buscarElemento(Disquera.getListDisquera(), id);
    }
    public int getIdDisquera(){
        return Disquera.getListDisquera().indexOf(disquera);
    }
    public void setIdArtista(int id){
        this.artista = (Artista) buscarElemento(Artista.getListArtista(), id);
    }
    public int getIdArtista(){
        return Artista.getListArtista().indexOf(artista);
    }
    public void setIdGeneroMusical(int id){
        this.generoMusical = (GeneroMusical) buscarElemento(GeneroMusical.getListGeneroMusical(), id);
    }
    public int getIdGeneroMusical(){
        return GeneroMusical.getListGeneroMusical().indexOf(generoMusical) + 1;
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
    public void leerBaseDatos() {
        listDisco = DiscoJdbcImpl.getInstance().findAll();
    }
    @Override
    public void guardarBaseDatos() {
        DiscoJdbcImpl db = DiscoJdbcImpl.getInstance();
        for (Disco e : listDisco) {
            db.guardar(e);
        }
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

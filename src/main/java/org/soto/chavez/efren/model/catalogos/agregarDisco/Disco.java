package org.soto.chavez.efren.model.catalogos.agregarDisco;

import org.soto.chavez.efren.generalUtil.ManejoArchivos;
import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.generalUtil.sql.implementacion.*;
import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;

public class Disco extends ClaseCatalogo {
    private static final long serialVersionUID = 1L;
    private static final ManejoArchivos<Disco> manejoArchivos = new ManejoArchivos<>(Disco.class);
    private static Integer idIteracion = 0;
    private static Disco manage;

    private double precio;
    private int existencia;
    private double descuento;
    private String fechaLanzamiento;
    private String imagen;
    private Disquera disquera;
    private Artista artista;
    private GeneroMusical generoMusical;
    private Disco discoEncontrada;


    public Disco() {
        super();
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

    public static Disco getManage() {
        if (manage == null) {
            manage = new Disco();
        }
        return manage;
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

    public Disquera getDisquera() {
        return disquera;
    }

    public void setDisquera(int id) {
        this.disquera = (Disquera) buscarElemento(DisqueraJdbcImpl.getInstance(), id);
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(int id) {
        this.artista = (Artista) buscarElemento(ArtistaJdbcImpl.getInstance(), id);
    }

    public GeneroMusical getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(int id) {
        this.generoMusical = (GeneroMusical) buscarElemento(GeneroMusicalJdbcImpl.getInstance(), id);
    }

    @Override
    public void alta() {
        if (estaVacia(DisqueraJdbcImpl.getInstance()) ||
            estaVacia(ArtistaJdbcImpl.getInstance()) ||
            estaVacia(GeneroMusicalJdbcImpl.getInstance())
        ) {
            System.out.println("No hay disqueras, artistas o generos registrados para poder proceder");
            return;
        }

        String nombreAlta = ReadUtil.read(Salidas.leerNombre);
        double precioAlta = ReadUtil.readDouble("Digita el precio: ");
        int existenciaAlta = ReadUtil.readInt("Digita el número de existencias: ");
        double descuentoAlta = ReadUtil.readDouble("Digita el descuentoAlta: ");
        String fechaLanzamientoAlta = ReadUtil.read("Digita la fecha de lanzamiento: ");
        String imagenAlta = ReadUtil.read("Digita la imagenAlta: ");

        System.out.println("Selecciona el id de la disquera a la que pertenece: ");
        Disquera disqueraLigada = (Disquera) buscarElemento(DisqueraJdbcImpl.getInstance());
        System.out.println("Selecciona el id del Artista al que pertenece: ");
        Artista artistaLigada = (Artista) buscarElemento(ArtistaJdbcImpl.getInstance());
        System.out.println("Selecciona el id del género musical al que pertenece: ");
        GeneroMusical generoMusicalLigado = (GeneroMusical) buscarElemento(GeneroMusicalJdbcImpl.getInstance());

        DiscoJdbcImpl.getInstance().guardar(new Disco(nombreAlta, precioAlta, existenciaAlta, descuentoAlta, fechaLanzamientoAlta, imagenAlta, disqueraLigada, artistaLigada, generoMusicalLigado));
    }

    @Override
    public void baja() {
        realizarBaja(DiscoJdbcImpl.getInstance());
    }

    @Override
    public void modificacion() {
        discoEncontrada = (Disco) buscarElemento(DiscoJdbcImpl.getInstance());

        if (discoEncontrada != null) {
            discoEncontrada.setNombre(ReadUtil.read(Salidas.nuevoNombre));
            discoEncontrada.setPrecio(ReadUtil.readDouble("Digita el nuevo precio: "));
            discoEncontrada.setExistencia(ReadUtil.readInt("Digita el nuevo número de existencias: "));
            discoEncontrada.setDescuento(ReadUtil.readDouble("Digita el nuevo descuento: "));
            discoEncontrada.setFechaLanzamiento(ReadUtil.read("Digita la nueva fecha de lanzamiento: "));
            discoEncontrada.setImagen(ReadUtil.read("Digita la nueva imagen: "));
            System.out.println("Selecciona el id del nuevo Artista al que pertenece: ");
            discoEncontrada.setArtista( ((Artista)buscarElemento(ArtistaJdbcImpl.getInstance())).getId() );
            System.out.println("Selecciona el id del nuevo Genero Musical al que pertenece: ");
            discoEncontrada.setGeneroMusical( ((GeneroMusical)buscarElemento(GeneroMusicalJdbcImpl.getInstance())).getId() );
            System.out.println("Selecciona el id de la nueva Disquera al que pertenece: ");
            discoEncontrada.setDisquera( ((Disquera)buscarElemento(DisqueraJdbcImpl.getInstance())).getId() );

            DiscoJdbcImpl.getInstance().actualizar(discoEncontrada);
        }
    }


    @Override
    public void vista() {
        mostrarVista(DiscoJdbcImpl.getInstance());
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

    /*
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



     */
}

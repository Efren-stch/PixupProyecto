package org.soto.chavez.efren.model.agregarDisco;
import org.soto.chavez.efren.generalUtil.ManejoArchivos;
import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.model.ClaseCatalogo;
import java.util.ArrayList;

public class Artista extends ClaseCatalogo {
    private static final long serialVersionUID = 1L;
    private static Integer idIteracion = 1;
    private static ArrayList<Artista> listArtista = new ArrayList<>();
    private static final ManejoArchivos<Artista> manejoArchivos = new ManejoArchivos<>(Artista.class);

    private static Artista manage;
    private Artista artistaEncontrado;
    private Artista() {
        super();
    }
    public static Artista getManage(){
        if (manage == null){
            manage = new Artista();
        }
        return manage;
    }
    public Artista(String nombre) {
        super(idIteracion, nombre);
        idIteracion++;
    }
    public static ArrayList<Artista> getListArtista() {
        if (listArtista == null) {
            listArtista = new ArrayList<>();
        }
        return listArtista;
    }
    @Override
    public void alta() {
        if (listArtista == null){
            listArtista = new ArrayList<>();
        }
        String nombreAlta = ReadUtil.read(Salidas.leerNombre);

        listArtista.add(new Artista(nombreAlta));
    }
    @Override
    public void baja() {
        realizarBaja(listArtista);
    }
    @Override
    public void modificacion() {
        artistaEncontrado = (Artista) buscarElemento(listArtista);
        if(artistaEncontrado != null) artistaEncontrado.setNombre(ReadUtil.read(Salidas.nuevoNombre));
    }
    @Override
    public void vista(){
        mostrarVista(listArtista);
    }
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
    public String toString() {
        return "Artista{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }
}

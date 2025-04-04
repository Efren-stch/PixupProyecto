package org.soto.chavez.efren.model.registrarUsuario;
import org.soto.chavez.efren.generalUtil.ManejoArchivos;
import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.model.ClaseCatalogo;

import java.util.ArrayList;
public class Municipio extends ClaseCatalogo {
    private static final long serialVersionUID = 1L;
    private static Integer idIteracion = 1;
    private static ArrayList<Municipio> listMunicipios = new ArrayList<>();
    private static final ManejoArchivos<Municipio> manejoArchivos = new ManejoArchivos<>(Municipio.class);

    private Estado estado;
    private static Municipio manage;
    private Municipio municipioEncontrado;

    private Municipio() {
        super(0);
    }
    public static Municipio getManage() {
        if (manage == null) {
            manage = new Municipio();
        }
        return manage;
    }
    public Municipio(String nombre, Estado estado) {
        super(idIteracion, nombre);
        this.estado = estado;
        idIteracion++;
    }
    public static ArrayList<Municipio> getListMunicipios() {
        if (listMunicipios == null) {
            listMunicipios = new ArrayList<>();
        }
        return listMunicipios;
    }
    @Override
    public void alta() {
        if(Estado.getListEstados().isEmpty()){
            System.out.println("No hay estados registrados");
            return;
        }
        if (listMunicipios == null){
            listMunicipios = new ArrayList<>();
        }
        String nombreAlta = ReadUtil.read(Salidas.leerNombre);
        System.out.println("Selecciona el id del estado al que pertenece: ");
        listMunicipios.add(new Municipio(nombreAlta, (Estado) buscarElemento(Estado.getListEstados())));
    }
    @Override
    public void baja() {
        realizarBaja(listMunicipios);
    }
    @Override
    public void modificacion() {
        municipioEncontrado = (Municipio) buscarElemento(listMunicipios);
        if (municipioEncontrado != null) municipioEncontrado.setNombre(ReadUtil.read(Salidas.nuevoNombre));
    }
    @Override
    public void vista(){
        mostrarVista(listMunicipios);
    }
    public void leerArchivo() {
        manejoArchivos.leerArchivo();
        listMunicipios = new ArrayList<>(manejoArchivos.getLista());  // Sincronizar con la lista interna
        System.out.println("Estados leídos desde archivo.");
    }
    @Override
    public void guardarArchivo() {
        manejoArchivos.setLista(new ArrayList<>(listMunicipios));
        manejoArchivos.guardarArchivo();
        System.out.println("Estados guardados en archivo.");
    }
    @Override
    public String toString() {
        return "Municipio{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", estado=" + estado.getNombre() +
                '}';
    }
}

package org.soto.chavez.efren.model.registrarUsuario;
import org.soto.chavez.efren.generalUtil.ManejoArchivos;
import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.model.ClaseCatalogo;

import java.util.ArrayList;
public class Estado extends ClaseCatalogo {
    private static final long serialVersionUID = 1L;
    private static Integer idIteracion = 1;
    private static ArrayList<Estado> listEstados = new ArrayList<>();
    private static ManejoArchivos<Estado> manejoArchivos = new ManejoArchivos<>(Estado.class);

    private static Estado manage;
    private Estado estadoEncontrado;

    private Estado() {
        super(0);
    }
    public static Estado getManage(){
        if (manage == null){
            manage = new Estado();
        }
        return manage;
    }
    public Estado(String nombre) {
        super(idIteracion, nombre);
        idIteracion++;
    }
    public static ArrayList<Estado> getListEstados() {
        if (listEstados == null) {
            listEstados = new ArrayList<>();
        }
        return listEstados;
    }
    @Override
    public void alta() {
        if (listEstados == null){
            listEstados = new ArrayList<>();
        }
        String nombreAlta = ReadUtil.read(Salidas.leerNombre);

        listEstados.add(new Estado(nombreAlta));
    }
    @Override
    public void baja() {
        realizarBaja(listEstados);
    }
    @Override
    public void modificacion() {
        estadoEncontrado = (Estado) buscarElemento(listEstados);
        if(estadoEncontrado != null) estadoEncontrado.setNombre(ReadUtil.read(Salidas.nuevoNombre));
    }
    @Override
    public void vista(){
        mostrarVista(listEstados);
    }
    @Override
    public void leerArchivo() {
        manejoArchivos.leerArchivo();
        listEstados = new ArrayList<>(manejoArchivos.getLista());
        System.out.println("Estados leídos desde archivo.");
    }
    @Override
    public void guardarArchivo() {
        manejoArchivos.setLista(new ArrayList<>(listEstados));
        manejoArchivos.guardarArchivo();
        System.out.println("Estados guardados en archivo.");
    }
    @Override
    public String toString() {
        return "Estado{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }
}

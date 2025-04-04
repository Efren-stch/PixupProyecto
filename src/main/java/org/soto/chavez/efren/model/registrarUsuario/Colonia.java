package org.soto.chavez.efren.model.registrarUsuario;
import org.soto.chavez.efren.generalUtil.ManejoArchivos;
import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.model.ClaseCatalogo;

import java.util.ArrayList;
public class Colonia extends ClaseCatalogo {
    private static final long serialVersionUID = 1L;
    private static Integer idIteracion = 1;
    private static ArrayList<Colonia> listColonia;
    private static final ManejoArchivos<Colonia> manejoArchivos = new ManejoArchivos<>(Colonia.class);

    private Integer cp;
    private Municipio municipio;
    private static Colonia manage;
    private Colonia coloniaEncontrada;

    private Colonia() {
        super(0);
    }
    public static Colonia getManage(){
        if (manage == null){
            manage = new Colonia();
        }
        return manage;
    }
    private Colonia(String nombre, Municipio municipio, Integer cp) {
        super(idIteracion, nombre);
        this.municipio = municipio;
        this.cp = cp;

        idIteracion++;
    }
    public Integer getCp() {
        return cp;
    }
    public void setCp(Integer cp) {
        this.cp = cp;
    }
    @Override
    public void alta() {
        if(Municipio.getListMunicipios().isEmpty()){
            System.out.println("No hay municipios registrados");
            return;
        }
        if (listColonia == null){
            listColonia = new ArrayList<>();
        }
        String nombreAlta = ReadUtil.read(Salidas.leerNombre);
        Integer cpAlta = ReadUtil.readInt(Salidas.leerCP);

        System.out.println("Selecciona el id del estado al que pertenece: ");
        Municipio municipioLigado = (Municipio) buscarElemento(Municipio.getListMunicipios());

        listColonia.add(new Colonia(nombreAlta, municipioLigado, cpAlta));
    }
    @Override
    public void baja() {
        realizarBaja(listColonia);
    }
    @Override
    public void modificacion() {
        coloniaEncontrada = (Colonia) buscarElemento(listColonia);
        if (coloniaEncontrada != null) {
            coloniaEncontrada.setNombre(ReadUtil.read(Salidas.nuevoNombre));
            coloniaEncontrada.setCp(ReadUtil.readInt(Salidas.leerCP));
        }
    }
    @Override
    public void vista(){
        mostrarVista(listColonia);
    }
    public void leerArchivo() {
        manejoArchivos.leerArchivo();
        listColonia = new ArrayList<>(manejoArchivos.getLista());  // Sincronizar con la lista interna
        System.out.println("Estados leídos desde archivo.");
    }
    @Override
    public void guardarArchivo() {
        manejoArchivos.setLista(new ArrayList<>(listColonia));
        manejoArchivos.guardarArchivo();
        System.out.println("Estados guardados en archivo.");
    }
    @Override
    public String toString() {
        return "Colonia {" +
                "nombre ='" + nombre + '\'' +
                ", id =" + id +
                ", cp =" + cp +
                ", municipio =" + municipio.getNombre() +
                '}';
    }
}

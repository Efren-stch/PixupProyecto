package org.soto.chavez.efren.model.catalogos.registrarUsuario;
import org.soto.chavez.efren.generalUtil.ManejoArchivos;
import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.generalUtil.sql.implementacion.ColoniaJdbcImpl;
import org.soto.chavez.efren.generalUtil.sql.implementacion.MunicipioJdbcImpl;
import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;

import java.util.ArrayList;
public class Colonia extends ClaseCatalogo {
    private static final long serialVersionUID = 1L;
    private static Integer idIteracion = 0;
    private static ArrayList<Colonia> listColonia;
    private static final ManejoArchivos<Colonia> manejoArchivos = new ManejoArchivos<>(Colonia.class);

    private Integer cp;
    private Municipio municipio;
    private static Colonia manage;
    private Colonia coloniaEncontrada;

    public Colonia() {
        super();
    }
    public static Colonia getManage(){
        if (manage == null){
            manage = new Colonia();
        }
        return manage;
    }
    private Colonia(String nombre, Municipio municipio, Integer cp) {
        super(idIteracion++, nombre);
        this.municipio = municipio;
        this.cp = cp;
    }
    public Integer getCp() {
        return cp;
    }
    public void setCp(Integer cp) {
        this.cp = cp;
    }
    public void setIdMunicipio(int id){
        this.municipio = (Municipio) buscarElemento(Municipio.getListMunicipios(), id);
    }
    public int getIdMunicipio(){
        return Municipio.getListMunicipios().indexOf(municipio) + 1;
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
    public void leerBaseDatos() {
        listColonia = ColoniaJdbcImpl.getInstance().findAll();
    }
    @Override
    public void guardarBaseDatos() {
        ColoniaJdbcImpl db = ColoniaJdbcImpl.getInstance();
        for (Colonia e : listColonia) {
            db.guardar(e);
        }
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

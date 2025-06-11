package org.soto.chavez.efren.model.catalogos.agregarDisco;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.soto.chavez.efren.BD.dao.implementacion.CatalogoDaoImpl;
import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table( name = "TBL_ARTISTA" )
public class Artista extends ClaseCatalogo {
    protected static CatalogoDaoImpl<Artista> catalogoDaoImpl = new CatalogoDaoImpl<>(Artista.class);

    private static Artista manage = null;
    private Artista(){

    }
    public static Artista getManage(){
        if(manage == null){
            manage = new Artista();
        }
        return manage;
    }


    @Override
    public void alta() {
        Artista artista = new Artista();
        artista.setNombre(ReadUtil.read(Salidas.leerNombre));

        catalogoDaoImpl.guardar(artista);
    }
    public boolean alta(String nombre){
        Artista artista = new Artista();
        artista.setNombre(nombre);
        catalogoDaoImpl.guardar(artista);
        return true;
    }

    @Override
    public void modificacion() {
        Artista artista = (Artista) buscarCatalogo(catalogoDaoImpl);
        if(artista != null){
            artista.setNombre(ReadUtil.read(Salidas.nuevoNombre));
            catalogoDaoImpl.actualizar(artista);
        }
    }
    public boolean modificacion(Integer id, String nombreNuevo) {
        Artista artista = catalogoDaoImpl.findById(id);
        if(artista != null){
            artista.setNombre(nombreNuevo);
            catalogoDaoImpl.actualizar(artista);
            return  true;
        }
        return false;
    }

    @Override
    public void baja() {
        realizarBaja(Artista.class);
    }
    public boolean baja(Integer id) {
        Artista artista = catalogoDaoImpl.findById(id);
        if(artista != null){
            catalogoDaoImpl.eliminar(artista);
            return true;
        }
        return false;
    }

    @Override
    public boolean vista() {
        System.out.println(realizarVista(catalogoDaoImpl));
        return false;
    }

}
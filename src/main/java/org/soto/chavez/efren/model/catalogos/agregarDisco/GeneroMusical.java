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
@Table( name = "TBL_GENEROMUSICAL" )
public class GeneroMusical extends ClaseCatalogo {
    protected static CatalogoDaoImpl<GeneroMusical> catalogoDaoImpl = new CatalogoDaoImpl<>(GeneroMusical.class);

    private static GeneroMusical manage = null;
    private GeneroMusical(){

    }
    public static GeneroMusical getManage(){
        if(manage == null){
            manage = new GeneroMusical();
        }
        return manage;
    }


    @Override
    public void alta() {
        GeneroMusical generoMusical = new GeneroMusical();
        generoMusical.setNombre(ReadUtil.read(Salidas.leerNombre));

        catalogoDaoImpl.guardar(generoMusical);
    }

    public boolean alta(String nombre) {
        GeneroMusical generoMusical = new GeneroMusical();
        generoMusical.setNombre(nombre);
        catalogoDaoImpl.guardar(generoMusical);
        return true;
    }

    @Override
    public void modificacion() {
        GeneroMusical generoMusical = (GeneroMusical) buscarCatalogo(catalogoDaoImpl);
        if(generoMusical != null){
            generoMusical.setNombre(ReadUtil.read(Salidas.nuevoNombre));
            catalogoDaoImpl.actualizar(generoMusical);
        }
    }

    public boolean modificacion(Integer id, String nombreNuevo) {
        GeneroMusical generoMusical = catalogoDaoImpl.findById(id);
        if (generoMusical != null) {
            generoMusical.setNombre(nombreNuevo);
            catalogoDaoImpl.actualizar(generoMusical);
            return true;
        }
        return false;
    }

    @Override
    public void baja() {
        realizarBaja(GeneroMusical.class);
    }

    public boolean baja(Integer id) {
        GeneroMusical generoMusical = catalogoDaoImpl.findById(id);
        if (generoMusical != null) {
            catalogoDaoImpl.eliminar(generoMusical);
            return true;
        }
        return false;
    }

    @Override
    public boolean vista() {
        System.out.println(realizarVista(catalogoDaoImpl));
        return true;
    }

}

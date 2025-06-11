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
@Table( name = "TBL_DISQUERA" )
public class Disquera extends ClaseCatalogo {
    protected static CatalogoDaoImpl<Disquera> catalogoDaoImpl = new CatalogoDaoImpl<>(Disquera.class);

    private static Disquera manage = null;
    private Disquera(){

    }
    public static Disquera getManage(){
        if(manage == null){
            manage = new Disquera();
        }
        return manage;
    }


    @Override
    public void alta() {
        Disquera disquera = new Disquera();
        disquera.setNombre(ReadUtil.read(Salidas.leerNombre));

        catalogoDaoImpl.guardar(disquera);
    }

    public boolean alta(String nombre) {
        Disquera disquera = new Disquera();
        disquera.setNombre(nombre);
        catalogoDaoImpl.guardar(disquera);
        return true;
    }

    @Override
    public void modificacion() {
        Disquera disquera = (Disquera) buscarCatalogo(catalogoDaoImpl);
        if(disquera != null){
            disquera.setNombre(ReadUtil.read(Salidas.nuevoNombre));
            catalogoDaoImpl.actualizar(disquera);
        }
    }

    public boolean modificacion(Integer id, String nombreNuevo) {
        Disquera disquera = catalogoDaoImpl.findById(id);
        if (disquera != null) {
            disquera.setNombre(nombreNuevo);
            catalogoDaoImpl.actualizar(disquera);
            return true;
        }
        return false;
    }

    @Override
    public void baja() {
        realizarBaja(Disquera.class);
    }

    public boolean baja(Integer id) {
        Disquera disquera = catalogoDaoImpl.findById(id);
        if (disquera != null) {
            catalogoDaoImpl.eliminar(disquera);
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

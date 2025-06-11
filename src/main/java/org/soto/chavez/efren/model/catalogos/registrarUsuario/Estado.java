package org.soto.chavez.efren.model.catalogos.registrarUsuario;

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
@Table( name = "TBL_ESTADO" )
public class Estado extends ClaseCatalogo {
    protected static CatalogoDaoImpl<Estado> catalogoDaoImpl = new CatalogoDaoImpl<>(Estado.class);

    private static Estado manage = null;
    private Estado(){

    }
    public static Estado getManage(){
        if(manage == null){
            manage = new Estado();
        }
        return manage;
    }


    @Override
    public void alta() {
        Estado estado = new Estado();
        estado.setNombre(ReadUtil.read(Salidas.leerNombre));

        catalogoDaoImpl.guardar(estado);
    }

    @Override
    public void modificacion() {
        Estado estado = (Estado) buscarCatalogo(catalogoDaoImpl);
        if(estado != null){
            estado.setNombre(ReadUtil.read(Salidas.nuevoNombre));
            catalogoDaoImpl.actualizar(estado);
        }
    }

    @Override
    public void baja() {
        realizarBaja(Estado.class);
    }

    @Override
    public boolean vista() {
        System.out.println(realizarVista(catalogoDaoImpl));
        return false;
    }
}
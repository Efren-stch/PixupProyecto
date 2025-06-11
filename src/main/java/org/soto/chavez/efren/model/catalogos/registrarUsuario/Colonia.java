package org.soto.chavez.efren.model.catalogos.registrarUsuario;

import lombok.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import org.soto.chavez.efren.BD.dao.implementacion.CatalogoDaoImpl;
import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table( name = "TBL_COLONIA" )
public class Colonia extends ClaseCatalogo {
    @Column( name = "idMunicipio" )
    private Integer idMunicipio;

    protected static CatalogoDaoImpl<Colonia> catalogoDaoImpl = new CatalogoDaoImpl<>(Colonia.class);

    private static Colonia manage = null;
    public static Colonia getManage(){
        if(manage == null){
            manage = new Colonia();
        }
        return manage;
    }


    @Override
    public void alta() {
        Colonia colonia = new Colonia();
        colonia.setNombre(ReadUtil.read(Salidas.leerNombre));

        Municipio municipio = (Municipio) buscarCatalogo(Municipio.catalogoDaoImpl);
        if(municipio != null) colonia.setIdMunicipio(municipio.getId());

        catalogoDaoImpl.guardar(colonia);
    }

    @Override
    public void modificacion() {
        Colonia colonia = (Colonia) buscarCatalogo(catalogoDaoImpl);
        if(colonia != null){
            colonia.setNombre(ReadUtil.read(Salidas.nuevoNombre));

            System.out.println("Nuevo Municipio: ");
            Municipio municipio = (Municipio) buscarCatalogo(Municipio.catalogoDaoImpl);
            if(municipio != null){
                colonia.setIdMunicipio(municipio.getId());
                catalogoDaoImpl.actualizar(colonia);
            }
        }
    }

    @Override
    public void baja() {
        realizarBaja(Colonia.class);
    }

    @Override
    public boolean vista() {
        System.out.println(realizarVista(catalogoDaoImpl));
        return false;
    }
}
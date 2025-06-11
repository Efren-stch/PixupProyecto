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
@Table( name = "TBL_MUNICIPIO" )
public class Municipio extends ClaseCatalogo {
    @Column( name = "idEstado" )
    private Integer idEstado;

    protected static CatalogoDaoImpl<Municipio> catalogoDaoImpl = new CatalogoDaoImpl<>(Municipio.class);

    private static Municipio manage = null;
    public static Municipio getManage(){
        if(manage == null){
            manage = new Municipio();
        }
        return manage;
    }

    @Override
    public void alta() { //AGREGAR VERIFICACIÓN DE QUE EXISTE UN ESTADO Y LO MISMO PARA LOS DEMÁS :3
        Municipio municipio = new Municipio();
        municipio.setNombre(ReadUtil.read(Salidas.leerNombre));

        Estado estado = (Estado) buscarCatalogo(Estado.catalogoDaoImpl);
        if(estado != null) municipio.setIdEstado(estado.getId());

        catalogoDaoImpl.guardar(municipio);
    }

    @Override
    public void modificacion() {
        Municipio municipio = (Municipio) buscarCatalogo(catalogoDaoImpl);
        if(municipio != null){
            municipio.setNombre(ReadUtil.read(Salidas.nuevoNombre));

            System.out.println("Nuevo Estado: ");
            Estado estado = (Estado) buscarCatalogo(Estado.catalogoDaoImpl);
            if(estado != null){
                municipio.setIdEstado(estado.getId());
                catalogoDaoImpl.actualizar(municipio);
            }
        }
    }

    @Override
    public void baja() {
        realizarBaja(Municipio.class);
    }

    @Override
    public boolean vista() {
        System.out.println(realizarVista(catalogoDaoImpl));
        return false;
    }
}
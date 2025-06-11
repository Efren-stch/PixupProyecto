package org.soto.chavez.efren.model.catalogos.agregarDisco;

import lombok.*;
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
@Table(name = "TBL_CANCION")
public class Cancion extends ClaseCatalogo {

    @Column(name = "duracion")
    private Double duracion;

    @Column(name = "idDisco")
    private Integer idDisco;

    protected static CatalogoDaoImpl<Cancion> catalogoDaoImpl = new CatalogoDaoImpl<>(Cancion.class);
    private static Cancion manage = null;

    public static Cancion getManage() {
        if (manage == null) {
            manage = new Cancion();
        }
        return manage;
    }

    @Override
    public void alta() {
        Cancion cancion = new Cancion();
        cancion.setNombre(ReadUtil.read(Salidas.leerNombre));
        cancion.setDuracion(Double.valueOf(ReadUtil.read("Duración (en minutos):")));

        System.out.println("Seleccione el Disco al que pertenece:");
        Disco disco = (Disco) buscarCatalogo(Disco.catalogoDaoImpl);
        if (disco != null) cancion.setIdDisco(disco.getId());

        catalogoDaoImpl.guardar(cancion);
    }

    public boolean alta(String nombre, Double duracion, Integer idDisco) {
        Cancion cancion = new Cancion();
        cancion.setNombre(nombre);
        cancion.setDuracion(duracion);
        cancion.setIdDisco(idDisco);
        catalogoDaoImpl.guardar(cancion);
        return true;
    }

    @Override
    public void modificacion() {
        Cancion cancion = (Cancion) buscarCatalogo(catalogoDaoImpl);
        if (cancion != null) {
            cancion.setNombre(ReadUtil.read(Salidas.nuevoNombre));
            cancion.setDuracion(Double.valueOf(ReadUtil.read("Nueva duración (en minutos):")));

            System.out.println("Seleccione el nuevo Disco:");
            Disco disco = (Disco) buscarCatalogo(Disco.catalogoDaoImpl);
            if (disco != null) cancion.setIdDisco(disco.getId());

            catalogoDaoImpl.actualizar(cancion);
        }
    }

    public boolean modificacion(Integer id, String nombre, Double duracion, Integer idDisco) {
        Cancion cancion = catalogoDaoImpl.findById(id);
        if (cancion != null) {
            cancion.setNombre(nombre);
            cancion.setDuracion(duracion);
            cancion.setIdDisco(idDisco);
            catalogoDaoImpl.actualizar(cancion);
            return true;
        }
        return false;
    }

    @Override
    public void baja() {
        realizarBaja(Cancion.class);
    }

    public boolean baja(Integer id) {
        Cancion cancion = catalogoDaoImpl.findById(id);
        if (cancion != null) {
            catalogoDaoImpl.eliminar(cancion);
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

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
@Table(name = "TBL_DISCO")
public class Disco extends ClaseCatalogo {

    @Column(name = "precio")
    private Double precio;
    @Column(name = "existencia")
    private Integer existencia;
    @Column(name = "descuento")
    private Double descuento;
    @Column(name = "fechaLanzamiento")
    private String fechaLanzamiento;
    @Column(name = "imagen")
    private String imagen;
    @Column(name = "idArtista")
    private Integer idArtista;
    @Column(name = "idDisquera")
    private Integer idDisquera;
    @Column(name = "idGeneroMusical")
    private Integer idGeneroMusical;

    protected static CatalogoDaoImpl<Disco> catalogoDaoImpl = new CatalogoDaoImpl<>(Disco.class);
    private static Disco manage = null;

    public static Disco getManage() {
        if (manage == null) {
            manage = new Disco();
        }
        return manage;
    }

    @Override
    public void alta() {
        Disco disco = new Disco();
        disco.setNombre(ReadUtil.read(Salidas.leerNombre));
        disco.setPrecio(Double.valueOf(ReadUtil.read("Precio:")));
        disco.setExistencia(Integer.valueOf(ReadUtil.read("Existencia:")));
        disco.setDescuento(Double.valueOf(ReadUtil.read("Descuento (%):")));
        disco.setFechaLanzamiento(ReadUtil.read("Fecha de lanzamiento (dd/mm/aaaa):"));
        disco.setImagen(ReadUtil.read("Ruta de la imagen:"));

        // Buscar Artista, Disquera y Género Musical
        Artista artista = (Artista) buscarCatalogo(Artista.catalogoDaoImpl);
        if (artista != null) disco.setIdArtista(artista.getId());

        Disquera disquera = (Disquera) buscarCatalogo(Disquera.catalogoDaoImpl);
        if (disquera != null) disco.setIdDisquera(disquera.getId());

        GeneroMusical genero = (GeneroMusical) buscarCatalogo(GeneroMusical.catalogoDaoImpl);
        if (genero != null) disco.setIdGeneroMusical(genero.getId());

        catalogoDaoImpl.guardar(disco);
    }

    public boolean alta(String nombre, Double precio, Integer existencia, Double descuento, String fechaLanzamiento, String imagen, Integer idArtista, Integer idDisquera, Integer idGeneroMusical) {
        Disco disco = new Disco();
        disco.setNombre(nombre);
        disco.setPrecio(precio);
        disco.setExistencia(existencia);
        disco.setDescuento(descuento);
        disco.setFechaLanzamiento(fechaLanzamiento);
        disco.setImagen(imagen);
        disco.setIdArtista(idArtista);
        disco.setIdDisquera(idDisquera);
        disco.setIdGeneroMusical(idGeneroMusical);
        catalogoDaoImpl.guardar(disco);
        return true;
    }

    @Override
    public void modificacion() {
        Disco disco = (Disco) buscarCatalogo(catalogoDaoImpl);
        if (disco != null) {
            disco.setNombre(ReadUtil.read(Salidas.nuevoNombre));
            disco.setPrecio(Double.valueOf(ReadUtil.read("Nuevo precio:")));
            disco.setExistencia(Integer.valueOf(ReadUtil.read("Nueva existencia:")));
            disco.setDescuento(Double.valueOf(ReadUtil.read("Nuevo descuento (%):")));
            disco.setFechaLanzamiento(ReadUtil.read("Nueva fecha de lanzamiento (dd/mm/aaaa):"));
            disco.setImagen(ReadUtil.read("Nueva ruta de la imagen:"));

            System.out.println("Nuevo Artista:");
            Artista artista = (Artista) buscarCatalogo(Artista.catalogoDaoImpl);
            if (artista != null) disco.setIdArtista(artista.getId());

            System.out.println("Nueva Disquera:");
            Disquera disquera = (Disquera) buscarCatalogo(Disquera.catalogoDaoImpl);
            if (disquera != null) disco.setIdDisquera(disquera.getId());

            System.out.println("Nuevo Género Musical:");
            GeneroMusical genero = (GeneroMusical) buscarCatalogo(GeneroMusical.catalogoDaoImpl);
            if (genero != null) disco.setIdGeneroMusical(genero.getId());

            catalogoDaoImpl.actualizar(disco);
        }
    }

    public boolean modificacion(Integer id, String nombre, Double precio, Integer existencia, Double descuento, String fechaLanzamiento, String imagen, Integer idArtista, Integer idDisquera, Integer idGeneroMusical) {
        Disco disco = catalogoDaoImpl.findById(id);
        if (disco != null) {
            disco.setNombre(nombre);
            disco.setPrecio(precio);
            disco.setExistencia(existencia);
            disco.setDescuento(descuento);
            disco.setFechaLanzamiento(fechaLanzamiento);
            disco.setImagen(imagen);
            disco.setIdArtista(idArtista);
            disco.setIdDisquera(idDisquera);
            disco.setIdGeneroMusical(idGeneroMusical);
            catalogoDaoImpl.actualizar(disco);
            return true;
        }
        return false;
    }

    @Override
    public void baja() {
        realizarBaja(Disco.class);
    }

    public boolean baja(Integer id) {
        Disco disco = catalogoDaoImpl.findById(id);
        if (disco != null) {
            catalogoDaoImpl.eliminar(disco);
            return true;
        }
        return false;
    }

    @Override
    public boolean vista() {
        System.out.println(realizarVista(catalogoDaoImpl));
        return true;
    }

    @Override
    public String toString() {
        return "Disco{" +
                "precio=" + precio +
                ", existencia=" + existencia +
                ", descuento=" + descuento +
                ", fechaLanzamiento='" + fechaLanzamiento + '\'' +
                ", imagen='" + imagen + '\'' +
                ", Artista=" + Artista.catalogoDaoImpl.findById(idArtista) +
                ", Disquera=" + Disquera.catalogoDaoImpl.findById(idDisquera)  +
                ", GeneroMusical=" + GeneroMusical.catalogoDaoImpl.findById(idGeneroMusical) +
                '}';
    }
}

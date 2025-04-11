package org.soto.chavez.efren.generalUtil.sql.implementacion;

import org.junit.jupiter.api.Test;
import org.soto.chavez.efren.model.catalogos.agregarDisco.Artista;
import org.soto.chavez.efren.model.catalogos.agregarDisco.Disco;

import static org.junit.jupiter.api.Assertions.*;

class DiscoJdbcImplTest {

    @Test
    void getInstance() {
    }

    @Test
    void findAll() {
    }

    @Test
    void guardar() {
        DiscoJdbcImpl discoJdbc = DiscoJdbcImpl.getInstance();
        Disco disco = new Disco();
        disco.setNombre("Disco 1");
        disco.setDescuento(.15);
        disco.setPrecio(12.55);
        disco.setExistencia(500);
        disco.setFechaLanzamiento("Hoy");
        disco.setImagen("img1");
        disco.setArtista(1);
        disco.setDisquera(1);
        disco.setGeneroMusical(1);
        
        assertTrue(discoJdbc.guardar(disco));
    }

    @Test
    void actualizar() {
    }

    @Test
    void eliminar() {
    }
}
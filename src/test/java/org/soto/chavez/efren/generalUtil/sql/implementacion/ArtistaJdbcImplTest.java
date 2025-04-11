package org.soto.chavez.efren.generalUtil.sql.implementacion;

import org.junit.jupiter.api.Test;
import org.soto.chavez.efren.model.catalogos.agregarDisco.Artista;

import static org.junit.jupiter.api.Assertions.*;

class ArtistaJdbcImplTest {

    @Test
    void getInstance() {
    }

    @Test
    void findAll() {
    }

    @Test
    void guardar() {
        ArtistaJdbcImpl artistaJdbc = ArtistaJdbcImpl.getInstance();
        Artista artista = new Artista();
        artista.setNombre("Artista1");
        assertTrue(artistaJdbc.guardar(artista));
    }

    @Test
    void actualizar() {
    }

    @Test
    void eliminar() {
    }
}
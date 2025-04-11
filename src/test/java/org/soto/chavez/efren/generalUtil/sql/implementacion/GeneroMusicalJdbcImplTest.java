package org.soto.chavez.efren.generalUtil.sql.implementacion;

import org.junit.jupiter.api.Test;
import org.soto.chavez.efren.model.catalogos.agregarDisco.GeneroMusical;

import static org.junit.jupiter.api.Assertions.*;

class GeneroMusicalJdbcImplTest {

    @Test
    void getInstance() {
    }

    @Test
    void findAll() {
    }

    @Test
    void guardar() {
        GeneroMusicalJdbcImpl generoMusicalJdbc = GeneroMusicalJdbcImpl.getInstance();
        GeneroMusical generoMusical = new GeneroMusical();
        generoMusical.setNombre("GeneroMusical 1");
        assertTrue(generoMusicalJdbc.guardar(generoMusical));
    }

    @Test
    void actualizar() {
    }

    @Test
    void eliminar() {
    }
}
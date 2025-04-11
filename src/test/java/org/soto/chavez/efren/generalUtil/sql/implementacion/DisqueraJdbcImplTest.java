package org.soto.chavez.efren.generalUtil.sql.implementacion;

import org.junit.jupiter.api.Test;
import org.soto.chavez.efren.model.catalogos.agregarDisco.Artista;
import org.soto.chavez.efren.model.catalogos.agregarDisco.Disquera;

import static org.junit.jupiter.api.Assertions.*;

class DisqueraJdbcImplTest {

    @Test
    void getInstance() {
    }

    @Test
    void findAll() {
    }

    @Test
    void guardar() {
        DisqueraJdbcImpl disqueraJdbc = DisqueraJdbcImpl.getInstance();
        Disquera disquera = new Disquera();
        disquera.setNombre("Disquera 1");
        assertTrue(disqueraJdbc.guardar(disquera));
    }

    @Test
    void actualizar() {
    }

    @Test
    void eliminar() {
    }
}
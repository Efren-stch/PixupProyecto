package org.soto.chavez.efren.generalUtil.sql.implementacion;

import org.junit.jupiter.api.Test;
import org.soto.chavez.efren.model.catalogos.agregarDisco.Cancion;

import static org.junit.jupiter.api.Assertions.*;

class CancionJdbcImplTest {

    @Test
    void getInstance() {
    }

    @Test
    void findAll() {
    }

    @Test
    void guardar() {
        CancionJdbcImpl cancionJdbc = CancionJdbcImpl.getInstance();
        Cancion cancion = new Cancion();
        cancion.setNombre("Cancion 1");
        cancion.setDuracion(5.25);
        cancion.setDisco(1);
        assertTrue(cancionJdbc.guardar(cancion));
    }

    @Test
    void actualizar() {
    }

    @Test
    void eliminar() {
    }
}
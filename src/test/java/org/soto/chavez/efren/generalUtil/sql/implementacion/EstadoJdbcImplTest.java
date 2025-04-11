package org.soto.chavez.efren.generalUtil.sql.implementacion;

import org.junit.jupiter.api.Test;
import org.soto.chavez.efren.model.catalogos.registrarUsuario.Estado;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EstadoJdbcImplTest {

    @Test
    void findAll() {
        EstadoJdbcImpl estadoJdbc = EstadoJdbcImpl.getInstance();
        Estado estado = new Estado();
        ArrayList list = estadoJdbc.findAll();

        assertNotNull( list );
        assertTrue( list.size() > 0);
        list.stream().forEach( System.out::println );
    }

    @Test
    void guardar() {
        EstadoJdbcImpl estadoJdbc = EstadoJdbcImpl.getInstance();
        Estado estado = new Estado();
        estado.setNombre("Pepe");

        assertTrue( estadoJdbc.guardar(estado) );
    }

    @Test
    void actualizar() {
        EstadoJdbcImpl estadoJdbcImpl = EstadoJdbcImpl.getInstance();
        Estado estado = new Estado();

        estado.setNombre("Ciudad de México");
        estado.setId(1);

        assertTrue( estadoJdbcImpl.actualizar( estado ));
    }

    @Test
    void eliminar() {
        EstadoJdbcImpl estadoJdbcImpl = EstadoJdbcImpl.getInstance();
        Estado estado = new Estado();

        estado.setId(2);

        assertTrue( estadoJdbcImpl.eliminar( 1 ) );
    }
}
package org.soto.chavez.efren.model.catalogos.agregarDisco;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneroMusicalTest {

    GeneroMusical generoMusicalManage = GeneroMusical.getManage();

    @Test
    void alta() {
        String nombre = "GeneroMusical Prueba 1";
        boolean resultado = generoMusicalManage.alta(nombre);
        assertTrue(resultado, "El alta del generoMusical debe ser exitosa");

        GeneroMusical comprobacion = GeneroMusical.catalogoDaoImpl.findAll().stream()
                .filter(g -> g.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);

        assertNotNull(comprobacion, "El generoMusical guardado no debe ser nulo");
        assertEquals(nombre, comprobacion.getNombre(), "El nombre del generoMusical guardado debe coincidir");
    }

    @Test
    void modificacion() {
        String nombreNuevo = "GeneroMusical 1.1";
        boolean resultado = generoMusicalManage.modificacion(1, nombreNuevo);
        assertTrue(resultado, "La modificación del generoMusical debe ser exitosa");

        GeneroMusical comprobacion = GeneroMusical.catalogoDaoImpl.findById(1);
        assertNotNull(comprobacion, "El generoMusical modificado no debe ser nulo");
        assertEquals(nombreNuevo, comprobacion.getNombre(), "El nombre del generoMusical no fue modificado correctamente");
    }

    @Test
    void baja() {
        boolean resultado = generoMusicalManage.baja(1);
        assertTrue(resultado, "La baja del generoMusical debe ser exitosa");

        GeneroMusical generoMusicalEliminado = GeneroMusical.catalogoDaoImpl.findById(1);
        assertNull(generoMusicalEliminado, "El generoMusical eliminado debe ser nulo");
    }

    @Test
    void vista() {
        assertDoesNotThrow(() -> generoMusicalManage.vista(), "El método vista() no funciona bien");
    }
}

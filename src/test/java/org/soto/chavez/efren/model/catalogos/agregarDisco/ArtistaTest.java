package org.soto.chavez.efren.model.catalogos.agregarDisco;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtistaTest {
    Artista artistaManage = Artista.getManage();

    @Test
    void alta() {
        String nombre = "Artista Prueba 1";
        boolean resultado = artistaManage.alta(nombre);
        assertTrue(resultado, "El alta del artista debe ser exitosa");

        Artista comprobacion = Artista.catalogoDaoImpl.findAll().stream()
                .filter(a -> a.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);

        assertNotNull(comprobacion, "El artista guardado no debe ser nulo");
        assertEquals(nombre, comprobacion.getNombre(), "El nombre del artista guardado debe coincidir");
    }

    @Test
    void modificacion() {
        String nombreNuevo = "Artista 1.1";
        boolean resultado = artistaManage.modificacion(1, nombreNuevo);
        assertTrue(resultado, "La modificación del artista debe ser exitosa");

        Artista comprobacion = Artista.catalogoDaoImpl.findById(1);
        assertNotNull(comprobacion, "El artista modificado no debe ser nulo");
        assertEquals(nombreNuevo, comprobacion.getNombre(), "El nombre del artista no fue modificado correctamente");
    }

    @Test
    void baja() {
        boolean resultado = artistaManage.baja(1);
        assertTrue(resultado, "La baja del artista debe ser exitosa");

        Artista artistaEliminado = Artista.catalogoDaoImpl.findById(1);
        assertNull(artistaEliminado, "El artista eliminado debe ser nulo");
    }

    @Test
    void vista() {
        assertDoesNotThrow(() -> artistaManage.vista(), "El método vista() no funciona bien");
    }
}
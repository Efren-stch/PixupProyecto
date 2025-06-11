package org.soto.chavez.efren.model.catalogos.agregarDisco;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisqueraTest {

    Disquera disqueraManage = Disquera.getManage();

    @Test
    void alta() {
        String nombre = "Disquera Prueba 1";
        boolean resultado = disqueraManage.alta(nombre);
        assertTrue(resultado, "El alta de la disquera debe ser exitosa");

        Disquera comprobacion = Disquera.catalogoDaoImpl.findAll().stream()
                .filter(d -> d.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);

        assertNotNull(comprobacion, "La disquera guardada no debe ser nula");
        assertEquals(nombre, comprobacion.getNombre(), "El nombre de la disquera guardada debe coincidir");
    }

    @Test
    void modificacion() {
        String nombreNuevo = "Disquera 1.1";
        boolean resultado = disqueraManage.modificacion(1, nombreNuevo);
        assertTrue(resultado, "La modificación de la disquera debe ser exitosa");

        Disquera comprobacion = Disquera.catalogoDaoImpl.findById(1);
        assertNotNull(comprobacion, "La disquera modificada no debe ser nula");
        assertEquals(nombreNuevo, comprobacion.getNombre(), "El nombre de la disquera no fue modificado correctamente");
    }

    @Test
    void baja() {
        boolean resultado = disqueraManage.baja(1);
        assertTrue(resultado, "La baja de la disquera debe ser exitosa");

        Disquera disqueraEliminada = Disquera.catalogoDaoImpl.findById(1);
        assertNull(disqueraEliminada, "La disquera eliminada debe ser nula");
    }

    @Test
    void vista() {
        assertDoesNotThrow(() -> disqueraManage.vista(), "El método vista() no funciona bien");
    }
}

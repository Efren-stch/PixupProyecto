package org.soto.chavez.efren.model.catalogos.agregarDisco;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CancionTest {

    Cancion cancionManage = Cancion.getManage();

    @Test
    void alta() {
        String nombre = "Cancion Prueba 1";
        Double duracion = 3.5;
        Integer idDisco = 2;

        boolean resultado = cancionManage.alta(nombre, duracion, idDisco);
        assertTrue(resultado, "El alta de la cancion debe ser exitosa");

        Cancion comprobacion = Cancion.catalogoDaoImpl.findAll().stream()
                .filter(c -> c.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);

        assertNotNull(comprobacion, "La cancion guardada no debe ser nula");
        assertEquals(nombre, comprobacion.getNombre(), "El nombre de la cancion guardada debe coincidir");
        assertEquals(duracion, comprobacion.getDuracion(), "La duración de la cancion guardada debe coincidir");
        assertEquals(idDisco, comprobacion.getIdDisco(), "El idDisco de la cancion guardada debe coincidir");
    }

    @Test
    void modificacion() {
        String nombreNuevo = "Cancion 1.1";
        Double duracionNueva = 4.0;
        Integer idDiscoNuevo = 2;

        boolean resultado = cancionManage.modificacion(2, nombreNuevo, duracionNueva, idDiscoNuevo);
        assertTrue(resultado, "La modificación de la cancion debe ser exitosa");

        Cancion comprobacion = Cancion.catalogoDaoImpl.findById(2);
        assertNotNull(comprobacion, "La cancion modificada no debe ser nula");
        assertEquals(nombreNuevo, comprobacion.getNombre(), "El nombre de la cancion no fue modificado correctamente");
        assertEquals(duracionNueva, comprobacion.getDuracion(), "La duración de la cancion no fue modificada correctamente");
        assertEquals(idDiscoNuevo, comprobacion.getIdDisco(), "El idDisco de la cancion no fue modificado correctamente");
    }

    @Test
    void baja() {
        boolean resultado = cancionManage.baja(2);
        assertTrue(resultado, "La baja de la cancion debe ser exitosa");

        Cancion cancionEliminada = Cancion.catalogoDaoImpl.findById(2);
        assertNull(cancionEliminada, "La cancion eliminada debe ser nula");
    }

    @Test
    void vista() {
        assertDoesNotThrow(() -> cancionManage.vista(), "El método vista() no funciona bien");
    }
}

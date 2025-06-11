package org.soto.chavez.efren.model.catalogos.agregarDisco;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscoTest {

    Disco discoManage = Disco.getManage();

    @Test
    void alta() {
        String nombre = "Disco Prueba 1";
        Double precio = 19.99;
        Integer existencia = 100;
        Double descuento = 0.1;
        String fechaLanzamiento = "01/01/2023";
        String imagen = "ruta/imagen.jpg";
        Integer idArtista = 3;
        Integer idDisquera = 3;
        Integer idGeneroMusical = 3;

        boolean resultado = discoManage.alta(nombre, precio, existencia, descuento, fechaLanzamiento, imagen, idArtista, idDisquera, idGeneroMusical);
        assertTrue(resultado, "El alta del disco debe ser exitosa");

        Disco comprobacion = Disco.catalogoDaoImpl.findAll().stream()
                .filter(d -> d.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);

        assertNotNull(comprobacion, "El disco guardado no debe ser nulo");
        assertEquals(nombre, comprobacion.getNombre(), "El nombre del disco guardado debe coincidir");
        assertEquals(precio, comprobacion.getPrecio(), "El precio del disco guardado debe coincidir");
        assertEquals(existencia, comprobacion.getExistencia(), "La existencia del disco guardado debe coincidir");
        assertEquals(descuento, comprobacion.getDescuento(), "El descuento del disco guardado debe coincidir");
        assertEquals(fechaLanzamiento, comprobacion.getFechaLanzamiento(), "La fecha de lanzamiento del disco guardado debe coincidir");
        assertEquals(imagen, comprobacion.getImagen(), "La imagen del disco guardado debe coincidir");
        assertEquals(idArtista, comprobacion.getIdArtista(), "El idArtista del disco guardado debe coincidir");
        assertEquals(idDisquera, comprobacion.getIdDisquera(), "El idDisquera del disco guardado debe coincidir");
        assertEquals(idGeneroMusical, comprobacion.getIdGeneroMusical(), "El idGeneroMusical del disco guardado debe coincidir");
    }

    @Test
    void modificacion() {
        String nombreNuevo = "Disco 1.1";
        Double precioNuevo = 24.99;
        Integer existenciaNueva = 50;
        Double descuentoNuevo = 0.2;
        String fechaLanzamientoNueva = "01/02/2023";
        String imagenNueva = "nueva/ruta/imagen.jpg";
        Integer idArtistaNuevo = 3;
        Integer idDisqueraNuevo = 3;
        Integer idGeneroMusicalNuevo = 3;

        boolean resultado = discoManage.modificacion(1, nombreNuevo, precioNuevo, existenciaNueva, descuentoNuevo, fechaLanzamientoNueva, imagenNueva, idArtistaNuevo, idDisqueraNuevo, idGeneroMusicalNuevo);
        assertTrue(resultado, "La modificación del disco debe ser exitosa");

        Disco comprobacion = Disco.catalogoDaoImpl.findById(1);
        assertNotNull(comprobacion, "El disco modificado no debe ser nulo");
        assertEquals(nombreNuevo, comprobacion.getNombre(), "El nombre del disco no fue modificado correctamente");
        assertEquals(precioNuevo, comprobacion.getPrecio(), "El precio del disco no fue modificado correctamente");
        assertEquals(existenciaNueva, comprobacion.getExistencia(), "La existencia del disco no fue modificada correctamente");
        assertEquals(descuentoNuevo, comprobacion.getDescuento(), "El descuento del disco no fue modificado correctamente");
        assertEquals(fechaLanzamientoNueva, comprobacion.getFechaLanzamiento(), "La fecha de lanzamiento del disco no fue modificada correctamente");
        assertEquals(imagenNueva, comprobacion.getImagen(), "La imagen del disco no fue modificada correctamente");
        assertEquals(idArtistaNuevo, comprobacion.getIdArtista(), "El idArtista del disco no fue modificado correctamente");
        assertEquals(idDisqueraNuevo, comprobacion.getIdDisquera(), "El idDisquera del disco no fue modificado correctamente");
        assertEquals(idGeneroMusicalNuevo, comprobacion.getIdGeneroMusical(), "El idGeneroMusical del disco no fue modificado correctamente");
    }

    @Test
    void baja() {
        boolean resultado = discoManage.baja(1);
        assertTrue(resultado, "La baja del disco debe ser exitosa");

        Disco discoEliminado = Disco.catalogoDaoImpl.findById(1);
        assertNull(discoEliminado, "El disco eliminado debe ser nulo");
    }

    @Test
    void vista() {
        assertDoesNotThrow(() -> discoManage.vista(), "El método vista() no funciona bien");
    }
}







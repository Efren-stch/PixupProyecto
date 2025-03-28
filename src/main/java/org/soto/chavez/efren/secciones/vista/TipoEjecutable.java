package org.soto.chavez.efren.secciones.vista;

import org.soto.chavez.efren.secciones.Ejecutable;
import org.soto.chavez.efren.secciones.vista.consola.Consola;
import org.soto.chavez.efren.secciones.vista.ventana.Ventana;

public enum TipoEjecutable {
    CONSOLA( 1, Consola.getInstance() ),
    VENTANA( 2, Ventana.getInstance() ),
    SALIR( 3, null ),
    OPCION_ERRONEA( 4, null );

    private Integer id;
    private Ejecutable ejecutable;

    TipoEjecutable(Integer id, Ejecutable ejecutable) {
        this.id = id;
        this.ejecutable = ejecutable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ejecutable getEjecutable() {
        return ejecutable;
    }

    public void setEjecutable(Ejecutable ejecutable) {
        this.ejecutable = ejecutable;
    }

    public static TipoEjecutable getTipoEjecutableById ( Integer opcion ) {
        switch (opcion) {
            case 1:
                return CONSOLA;
            case 2:
                return VENTANA;
            case 3:
                return SALIR;
            default:
                return OPCION_ERRONEA;

        }
        /*return switch ( opcion ) {
            case 1 -> CONSOLA;
            case 2 -> VENTANA;
            case 3 -> SALIR;
            default -> OPCION_ERRONEA;
        };*/
    }
}

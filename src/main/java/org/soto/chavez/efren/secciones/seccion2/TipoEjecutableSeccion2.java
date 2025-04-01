package org.soto.chavez.efren.secciones.seccion2;

import org.soto.chavez.efren.secciones.Ejecutable;
import org.soto.chavez.efren.secciones.seccion2.catalogos.Catalogos;

public enum TipoEjecutableSeccion2 {
    CATALOGO( 1, Catalogos.getInstance() ),
    PENDIENTE( 2, null ),
    SALIR( 3, null ),
    OPCION_ERRONEA( 4, null );

    private Integer id;
    private Ejecutable ejecutable;

    TipoEjecutableSeccion2(Integer id, Ejecutable ejecutable) {
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

    public static TipoEjecutableSeccion2 getTipoEjecutableById ( Integer opcion ) {
        return switch ( opcion ) {
            case 1 -> CATALOGO;
            case 2 -> PENDIENTE;
            case 3 -> SALIR;
            default -> OPCION_ERRONEA;
        };
    }
}

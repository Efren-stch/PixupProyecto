package org.soto.chavez.efren.secciones.seccionABM;

import org.soto.chavez.efren.lugares.Lugar;

public enum AbmEnum {
    ALTA(1),
    BAJA(2),
    MODIFICACION(3),
    VISTA(4),
    SALIR(5),
    OPCION_ERRONEA(6);

    private Integer tipo;

    AbmEnum(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public static AbmEnum getAbmByTipo ( Integer tipo ) {
        return switch (tipo) {
            case 1 -> ALTA;
            case 2 -> BAJA;
            case 3 -> VISTA;
            case 4 -> MODIFICACION;
            case 5 -> SALIR;
            default -> OPCION_ERRONEA;
        };
    }

}

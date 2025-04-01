package org.soto.chavez.efren.secciones.seccionABM;

import org.soto.chavez.efren.lugares.Lugar;

public enum AbmEnum {
    ALTA(1),
    BAJA(2),
    MODIFICACION(3),
    VISTA(4),
    GUARDAR_ARCHIVO(5),
    LEER_ARCHIVO(6),
    SALIR(7),
    OPCION_ERRONEA(8);

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
            case 5 -> GUARDAR_ARCHIVO;
            case 6 -> LEER_ARCHIVO;
            case 7 -> SALIR;
            default -> OPCION_ERRONEA;
        };
    }

}

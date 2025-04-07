package org.soto.chavez.efren.model.secciones.enums;

public enum AbmEnum {
    ALTA(1),
    BAJA(2),
    VISTA(3),
    MODIFICACION(4),
    GUARDAR_ARCHIVO(5),
    LEER_ARCHIVO(6),
    GUARDAR_BD(7),
    LEER_BD(8),
    SALIR(9),
    OPCION_ERRONEA(10);

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
            case 7 -> GUARDAR_BD;
            case 8 -> LEER_BD;
            case 9 -> SALIR;
            default -> OPCION_ERRONEA;
        };
    }

}

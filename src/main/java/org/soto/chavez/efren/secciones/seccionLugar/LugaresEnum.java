package org.soto.chavez.efren.secciones.seccionLugar;

import org.soto.chavez.efren.lugares.Colonia;
import org.soto.chavez.efren.lugares.Estado;
import org.soto.chavez.efren.lugares.Lugar;
import org.soto.chavez.efren.lugares.Municipio;

public enum LugaresEnum {
    ESTADO(1, Estado.getManage()),
    MUNICIPIO(2, Municipio.getManage()),
    COLONIA(3, Colonia.getManage()),
    SALIR(4, null),
    OPCION_ERRONEA(5, null);

    private Integer tipo;
    private Lugar lugar;

    LugaresEnum(Integer tipo, Lugar lugar) {
        this.tipo = tipo;
        this.lugar = lugar;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public static LugaresEnum getLugarBytipo(Integer tipo){
        return switch (tipo) {
            case 1 -> ESTADO;
            case 2 -> MUNICIPIO;
            case 3 -> COLONIA;
            case 4 -> SALIR;
            default -> OPCION_ERRONEA;
        };
    }
}

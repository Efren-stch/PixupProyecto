package org.soto.chavez.efren.model.secciones.enums;

import lombok.Getter;
import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;
import org.soto.chavez.efren.model.catalogos.agregarDisco.Artista;
import org.soto.chavez.efren.model.catalogos.agregarDisco.Cancion;
import org.soto.chavez.efren.model.catalogos.agregarDisco.Disco;
import org.soto.chavez.efren.model.catalogos.agregarDisco.Disquera;
import org.soto.chavez.efren.model.catalogos.agregarDisco.GeneroMusical;
import org.soto.chavez.efren.model.catalogos.registrarUsuario.Colonia;
import org.soto.chavez.efren.model.catalogos.registrarUsuario.Estado;
import org.soto.chavez.efren.model.catalogos.registrarUsuario.Municipio;

public enum TipoCatalogoEnum {
    ESTADO(1, Estado.getManage()),
    MUNICIPIO(2, Municipio.getManage()),
    COLONIA(3, Colonia.getManage()),
    ARTISTA(4, Artista.getManage()),
    DISQUERA(5, Disquera.getManage()),
    GENERO_MUSICAL(6, GeneroMusical.getManage()),
    CANCION(7, Cancion.getManage()),
    DISCO(8, Disco.getManage()),
    SALIR(9, null),
    OPCION_ERRONEA(-1, null); 

    @Getter
    private final int tipo;
    private final ClaseCatalogo claseCatalogo;

    TipoCatalogoEnum(int tipo, ClaseCatalogo claseCatalogo) {
        this.tipo = tipo;
        this.claseCatalogo = claseCatalogo;
    }

    public ClaseCatalogo getCatalogo() {
        return claseCatalogo;
    }
    public static TipoCatalogoEnum getCatalogoByTipo(int tipo) {
        for (TipoCatalogoEnum tipoCatalogoEnum : values()) {
            if (tipoCatalogoEnum.tipo == tipo) {
                return tipoCatalogoEnum;
            }
        }
        return OPCION_ERRONEA;
    }
}
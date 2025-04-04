package org.soto.chavez.efren.model.secciones.enums;

import org.soto.chavez.efren.model.ClaseCatalogo;
import org.soto.chavez.efren.model.agregarDisco.Artista;
import org.soto.chavez.efren.model.agregarDisco.Cancion;
import org.soto.chavez.efren.model.agregarDisco.Disco;
import org.soto.chavez.efren.model.agregarDisco.Disquera;
import org.soto.chavez.efren.model.agregarDisco.GeneroMusical;
import org.soto.chavez.efren.model.registrarUsuario.Colonia;
import org.soto.chavez.efren.model.registrarUsuario.Estado;
import org.soto.chavez.efren.model.registrarUsuario.Municipio;

public enum CatalogosEnum {
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

    private final int tipo;
    private final ClaseCatalogo claseCatalogo;

    CatalogosEnum(int tipo, ClaseCatalogo claseCatalogo) {
        this.tipo = tipo;
        this.claseCatalogo = claseCatalogo;
    }
    public int getTipo() {
        return tipo;
    }
    public ClaseCatalogo getCatalogo() {
        return claseCatalogo;
    }
    public static CatalogosEnum getCatalogoByTipo(int tipo) {
        for (CatalogosEnum catalogosEnum : values()) {
            if (catalogosEnum.tipo == tipo) {
                return catalogosEnum;
            }
        }
        return OPCION_ERRONEA;
    }
}
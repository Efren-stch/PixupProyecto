package org.soto.chavez.efren.model.secciones.enums;

import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;
import org.soto.chavez.efren.model.catalogos.agregarDisco.Artista;
import org.soto.chavez.efren.model.catalogos.agregarDisco.Cancion;
import org.soto.chavez.efren.model.catalogos.agregarDisco.Disco;
import org.soto.chavez.efren.model.catalogos.agregarDisco.Disquera;
import org.soto.chavez.efren.model.catalogos.agregarDisco.GeneroMusical;
import org.soto.chavez.efren.model.catalogos.registrarUsuario.Colonia;
import org.soto.chavez.efren.model.catalogos.registrarUsuario.Estado;
import org.soto.chavez.efren.model.catalogos.registrarUsuario.Municipio;

public enum OperacionesCatalogosEnum {
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

    OperacionesCatalogosEnum(int tipo, ClaseCatalogo claseCatalogo) {
        this.tipo = tipo;
        this.claseCatalogo = claseCatalogo;
    }
    public int getTipo() {
        return tipo;
    }
    public ClaseCatalogo getCatalogo() {
        return claseCatalogo;
    }
    public static OperacionesCatalogosEnum getCatalogoByTipo(int tipo) {
        for (OperacionesCatalogosEnum operacionesCatalogosEnum : values()) {
            if (operacionesCatalogosEnum.tipo == tipo) {
                return operacionesCatalogosEnum;
            }
        }
        return OPCION_ERRONEA;
    }
}
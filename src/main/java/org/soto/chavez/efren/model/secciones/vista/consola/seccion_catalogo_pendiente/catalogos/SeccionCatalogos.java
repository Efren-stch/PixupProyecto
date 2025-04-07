package org.soto.chavez.efren.model.secciones.vista.consola.seccion_catalogo_pendiente.catalogos;

import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;
import org.soto.chavez.efren.model.secciones.Ejecutable;
import org.soto.chavez.efren.model.secciones.enums.AbmEnum;
import org.soto.chavez.efren.model.secciones.enums.OperacionesCatalogosEnum;

public class SeccionCatalogos implements Ejecutable {
    private static SeccionCatalogos catalogos;

    private SeccionCatalogos() {
    }

    public static SeccionCatalogos getInstance() {
        if (catalogos == null) {
            catalogos = new SeccionCatalogos();
        }
        return catalogos;
    }

    @Override
    public void run() {
        int opcion;
        OperacionesCatalogosEnum operacionesCatalogosEnum;
        ClaseCatalogo claseCatalogo;

        while (true) {
            System.out.println(Salidas.menuUbicacion);
            opcion = ReadUtil.readInt(Salidas.seleccionarOpcion, 1, 9); // actualizado para cubrir nuevas opciones

            operacionesCatalogosEnum = OperacionesCatalogosEnum.getCatalogoByTipo(opcion);

            if (OperacionesCatalogosEnum.SALIR.equals(operacionesCatalogosEnum)) {
                return;
            } else if (OperacionesCatalogosEnum.OPCION_ERRONEA.equals(operacionesCatalogosEnum)) {
                Salidas.opcionInvalida();
                continue;
            }

            claseCatalogo = operacionesCatalogosEnum.getCatalogo();

            if (claseCatalogo != null) {
                boolean continuarAbm = true;
                while (continuarAbm) {
                    System.out.println(Salidas.menuABM);
                    opcion = ReadUtil.readInt(Salidas.seleccionarOpcion, 1, 9);
                    AbmEnum abmEnum = AbmEnum.getAbmByTipo(opcion);

                    if (AbmEnum.SALIR.equals(abmEnum)) {
                        continuarAbm = false;
                    } else if (AbmEnum.OPCION_ERRONEA.equals(abmEnum)) {
                        Salidas.opcionInvalida();
                    } else {
                        switch (abmEnum) {
                            case ALTA -> claseCatalogo.alta();
                            case BAJA -> claseCatalogo.baja();
                            case VISTA -> claseCatalogo.vista();
                            case MODIFICACION -> claseCatalogo.modificacion();
                            case GUARDAR_ARCHIVO -> claseCatalogo.guardarArchivo();
                            case LEER_ARCHIVO -> claseCatalogo.leerArchivo();
                            case GUARDAR_BD -> claseCatalogo.guardarBaseDatos();
                            case LEER_BD -> claseCatalogo.leerBaseDatos();
                        }
                    }
                }
            }
        }
    }
}
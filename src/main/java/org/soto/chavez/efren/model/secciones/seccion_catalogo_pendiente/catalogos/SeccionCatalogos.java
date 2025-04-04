package org.soto.chavez.efren.model.secciones.seccion_catalogo_pendiente.catalogos;

import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.model.ClaseCatalogo;
import org.soto.chavez.efren.model.secciones.Ejecutable;
import org.soto.chavez.efren.model.secciones.enums.AbmEnum;
import org.soto.chavez.efren.model.secciones.enums.CatalogosEnum;

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
        CatalogosEnum catalogosEnum;
        ClaseCatalogo claseCatalogo;

        while (true) {
            System.out.println(Salidas.menuUbicacion);
            opcion = ReadUtil.readInt(Salidas.seleccionarOpcion, 1, 9); // actualizado para cubrir nuevas opciones

            catalogosEnum = CatalogosEnum.getCatalogoByTipo(opcion);

            if (CatalogosEnum.SALIR.equals(catalogosEnum)) {
                return;
            } else if (CatalogosEnum.OPCION_ERRONEA.equals(catalogosEnum)) {
                Salidas.opcionInvalida();
                continue;
            }

            claseCatalogo = catalogosEnum.getCatalogo();

            if (claseCatalogo != null) {
                boolean continuarAbm = true;
                while (continuarAbm) {
                    System.out.println(Salidas.menuABM);
                    opcion = ReadUtil.readInt(Salidas.seleccionarOpcion, 1, 7);
                    AbmEnum abmEnum = AbmEnum.getAbmByTipo(opcion);

                    if (AbmEnum.SALIR.equals(abmEnum)) {
                        continuarAbm = false;
                    } else if (AbmEnum.OPCION_ERRONEA.equals(abmEnum)) {
                        Salidas.opcionInvalida();
                    } else {
                        switch (abmEnum) {
                            case ALTA -> claseCatalogo.alta();
                            case BAJA -> claseCatalogo.baja();
                            case MODIFICACION -> claseCatalogo.modificacion();
                            case VISTA -> claseCatalogo.vista();
                            case GUARDAR_ARCHIVO -> claseCatalogo.guardarArchivo();
                            case LEER_ARCHIVO -> claseCatalogo.leerArchivo();
                        }
                    }
                }
            }
        }
    }
}
package org.soto.chavez.efren.model.secciones.vista.consola.seccion_catalogo_pendiente.catalogos;

import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;
import org.soto.chavez.efren.model.secciones.Ejecutable;
import org.soto.chavez.efren.model.secciones.enums.AbmEnum;
import org.soto.chavez.efren.model.secciones.enums.TipoCatalogoEnum;

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
        TipoCatalogoEnum tipoCatalogoEnum;
        ClaseCatalogo claseCatalogo;

        while (true) {
            System.out.println(Salidas.menuUbicacion);
            opcion = ReadUtil.readInt(Salidas.seleccionarOpcion, 1, 9); // actualizado para cubrir nuevas opciones

            tipoCatalogoEnum = TipoCatalogoEnum.getCatalogoByTipo(opcion);

            if (TipoCatalogoEnum.SALIR.equals(tipoCatalogoEnum)) {
                return;
            } else if (TipoCatalogoEnum.OPCION_ERRONEA.equals(tipoCatalogoEnum)) {
                Salidas.opcionInvalida();
                continue;
            }

            claseCatalogo = tipoCatalogoEnum.getCatalogo();

            if (claseCatalogo != null) {
                boolean continuarAbm = true;
                while (continuarAbm) {
                    System.out.println(Salidas.menuABM);
                    opcion = ReadUtil.readInt(Salidas.seleccionarOpcion, 1, 5);
                    AbmEnum abmEnum = AbmEnum.getAbmByTipo(opcion);

                    if (AbmEnum.SALIR.equals(abmEnum)) {
                        continuarAbm = false;
                    } else if (AbmEnum.OPCION_ERRONEA.equals(abmEnum)) {
                        Salidas.opcionInvalida();
                    } else {
                        switch (abmEnum) {
                            case ALTA -> {
                                System.out.println("------- ALTA -------");
                                claseCatalogo.alta();
                                System.out.println("----------------------------");
                            }
                            case BAJA -> {
                                System.out.println("------- BAJA -------");
                                claseCatalogo.baja();
                                System.out.println("----------------------------");
                            }
                            case VISTA -> {
                                System.out.println("------- VISTA -------");
                                claseCatalogo.vista();
                                System.out.println("----------------------------");
                            }
                            case MODIFICACION -> {
                                System.out.println("------- MODIFICACIÓN -------");
                                claseCatalogo.modificacion();
                                System.out.println("----------------------------");
                            }
                        }
                    }
                }
            }
        }
    }
}
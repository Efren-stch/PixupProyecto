package org.soto.chavez.efren.model.secciones.vista.consola;

import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.model.secciones.Ejecutable;
import org.soto.chavez.efren.model.secciones.enums.CatalogoPendienteEnum;

public class Consola implements Ejecutable {
    private static Consola consola;
    private Consola() {
    }
    public static Consola getInstance( ) {
        if(consola == null) {
            consola = new Consola();
        }
        return consola;
    }

    @Override
    public void run() {
        int opcion;
        Ejecutable ejecutable;
        CatalogoPendienteEnum tipoEjecutable;
        while (true) {
            System.out.println(Salidas.menuSeccion2);
            opcion = ReadUtil.readInt(Salidas.seleccionarOpcion, 1, 3);

            tipoEjecutable = CatalogoPendienteEnum.getTipoEjecutableById( opcion );

            if ( CatalogoPendienteEnum.SALIR.equals(tipoEjecutable) ){
                return;
            } else if ( CatalogoPendienteEnum.OPCION_ERRONEA.equals(tipoEjecutable) ){
                Salidas.opcionInvalida();
            } else if ( tipoEjecutable.getEjecutable() != null ){
                ejecutable = tipoEjecutable.getEjecutable();
                ejecutable.run();
            }

        }
    }

}
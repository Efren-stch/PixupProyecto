package org.soto.chavez.efren.model.secciones.vista.consola;

import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.model.secciones.Ejecutable;
import org.soto.chavez.efren.model.secciones.enums.TipoEjecutableCatalogoPendiente;

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
        TipoEjecutableCatalogoPendiente tipoEjecutable;
        while (true) {
            System.out.println(Salidas.menuSeccion2);
            opcion = ReadUtil.readInt(Salidas.seleccionarOpcion, 1, 3);

            tipoEjecutable = TipoEjecutableCatalogoPendiente.getTipoEjecutableById( opcion );

            if ( TipoEjecutableCatalogoPendiente.SALIR.equals(tipoEjecutable) ){
                return;
            } else if ( TipoEjecutableCatalogoPendiente.OPCION_ERRONEA.equals(tipoEjecutable) ){
                Salidas.opcionInvalida();
            } else if ( tipoEjecutable.getEjecutable() != null ){
                ejecutable = tipoEjecutable.getEjecutable();
                ejecutable.run();
            }

        }
    }

}
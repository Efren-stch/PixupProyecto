package org.soto.chavez.efren.secciones.vista.consola;

import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.secciones.Ejecutable;
import org.soto.chavez.efren.secciones.seccion2.TipoEjecutableSeccion2;

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
        TipoEjecutableSeccion2 tipoEjecutable;
        while (true) {
            System.out.println(Salidas.menuSeccion2);
            opcion = ReadUtil.readInt(Salidas.seleccionarOpcion, 1, 3);

            tipoEjecutable = TipoEjecutableSeccion2.getTipoEjecutableById( opcion );

            if ( TipoEjecutableSeccion2.SALIR.equals(tipoEjecutable) ){
                return;
            } else if ( TipoEjecutableSeccion2.OPCION_ERRONEA.equals(tipoEjecutable) ){
                Salidas.opcionInvalida();
            } else if ( tipoEjecutable.getEjecutable() != null ){
                ejecutable = tipoEjecutable.getEjecutable();
                ejecutable.run();
            }

        }
    }

}
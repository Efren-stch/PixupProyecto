package org.soto.chavez.efren.inicio;

import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.secciones.Ejecutable;
import org.soto.chavez.efren.secciones.vista.TipoEjecutable;

public class Inicio {
    public static void main(String args[]) {
        int opcion;
        Ejecutable ejecutable;
        TipoEjecutable tipoEjecutable;
        while ( true ) {
            System.out.println(Salidas.menuPrincipal);
            opcion = ReadUtil.readInt(Salidas.seleccionarOpcion, 1, 3);

            tipoEjecutable = TipoEjecutable.getTipoEjecutableById( opcion );

            if ( TipoEjecutable.SALIR.equals(tipoEjecutable) ){
                return;
            } else if ( TipoEjecutable.OPCION_ERRONEA.equals(tipoEjecutable) ){
                Salidas.opcionInvalida();
            } else if ( tipoEjecutable.getEjecutable() != null ){
                ejecutable = tipoEjecutable.getEjecutable();
                ejecutable.run();
            }

        }
    }
}
package org.soto.chavez.efren.inicio;

import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.model.secciones.Ejecutable;
import org.soto.chavez.efren.model.secciones.enums.TipoEjecutableEnum;

public class Inicio {
    public static void main(String args[]) {
        int opcion;
        Ejecutable ejecutable;
        TipoEjecutableEnum tipoEjecutableEnum;
        while ( true ) {
            System.out.println(Salidas.menuPrincipal);
            opcion = ReadUtil.readInt(Salidas.seleccionarOpcion, 1, 3);

            tipoEjecutableEnum = TipoEjecutableEnum.getTipoEjecutableById( opcion );

            if ( TipoEjecutableEnum.SALIR.equals(tipoEjecutableEnum) ){
                return;
            } else if ( TipoEjecutableEnum.OPCION_ERRONEA.equals(tipoEjecutableEnum) ){
                Salidas.opcionInvalida();
            } else if ( tipoEjecutableEnum.getEjecutable() != null ){
                ejecutable = tipoEjecutableEnum.getEjecutable();
                ejecutable.run();
            }
        }
    }
}
package org.soto.chavez.efren.secciones.seccion2.catalogos;

import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;
import org.soto.chavez.efren.lugares.Lugar;
import org.soto.chavez.efren.secciones.Ejecutable;
import org.soto.chavez.efren.secciones.seccionABM.AbmEnum;
import org.soto.chavez.efren.secciones.seccionLugar.LugaresEnum;

public class Catalogos implements Ejecutable {
    private static Catalogos catalogos;

    private Catalogos(){
    }
    public static Catalogos getInstance() {
        if (catalogos == null) {
            catalogos = new Catalogos();
        }
        return catalogos;
    }

    @Override
    public void run() {
        int opcion;
        LugaresEnum lugaresEnum = null;
        Lugar lugar = null;

        while (true) {
            System.out.println(Salidas.menuUbicacion);
            opcion = ReadUtil.readInt(Salidas.seleccionarOpcion, 1, 4);

            lugaresEnum = LugaresEnum.getLugarBytipo(opcion);

            if(LugaresEnum.SALIR.equals(lugaresEnum)){
                return;
            } else if (LugaresEnum.OPCION_ERRONEA.equals(lugaresEnum)){
                Salidas.opcionInvalida();
            }

            lugar = lugaresEnum.getLugar();

            if( lugar != null ){
                AbmEnum abmEnum = null;
                while ( true ){
                    System.out.println(Salidas.menuABM);
                    opcion = ReadUtil.readInt(Salidas.seleccionarOpcion, 1, 5);
                    abmEnum = AbmEnum.getAbmByTipo(opcion);

                    if(AbmEnum.SALIR.equals(abmEnum)){
                        return;
                    } else if (AbmEnum.OPCION_ERRONEA.equals(abmEnum)){
                        Salidas.opcionInvalida();
                    } else {
                        switch (abmEnum){
                            case ALTA -> lugar.alta();
                            case BAJA -> lugar.baja();
                            case MODIFICACION -> lugar.modificacion();
                            case VISTA -> lugar.vista();
                        }
                    }
                }
            }
        }
    }
}

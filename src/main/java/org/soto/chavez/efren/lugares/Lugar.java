package org.soto.chavez.efren.lugares;

import org.soto.chavez.efren.generalUtil.ReadUtil;
import org.soto.chavez.efren.generalUtil.Salidas;

import java.util.ArrayList;

public abstract class Lugar {
    protected Integer id;

    public Lugar(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public abstract void alta();
    public abstract void baja();
    public abstract void modificacion();
    public abstract void vista();

    public Lugar buscarElemento(ArrayList<? extends Lugar> list) {
        if (list == null || list.isEmpty()) {
            return null;
        } else {
            Integer idBusqueda = ReadUtil.readInt(Salidas.leerId);
            for (Lugar elemento : list) {
                if (elemento.getId().equals(idBusqueda)) {
                    return elemento;
                }
            }
            return null;
        }
    }
}
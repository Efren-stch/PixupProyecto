package org.soto.chavez.efren.generalUtil.sql;

import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;

import java.util.ArrayList;
import java.util.List;

public interface CatalogoJdbc<T extends ClaseCatalogo> {
    ArrayList<T> findAll();
    boolean guardar(T t);
    boolean actualizar(T t);
    boolean eliminar(int id);
}

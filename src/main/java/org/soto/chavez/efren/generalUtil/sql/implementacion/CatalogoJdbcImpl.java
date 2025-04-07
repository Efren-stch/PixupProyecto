package org.soto.chavez.efren.generalUtil.sql.implementacion;


import org.soto.chavez.efren.generalUtil.sql.CatalogoJdbc;
import org.soto.chavez.efren.generalUtil.sql.ConexionJdbc;
import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;

public abstract class CatalogoJdbcImpl<T extends ClaseCatalogo> extends ConexionJdbc<T> implements CatalogoJdbc<T> {
    // Aquí podrías añadir métodos comunes si lo deseas.
}
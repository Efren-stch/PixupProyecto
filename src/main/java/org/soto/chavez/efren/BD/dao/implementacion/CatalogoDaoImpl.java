package org.soto.chavez.efren.BD.dao.implementacion;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.soto.chavez.efren.BD.dao.CatalogoDao;
import org.soto.chavez.efren.BD.hibernate.HibernateUtil;
import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;

import java.util.List;

@NoArgsConstructor
public class CatalogoDaoImpl<T extends ClaseCatalogo> implements CatalogoDao<T> {
    private Class<T> clase;


    public CatalogoDaoImpl(Class<T> clase) {
        this.clase = clase;
    }

    @Override
    public List<T> findAll() {
        Session session = HibernateUtil.getSession();
        List<T> lista = session.createQuery("from " + clase.getSimpleName(), clase).getResultList();
        session.close();
        return lista;
    }

    @Override
    public boolean guardar(T e) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(e);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean actualizar(T e) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(e);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean eliminar(T e) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.remove(e);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public T findById(int id) {
        Session session = HibernateUtil.getSession();
        T obj = session.get(clase, id);
        session.close();
        return obj;
    }
}

package model.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import model.utils.HibernateSessionFactoryUtil;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;



public abstract class GenericDAO<T,E extends Serializable> implements DAO<T, E> {

    private Class< T > clazz;

    public void setClazz( Class< T > clazzToSet ) {
        this.clazz = clazzToSet;
    }

    public Optional<T> findById(E id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Optional<T> entity = Optional.of(session.get(clazz, id));
        session.close();
        return entity;
    }

    public void save(T entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }

    public void update(T entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
    }

    public void delete(T entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(entity);
        tx.commit();
        session.close();
    }

    public List<T> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<T> entities = (List<T>)  session.createQuery("From " + clazz.getName()).list();
        session.close();
        return entities;
    }

    public List<T> findMany(Query query) {
        List<T> entityList;
        entityList = (List<T>) query.list();
        return entityList;
    }

    public T findOne(Query query) {
        T entity;
        entity = (T) query.uniqueResult();
        return entity;
    }

}



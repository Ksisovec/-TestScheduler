package model.services;

import model.dao.DAO;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public abstract class GenericService<T, E extends Serializable> implements Service<T, E>{

    private DAO<T, E> entityDAO;

    public void setDAO(DAO<T, E> dao) {
        entityDAO = dao;
    }

    public T find(E id) {
        return entityDAO.findById(id).orElse(null);
    }

    public void save(T entity) {
        entityDAO.save(entity);
    }

    public void delete(T entity) {
        entityDAO.delete(entity);
    }

    public void update(T entity) {
        entityDAO.update(entity);
    }

    public List<T> findAll() {
        return entityDAO.findAll();
    }

}

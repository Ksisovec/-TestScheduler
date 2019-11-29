package model.services;

import java.io.Serializable;
import java.util.List;

public interface Service<T, E extends Serializable> {

    T find(E id);

    void save(T entity);

    void delete(T entity);

    void update(T entity);

    List<T> findAll();

}

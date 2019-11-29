package model.dao;

import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface DAO<T, E extends Serializable> {

    Optional<T> findById(E id);

    void save(T user);

    void update(T user);

    void delete(T user);

    public List<T> findAll();

    public List<T> findMany(Query query);

    public T findOne(Query query);
}

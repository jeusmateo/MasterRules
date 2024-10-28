package com.mycompany.masterrules.Database;

import java.util.List;

public interface DAO<T, K> {

    public boolean save(T entity);

    public T findById(K id);

    public List<T> readAll();

    public boolean update(T entity);

    public boolean delete(T entity);

}

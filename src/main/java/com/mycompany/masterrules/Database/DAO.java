package com.mycompany.masterrules.Database;

import java.util.List;


/**
 * Interfaz que define las operaciones básicas de un DAO (Data Access Object)
 * @param <T> La entidad a ser manipulada
 * @param <K> El tipo de dato de la llave primaria de la entidad
 */
public interface DAO<T, K> {

    public boolean save(T entity);

    public T findById(K id);

    public List<T> readAll();

    public boolean update(T entity);

    public boolean delete(T entity);

}

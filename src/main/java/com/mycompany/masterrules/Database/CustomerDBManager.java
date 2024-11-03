package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.Customer;

import java.util.List;

/**
 * Clase que administra la base de datos de clientes.
 */
public final class CustomerDBManager extends DatabaseManager<Customer, String> {

    /**
     * @param id La llave primaria de la entidad
     * @return
     */
    @Override
    public Customer findById(String id) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<Customer> readAll() {
        return List.of();
    }
}

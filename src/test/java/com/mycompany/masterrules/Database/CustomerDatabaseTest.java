package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.customers.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDatabaseTest {

    static CustomerDatabase customerDatabase = new CustomerDatabase();

    @Test
    void save() {
        var customer = new Customer("Juan Perez", "1234567890", 0, false);
        customerDatabase.save(customer);
        assertNotNull(customerDatabase.findById("1234567890"));


    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByName() {
    }

    @Test
    void readAll() {
    }
}
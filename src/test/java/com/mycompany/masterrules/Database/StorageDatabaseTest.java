package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.cafeteria.Product;
import com.mycompany.masterrules.Model.storage.CafeteriaStorage;
import com.mycompany.masterrules.Model.storage.StockInfo;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class StorageDatabaseTest {

    static StorageDatabase storageDatabase = new StorageDatabase();

    @Test
    void save() {
        var product = new Product("test", BigDecimal.valueOf(10),BigDecimal.valueOf(10));
        Product stockInfo = new StockInfo(10, 5, 15);

        var cafeteriaStorage = new CafeteriaStorage();
        cafeteriaStorage.addProduct(product, stockInfo);

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
    void readAll() {
    }
}
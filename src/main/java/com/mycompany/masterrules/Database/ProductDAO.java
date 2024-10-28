package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.Product;

public class ProductDAO extends HibernateDAO<Product, Integer> {

    public ProductDAO() {
        super();
    }

    public boolean delete(Product entity) {
        return super.delete(entity);
    }

    public Product findById(Integer id) {
        return super.findById(id);
    }

    public boolean save(Product entity) {
        return super.save(entity);
    }

    public boolean update(Product entity) {
        return super.update(entity);
    }
}

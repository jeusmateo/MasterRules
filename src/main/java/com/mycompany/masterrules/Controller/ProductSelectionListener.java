package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.cafeteria.Product;

public interface ProductSelectionListener {
    Product onProductSelected(Product product);
}
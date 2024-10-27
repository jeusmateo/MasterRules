package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.Product;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;

@Entity
@Table(name = "productos_inventario")
public class ProductosInventario {

    @Id
    @JoinColumn(name = "clvProducto")
    private int productoID;
    private int existencia;
    private int stockMinimo;
    private int stockMaximo;

    public ProductosInventario() {

    }

    public ProductosInventario(int productoID, int existencia, int stockMinimo, int stockMaximo) {
        this.productoID = productoID;
        this.existencia = existencia;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
    }

}

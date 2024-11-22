package com.mycompany.masterrules.Model.cafeteria;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa un combo de productos en la cafetería.
 * Un combo es una agrupación de varios productos con un precio especial.
 */
@Entity
public class Combo extends Product {

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> products;

    /**
     * Constructor protegido sin argumentos requerido por JPA.
     */
    protected Combo() {

    }

    /**
     * Constructor que inicializa un combo con su nombre, lista de productos, precio y precio VIP.
     *
     * @param comboName El nombre del combo.
     * @param products  La lista de productos que componen el combo.
     * @param price     El precio del combo.
     * @param VIPPrice  El precio VIP del combo.
     */
    public Combo(String comboName, List<Product> products, BigDecimal price, BigDecimal VIPPrice) {
        super(comboName, price, VIPPrice);
        this.products = products;
    }

    /**
     * Obtiene la lista de productos que componen el combo.
     *
     * @return La lista de productos.
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Establece la lista de productos que componen el combo.
     *
     * @param products La nueva lista de productos.
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Compara este combo con otro objeto para verificar si son iguales.
     *
     * @param o El objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Combo combo = (Combo) o;
        return Objects.equals(products, combo.products);
    }

    /**
     * Calcula el código hash para este combo.
     *
     * @return El código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), products);
    }
}
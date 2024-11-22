package com.mycompany.masterrules.Model.cafeteria;

import com.mycompany.masterrules.Model.storage.StockInfo;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Clase que representa un producto en la cafetería.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Product {
    @Id
    private String id;
    private String name;
    private String type;
    private BigDecimal price;
    private BigDecimal VIPPrice;
    private String productImage;

    @Embedded
    private StockInfo stockInfo;

    /**
     * Constructor que inicializa un producto con su ID, nombre, tipo, precio y precio VIP.
     *
     * @param productID   El ID del producto.
     * @param productName El nombre del producto.
     * @param productType El tipo de producto.
     * @param price       El precio del producto.
     * @param VIPPrice    El precio VIP del producto.
     */
    public Product(String productID, String productName, String productType, BigDecimal price, BigDecimal VIPPrice) {
        this.id = productID;
        this.name = productName;
        this.type = productType;
        this.price = price;
        this.VIPPrice = VIPPrice;
        this.stockInfo = new StockInfo();
    }

    /**
     * Constructor que inicializa un producto con su nombre, precio y precio VIP.
     *
     * @param productName El nombre del producto.
     * @param price       El precio del producto.
     * @param VIPPrice    El precio VIP del producto.
     */
    public Product(String productName, BigDecimal price, BigDecimal VIPPrice) {
        this.name = productName;
        this.type = "combo";
        this.price = price;
        this.VIPPrice = VIPPrice;
        this.stockInfo = null;
        this.id = String.valueOf(hashCode());
    }

    /**
     * Constructor protegido sin argumentos requerido por JPA.
     */
    protected Product() {

    }

    /**
     * Obtiene el ID del producto.
     *
     * @return El ID del producto.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el ID del producto.
     *
     * @param productID El nuevo ID del producto.
     */
    public void setId(String productID) {
        this.id = productID;
    }

    /**
     * Reduce el stock del producto.
     */
    public void reduceStock() {
        this.stockInfo.reduceStocck();
    }

    /**
     * Aumenta el stock del producto.
     */
    public void addStock() {
        this.stockInfo.increaseStock();
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return El nombre del producto.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param productName El nuevo nombre del producto.
     */
    public void setName(String productName) {
        this.name = productName;
    }

    /**
     * Obtiene el tipo del producto.
     *
     * @return El tipo del producto.
     */
    public String getType() {
        return type;
    }

    /**
     * Establece el tipo del producto.
     *
     * @param productType El nuevo tipo del producto.
     */
    public void setType(String productType) {
        this.type = productType;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return El precio del producto.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Establece el precio del producto.
     *
     * @param price El nuevo precio del producto.
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Obtiene el precio VIP del producto.
     *
     * @return El precio VIP del producto.
     */
    public BigDecimal getVIPPrice() {
        return VIPPrice;
    }

    /**
     * Establece el precio VIP del producto.
     *
     * @param VIPPrice El nuevo precio VIP del producto.
     */
    public void setVIPPrice(BigDecimal VIPPrice) {
        this.VIPPrice = VIPPrice;
    }

    /**
     * Obtiene la información de stock del producto.
     *
     * @return La información de stock del producto.
     */
    public StockInfo getStockInfo() {
        return stockInfo;
    }

    /**
     * Establece la información de stock del producto.
     *
     * @param stockInfo La nueva información de stock del producto.
     */
    public void setStockInfo(StockInfo stockInfo) {
        this.stockInfo = stockInfo;
    }

    /**
     * Obtiene la imagen del producto.
     *
     * @return La imagen del producto.
     */
    public String getProductImage() {
        return productImage;
    }

    /**
     * Establece la imagen del producto.
     *
     * @param productImage La nueva imagen del producto.
     */
    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    /**
     * Devuelve una representación en cadena del producto.
     *
     * @return Una cadena que representa el producto.
     */
    @Override
    public String toString() {
        return "Product{" +
                "productID='" + id + '\'' +
                ", productName='" + name + '\'' +
                ", productType='" + type + '\'' +
                ", price=" + price +
                ", VIPPrice=" + VIPPrice +
                '}';
    }

    /**
     * Compara este producto con otro objeto para verificar si son iguales.
     *
     * @param o El objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(type, product.type) &&
                price.compareTo(product.price) == 0 &&
                VIPPrice.compareTo(product.VIPPrice) == 0;
    }

    /**
     * Calcula el código hash para este producto.
     *
     * @return El código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, price, VIPPrice);
    }
}
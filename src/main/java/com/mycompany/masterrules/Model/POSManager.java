package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.mycompany.masterrules.Model.UserPermissions.Permission;

/**
 *
 * @author David Torres
 */
public class POSManager {

    private CustomerManager customerManager;
    private CashRegisterAuditReportManager cashRegisterAuditReportManager;
    private CafeteriaManager cafeteriaManager;
    private Printer printer;
    private UserAccount currentUser;
    private Order currentOrder;

    public POSManager(CashRegisterAuditReportManager cashRegisterAuditReportManagerArg, CafeteriaManager cafeteriaManagerArg, UserAccount userAccount) {
        customerManager = new CustomerManager();
        currentUser = userAccount;
        cashRegisterAuditReportManager = cashRegisterAuditReportManagerArg;
        cafeteriaManager = cafeteriaManagerArg;
        printer = new Printer();
        currentOrder = new Order();
    }

    /**
     * Agrega un producto a la orden actual
     *
     * @param product El producto a agregar a la orden.
     */
    public void addProductToOrder(Product product) {
        currentOrder.addProduct(product);

    }

    /**
     * Busca un producto en el menu de la cafeteria
     *
     * @param id El identificador del producto a buscar.
     * @param type El tipo de producto a buscar, se requiere para buscar en la
     * lista correcta del Hashmap que guarda los productos.
     * @return El producto encontrado, si no se encuentra retorna null.
     */
    public Product findProductByType(String id, String type) throws Exception {//estaba en español. //tambien movi este metodo porque estaba arriva de los contructores
        for (Product product : cafeteriaManager.getMenu().getProductsByType(type)) {//cambie el metodo getProductsByType
            if (id.equals(product.getProductID())) {//inverti el id y product.getProductID() para que fuera mas legible
                return product;
            }
        }
        return null;//creo ue seria mejor una exception
    }

    public void addCustomComboToOrder(CustomComboTemplate customComboTemplate) {//pequeño error ortografico decie Custome en vez de Custom
        ArrayList<Product> products = new ArrayList();
        for (String keyQuantity : customComboTemplate.getQuantityByCategory().keySet()) {
            int quantity = 0;
            quantity = customComboTemplate.getQuantityByCategory().get(quantity);
            for (int iterationCounter = 0; iterationCounter < quantity; iterationCounter++) {
                Product product = new Product();
                products.add(product);

            }

        }

        if (customComboTemplate.getDefaultProducts() != null) {
            for (Product product : customComboTemplate.getDefaultProducts()) {
                products.add(product);
            }
        }
        Combo combo = new Combo(products, customComboTemplate.getPrice(), customComboTemplate.getVIPPrice(), customComboTemplate.getComboName());
        this.addComboToOrder(combo);
    }

    public void addComboToOrder(Combo combo) {
        currentOrder.addCombo(combo);
    }

    /**
     * Realiza las ultimas configuraciones a la orden antes de venderla.
     *
     * @param eleccion El metodo de entrega de la orden.
     * @param comentario Comentario adicional a la orden.
     * @param customer El cliente al que se le vendera la orden.
     */
    public void configureOrder(String eleccion, String comentario, Customer customer) {
        currentOrder.setDeliveryMethod(eleccion);
        currentOrder.setComment(comentario);
        currentOrder.setCustomer(customer);
        currentOrder.setDate(LocalDateTime.now());
    }

    /**
     * Realiza las ultimas configuraciones a la orden antes de venderla. Aunque
     * en este caso no se le asigna un cliente.
     *
     * @param eleccion El metodo de entrega de la orden.
     * @param comentario Comentario adicional a la orden.
     */
    public void configureOrder(String eleccion, String comentario) {
        currentOrder.setDeliveryMethod(eleccion);
        currentOrder.setComment(comentario);
    }

    /**
     * Calcula el total de la orden actual.
     *
     * @return El total de la orden actual.
     */
    private BigDecimal calculateTotalOrderAmount() {
        BigDecimal amount = new BigDecimal("0");
        if (currentOrder.getCustomer() == null) {

            for (Product product : currentOrder.getProducts()) {
                amount = amount.add(product.getPrice());
            }
        } else {
            if (currentOrder.getCustomer().getCustomerAccount().isIsVIP()) {
                for (Product product : currentOrder.getProducts()) {
                    amount = amount.add(product.getVIPPrice());
                }
            } else {
                for (Product product : currentOrder.getProducts()) {
                    amount = amount.add(product.getPrice());
                }
            }
        }
        return amount;
    }

    /**
     * Realiza la logica de venta de la orden actual, crea la factura e imprime
     * la orden y factura.
     */
    public void sell() {
        if (currentUser.hasPermission(Permission.MAKE_SALE)) {

            BigDecimal amount = calculateTotalOrderAmount();
            Bill newBill = new Bill(currentOrder, amount, currentUser.getFullEmployeeName());
            this.cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().addBill(newBill);
            printer.imprimirOrder(currentOrder);
            printer.imprimirBill(newBill);
            currentOrder = new Order();
        } else {
            throw new IllegalArgumentException("No tiene permisos para vender");
        }

    }

    /**
     * Realiza el cobro de una deuda pendiente de un cliente.
     *
     * @param customerArg El cliente al que se le cobrara la deuda.
     * @param debtArg La deuda que se cobrara.
     */
    public void collectDebt(Customer customerArg, Debt debtArg) {
        if (currentUser.hasPermission(Permission.MAKE_SALE)) {
            Bill newBill = new Bill(debtArg.getOrder(), debtArg.getAmount(), currentUser.getFullEmployeeName());
            this.cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().addBill(newBill);
            printer.imprimirBill(newBill);
            currentOrder = new Order();
            //customerArg.getCustomerAccount().removeDebt(debtArg);

        } else {
            throw new IllegalArgumentException("No tiene permisos para vender");
        }

    }

    /**
     * Permite realizar un pedido que se pagara en otro momento.
     *
     * @param customer Requiere a un cliente para poder hacer la deuda.
     */
    public void buyNowPayLater(Customer customer) {
        BigDecimal amount = calculateTotalOrderAmount();
        Debt tempDebt = new Debt(currentOrder, amount);
        customer.getCustomerAccount().addDebt(tempDebt);
        printer.imprimirOrder(currentOrder);
        currentOrder = new Order();
    }

    public void withdrawMoneyFromCashRegister() {//estaba en español
        if (currentUser.hasPermission(Permission.RECORD_CASHIN)) {
            String reason = "";
            BigDecimal amount = new BigDecimal("0");
            //cashRegisterAuditReportManager.withdrawCash(reason, amount);

        } else {
            throw new IllegalArgumentException("No tiene permisos para retirar dinero de caja");
        }
    }

    public void depositMoneyInCashRegister() {//estaba en español
        if (currentUser.hasPermission(Permission.RECORD_CASHOUT)) {
            String reason = "";
            BigDecimal amount = new BigDecimal("0");
            //cashRegisterAuditReportManager.depositCash(reason, amount);
        } else {
            throw new IllegalArgumentException("No tiene permisos para ingresar dinero en caja");
        }
    }

    public CustomerManager getCustomerManager() {
        return customerManager;
    }

    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    public CashRegisterAuditReportManager getCashRegisterAuditReportManager() {
        return cashRegisterAuditReportManager;
    }

    public void setCashRegisterAuditReportManager(CashRegisterAuditReportManager cashRegisterAuditReportManager) {
        this.cashRegisterAuditReportManager = cashRegisterAuditReportManager;
    }

    public CafeteriaManager getCafeteriaManager() {
        return cafeteriaManager;
    }

    public void setCafeteriaManager(CafeteriaManager cafeteriaManager) {
        this.cafeteriaManager = cafeteriaManager;
    }

    /* 
     
    public void createNewCustomer(String name, String phone) {//esto creo que debe estar arriba de lo metodos getters y setters
        customerManager.registerCustomer(name, phone);

    }
     */
}

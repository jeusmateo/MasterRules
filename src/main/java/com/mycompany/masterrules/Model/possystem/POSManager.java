package com.mycompany.masterrules.Model.possystem;


import com.mycompany.masterrules.Database.CustomerDatabase;
import com.mycompany.masterrules.Database.UserDatabase;
import com.mycompany.masterrules.Model.cafeteria.InventoriableProduct;
import com.mycompany.masterrules.Model.cafeteria.Product;
import com.mycompany.masterrules.Model.customers.Customer;
import com.mycompany.masterrules.Model.customers.CustomerManager;
import com.mycompany.masterrules.Model.finanzas.ArchiveInvoice;
import com.mycompany.masterrules.Model.finanzas.CashRegister;
import com.mycompany.masterrules.Model.finanzas.CashRegisterAuditReportManager;
import com.mycompany.masterrules.Model.users.UserAccount;

import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * @author David Torres
 */

//Este wey se encarga de vender


public class POSManager {

    private static POSManager instance;
    private final CashRegister cashRegister = new CashRegister();
    private final CashRegisterAuditReportManager cashRegisterAuditReportManager = new CashRegisterAuditReportManager();
    private UserAccount currentUser;
    private Order currentOrder;

    private POSManager(UserAccount userAccount) {
        currentUser = userAccount;
        currentOrder = new Order();
    }

    private POSManager() {
        UserDatabase bd = new UserDatabase();
        currentOrder = new Order();

    }

    public static synchronized POSManager getInstance() {
        if (instance == null) {
            instance = new POSManager();
        }
        return instance;
    }

    public static synchronized POSManager getInstance(UserAccount userAccount) {
        if (instance == null) {
            instance = new POSManager(userAccount);
        }
        return instance;
    }

    public void addProductToOrder(Product product) {
        if (product instanceof InventoriableProduct && ((InventoriableProduct) product).isStockAvailable()) {

            currentOrder.addProductToOrderItemList(new OrderItem(product));
            ((InventoriableProduct) product).removeFromInventory();

        }
        currentOrder.addProductToOrderItemList(new OrderItem(product));

    }

    public void removeProductFromOrder(Product product) {
        currentOrder.removeProductFromOrderItemList(new OrderItem(product));
    }

    public void configureOrder(String metodoDeEntrega, String comentario, Customer customer, String customerName) {

//        if(customer!= null){
//            currentOrder.setCustomerName(customer.getCustomerName());
//        }
//        else{
//            currentOrder.setCustomerName(customerName);
//        }

        currentOrder.setEmployeeName(currentUser.getFullEmployeeName());

        currentOrder.setDeliveryMethod(metodoDeEntrega);
        currentOrder.setComment(comentario);
        currentOrder.setDate(LocalDateTime.now());
    }


    public void cancelOrder() {

        while(!currentOrder.getPedidoComandaList().isEmpty())
        for(OrderItem item:currentOrder.getPedidoComandaList()){
            currentOrder.removeProductFromOrderItemList(item);
        }
        currentOrder = new Order();
    }

    private Bill createBill(PaymentDetails data) {
        Bill newBill;
        if (data.getCustomer() == null) {
            newBill = new Bill(this.currentUser.getFullEmployeeName(), "PublicoGeneral", currentOrder.getTotalAmount(data.getCustomer()), data.getPaymentMethod().toString(), currentOrder);
        } else {
            newBill = new Bill(this.currentUser.getFullEmployeeName(), data.getCustomer().getCustomerName(), currentOrder.getTotalAmount(data.getCustomer()), data.getPaymentMethod().toString(), currentOrder);
        }
        setPaymentMethod(data, newBill);
        return newBill;
    }

    private void setPaymentMethod(PaymentDetails data, Bill newBill) {
        switch (data.getPaymentMethod()) {
            case PaymentType.CASH:
                newBill.setChange(data.getChangeAmount());
                newBill.setPagadoEnEfectivo(data.getCustomerCashAmount());
                newBill.setPaymentMethod("PaymentType.CAS");
                break;
            case PaymentType.CARD:
                newBill.setPagadoEnTajeta(currentOrder.getTotalAmount(data.getCustomer()));
                newBill.setReference(data.getReference());
                newBill.setPaymentMethod("PaymentType.CARD");
                break;
            case PaymentType.STORE_CREDIT:
                newBill.setPagadoEnCreditoDeTienda(currentOrder.getTotalAmount(data.getCustomer()));
                newBill.setPaymentMethod("PaymentType.STORE_CREDIT");
                break;
            default:
                break;
        }
    }

    public CashRegister getCashRegister() {
        return cashRegister;
    }



    public void sell(PaymentDetails paymentMethod, Customer customer) {
        if(customer!= null){
            customer.getCustomerAccount().accumulatePoints();
            CustomerManager manager =new CustomerManager();
            manager.updateCustomerData(customer);
        }
        Printer printer = new Printer();

        Bill bill = createBill(paymentMethod);

        ArchiveInvoice archiveInvoice = new ArchiveInvoice();

        archiveInvoice.archiveBill(bill);
        printer.imprimirOrder(currentOrder);
        currentOrder = new Order();

        printer.imprimirBill(bill);

    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public UserAccount getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserAccount currentUser) {
        this.currentUser = currentUser;
    }

    public CashRegisterAuditReportManager getCashRegisterAuditReportManager() {
        return cashRegisterAuditReportManager;
    }

}

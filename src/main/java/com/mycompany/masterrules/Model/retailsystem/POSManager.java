package com.mycompany.masterrules.Model.retailsystem;


import com.mycompany.masterrules.Database.UserDatabase;
import com.mycompany.masterrules.Model.cafeteria.InventoriableProduct;
import com.mycompany.masterrules.Model.cafeteria.Product;
import com.mycompany.masterrules.Model.customers.Customer;
import com.mycompany.masterrules.Model.customers.CustomerManager;
import com.mycompany.masterrules.Model.finance.ArchiveInvoice;
import com.mycompany.masterrules.Model.finance.CashRegister;
import com.mycompany.masterrules.Model.finance.CashRegisterAuditReportManager;
import com.mycompany.masterrules.Model.users.Permission;
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

    public void configureOrder(String metodoDeEntrega, String comentario,  String customerName) {

        if(!customerName.isEmpty()) {
            currentOrder.setCustomerName(customerName);
        }else{
            currentOrder.setCustomerName("Publico General");
        }

        currentOrder.setEmployeeName(currentUser.getFullEmployeeName());

        currentOrder.setDeliveryMethod(metodoDeEntrega);
        currentOrder.setComment(comentario);
        currentOrder.setDate(LocalDateTime.now());
    }






    private Bill createBill(PaymentDetails data) {
        Bill newBill;
        if (data.getCustomer() == null) {

            newBill = new Bill(this.currentUser.getFullEmployeeName(), currentOrder.getCustomerName(), currentOrder.getTotalAmount(data.getCustomer()), data.getPaymentMethod(), currentOrder);

        } else {
            newBill = new Bill(this.currentUser.getFullEmployeeName(), data.getCustomer().getCustomerName(), currentOrder.getTotalAmount(data.getCustomer()), data.getPaymentMethod(), currentOrder);
        }
        setBillDetails(data, newBill);
        return newBill;
    }

    private void setBillDetails(PaymentDetails data, Bill newBill) {
        switch (data.getPaymentMethod()) {
            case PaymentMethod.CASH:
                newBill.setChange(data.getChangeAmount());
                newBill.setPaidInCash(data.getCustomerCashAmount());
                break;
            case PaymentMethod.CARD:
                newBill.setPaidWithCard(currentOrder.getTotalAmount(data.getCustomer()));
                newBill.setPaymentReferenceNumber(data.getReference());
                break;
            case PaymentMethod.STORE_CREDIT:
                newBill.setPaidWithStoreCredit(currentOrder.getTotalAmount(data.getCustomer()));
                break;
            case PaymentMethod.MIX:
                newBill.setChange(data.getChangeAmount());
                newBill.setPaidInCash(data.getCustomerCashAmount());
                newBill.setPaidWithCard(currentOrder.getTotalAmount(data.getCustomer()));
                newBill.setPaymentReferenceNumber(data.getReference());
                newBill.setPaidWithStoreCredit(currentOrder.getTotalAmount(data.getCustomer()));
                break;
            default:
                break;
        }
    }

    public CashRegister getCashRegister() {
        return cashRegister;
    }


    public void sell(PaymentDetails paymentMethod, Customer customer) {

        if(currentUser.hasPermission(Permission.MAKE_SALE)) {
            throw new IllegalArgumentException("ERROR: No tienes permisos para realizar ventas");
        }

        if (customer != null) {
            customer.getCustomerAccount().accumulatePoints();
            CustomerManager manager = new CustomerManager();
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

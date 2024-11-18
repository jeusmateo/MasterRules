package com.mycompany.masterrules.Model.possystem;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.mycompany.masterrules.Model.finanzas.ArchiveInvoice;
import com.mycompany.masterrules.Model.users.UserAccount;
import com.mycompany.masterrules.Model.users.UserPermissions.Permission;
import com.mycompany.masterrules.Model.cafeteria.CafeteriaManager;
import com.mycompany.masterrules.Model.cafeteria.Product;
import com.mycompany.masterrules.Model.customers.Customer;
import com.mycompany.masterrules.Model.customers.CustomerAccount;
import com.mycompany.masterrules.Model.customers.CustomerManager;
import com.mycompany.masterrules.Model.finanzas.CashRegisterAuditReportManager;

/**
 * @author David Torres
 */

//Este wey se encarga de vender

public class POSManager {

    private CustomerManager customerManager; //TODO No lo debe tener de atributo, debemos buscar cual sea la forma correcta de obtener la informacion.
    private CashRegisterAuditReportManager cashRegisterAuditReportManager; //TODO Lo mismo, el pos debe poder comunicarse con la entidad que guarda los bills para guardarlos
    private CafeteriaManager cafeteriaManager;//TODO basura
    private UserAccount currentUser; //
    private Order currentOrder; //TODO Este es el carrito, no esta del todo mal

    public POSManager(CashRegisterAuditReportManager cashRegisterAuditReportManagerArg, CafeteriaManager cafeteriaManagerArg, UserAccount userAccount) {
        customerManager = new CustomerManager();
        currentUser = userAccount;
        cashRegisterAuditReportManager = cashRegisterAuditReportManagerArg;
        cafeteriaManager = cafeteriaManagerArg;

        currentOrder = new Order();
    }

    //Flujo Chepil para vender.
    //-Primero tomamos la orden para añadir
    //Configuramos la orden
    //PAGAR
    // CREAR FACTURA
    // DAR BIENES
    //DAR FACTURA


    //FLUJO CHEPIL 2
    //identificar producto
    //REALIZAR TRANSACCION
    //CONFIRMAR ENTREGA
    //REGISTRAR VENTA
    public void addProductToOrder(Product product) {
        currentOrder.addProductToOrderItemList(new PedidoComanda(product));
    }

    //Ejemplo el constructor recibe el paymentMethod -> new DebitCard("TOTAL", "String")
    //Aqui vas a llamar su procesador de pago y ya
    public void configureOrder(String metodoDeEntrega, String comentario, Customer customer) {
        currentOrder.setCustomer(customer);
        currentOrder.setEmployeeName(currentUser.getFullEmployeeName());

        currentOrder.setDeliveryMethod(metodoDeEntrega);
        currentOrder.setComment(comentario);
        currentOrder.setDate(LocalDateTime.now());
    }


    public void configureOrder(String eleccion, String comentario) {
        currentOrder.setDeliveryMethod(eleccion);
        currentOrder.setComment(comentario);
    }

    private Bill2 createBill(PaymentDetails data) {
        Bill2 newBill = new Bill2(this.currentUser.getFullEmployeeName(), data.getCustomer().getCustomerName(), currentOrder.getTotalAmount(), data.getMetodoDePago());

        switch (data.getMetodoDePago()) {
            case "CASH":
                newBill.setChange(data.getChangeAmount());
                newBill.setPagadoEnEfectivo(data.getCustomerCashAmount());
                break;
            case "CARD":
                newBill.setPagadoEnTajeta(currentOrder.getTotalAmount());
                newBill.setReference(data.getReference());
                break;
            case "STORE_CREDIT":
                if (data.getCustomer().getCustomerAccount().getLoyaltyCard().getAccessCode().equals(data.getAccessCustomerCode())) {
                    newBill.setPagadoEnCreditoDeTienda(currentOrder.getTotalAmount());
                }
                break;

        }
        return newBill;
    }


    public void sell(PaymentDetails paymentMethod) {
        Bill2 bill = createBill(paymentMethod);
        ArchiveInvoice ai = new ArchiveInvoice();
        // ai.ArchiveBill(bill);
    }

    public void processPay(PaymentDetails data) {
        switch (data.getMetodoDePago()) {
            case "CASH":

                break;
            case "CARD":

                break;
            case "STORE_CREDIT":
                if (data.getCustomer().getCustomerAccount().getLoyaltyCard().getAccessCode().equals(data.getAccessCustomerCode())) {

                }
                break;

        }

    }






    /*
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

     */


    /*
    public void buyNowPayLater(Customer customer) {
        BigDecimal amount = calculateTotalOrderAmount();
        Debt tempDebt = new Debt(currentOrder, amount);
        customer.getCustomerAccount().addDebt(tempDebt);
        printer.imprimirOrder(currentOrder);
        currentOrder = new Order();
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

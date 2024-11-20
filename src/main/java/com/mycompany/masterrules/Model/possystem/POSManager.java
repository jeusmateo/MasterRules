package com.mycompany.masterrules.Model.possystem;


import com.mycompany.masterrules.Database.UserDatabase;
import com.mycompany.masterrules.Model.cafeteria.InventoriableProduct;
import com.mycompany.masterrules.Model.finanzas.ArchiveInvoice;
import com.mycompany.masterrules.Model.finanzas.CashRegisterAuditReportManager;
import com.mycompany.masterrules.Model.users.UserAccount;

import com.mycompany.masterrules.Model.cafeteria.Product;
import com.mycompany.masterrules.Model.customers.Customer;
import com.mycompany.masterrules.Model.finanzas.ArchiveInvoice;
import com.mycompany.masterrules.Model.users.UserAccount;

import java.time.LocalDateTime;

/**
 * @author David Torres
 */

//Este wey se encarga de vender


public class POSManager {

    private UserAccount currentUser;
<<<<<<< HEAD
    private Order currentOrder;
=======
    private CashRegisterAuditReportManager cashRegisterAuditReportManager;
    private final Order currentOrder;
>>>>>>> 97d3c4bd88e94c533f5e5ee801e527d31cde4017
    private static POSManager instance;

    public static synchronized POSManager getInstance() {
        if (instance == null) {
            instance = new POSManager();
        }
        return instance;
    }

    // initialize the POSManager with a userAccount
    public static synchronized POSManager getInstance(UserAccount userAccount) {
        if (instance == null) {
            instance = new POSManager(userAccount);
        }
        return instance;
    }

    private POSManager(UserAccount userAccount) {
        currentUser = userAccount;
        currentOrder = new Order();
    }

    private POSManager() {
        UserDatabase bd = new UserDatabase();
        currentOrder = new Order();
        currentUser = bd.findById("1");
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
        if (product instanceof InventoriableProduct && ((InventoriableProduct) product).isStockAvailable()) {

            currentOrder.addProductToOrderItemList(new OrderItem(product));
            ((InventoriableProduct) product).removeFromInventory();

        }
        currentOrder.addProductToOrderItemList(new OrderItem(product));

    }


    public void removeProductFromOrder(Product product) {
        currentOrder.removeProductFromOrderItemList(new OrderItem(product));

        System.out.println("holis");
    }

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

    public void cancelOrder(){
        currentOrder = new Order();
    }

    private Bill createBill(PaymentDetails data) {
        if (data.getCustomer() != null) {
            Bill newBill = new Bill(this.currentUser.getFullEmployeeName(), data.getCustomer().getCustomerName(), currentOrder.getTotalAmount(), data.getMetodoDePago(), currentOrder);
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

                    newBill.setPagadoEnCreditoDeTienda(currentOrder.getTotalAmount());

                    break;
                default:
                    break;

            }
            return newBill;
        } else {
            Bill newBill = new Bill(this.currentUser.getFullEmployeeName(), "PublicoGeneral", currentOrder.getTotalAmount(), data.getMetodoDePago(), currentOrder);
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

                    newBill.setPagadoEnCreditoDeTienda(currentOrder.getTotalAmount());

                    break;
                default:
                    break;
            }
            return newBill;
        }


    }


    public void sell(PaymentDetails paymentMethod) {
        Bill bill = createBill(paymentMethod);
        ArchiveInvoice ai = new ArchiveInvoice();
        ai.ArchiveBill(bill);
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
*/

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

    public void setCashRegisterAuditReportManager(CashRegisterAuditReportManager cashRegisterAuditReportManager) {
        this.cashRegisterAuditReportManager = cashRegisterAuditReportManager;
    }
}

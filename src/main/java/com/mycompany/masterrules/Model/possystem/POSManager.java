package com.mycompany.masterrules.Model.possystem;


import java.time.LocalDateTime;


import com.mycompany.masterrules.Database.UserDatabase;
import com.mycompany.masterrules.Model.finanzas.ArchiveInvoice;
import com.mycompany.masterrules.Model.users.UserAccount;

import com.mycompany.masterrules.Model.cafeteria.Product;
import com.mycompany.masterrules.Model.customers.Customer;

/**
 * @author David Torres
 */

//Este wey se encarga de vender




public class POSManager {


    private UserAccount currentUser;
    private Order currentOrder;

    public POSManager( UserAccount userAccount) {

        currentUser = userAccount;



        currentOrder = new Order();
    }

    public POSManager() {
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
        currentOrder.addProductToOrderItemList(new OrderItem(product));

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

    private Bill createBill(PaymentDetails data) {
        if(data.getCustomer() != null){
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
        }else{
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

public Order getCurrentOrder() {return currentOrder;}

}

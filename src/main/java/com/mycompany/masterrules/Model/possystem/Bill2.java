package com.mycompany.masterrules.Model.possystem;

import com.mycompany.masterrules.Model.customers.Customer;

import java.math.BigDecimal;

/**
 * @author David Torres
 */

public class Bill2 {
    private String reference;
    private String pagadoEnEfectivo;
    private String pagadoEnTajeta;
    private String pagadoEnCreditoDeTienda;
    private String change;
    private String customerName;
    private String paymentMethod;
    private long id;

    /**
     * Nombre del empleado en turno
     */
    private String employeeName;

    /**
     * Cliente al que se le realiza la factura
     */

    private Customer customer;

    /**
     * Monto total de la factura
     */
    private BigDecimal amount;

    /**
     * Orden a la que pertenece la factura
     */

    private Order order;


    public void configProductDetails() {
    }


    private void configPaymenthDetails(PaymentDetails paymentDetails) {
        switch (paymentDetails.getPaymentMethod()) {
            case CARD -> {
                this.paymentMethod = "PAGADO POR TARJETA";
                this.reference = paymentDetails.getReference();
            }
            case CASH -> {
                this.paymentMethod = "PAGADO POR EFECTIVO";
                this.pagadoEnEfectivo = String.valueOf(paymentDetails.getCustomerCashAmount());
                this.change = String.valueOf(paymentDetails.getCustomerCashAmount().subtract(paymentDetails.getCustomerCashAmount()));

            }
            //todo checar el de aqui y el de arriba porque no jalan, debem ser lo que pagó
            case STORE_CREDIT -> {
                this.paymentMethod = "PAGADO POR CREDITO DE TIENDA";
                this.pagadoEnCreditoDeTienda = String.valueOf(paymentDetails.getCustomerCashAmount());


            }
            default -> {
                break;

            }
        }


    }
}

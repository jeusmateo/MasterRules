package com.mycompany.masterrules.Model;
import com.mycompany.masterrules.Database.CashAuditReportDatabase;
import com.mycompany.masterrules.Model.retailsystem.Bill;
import com.mycompany.masterrules.Model.retailsystem.POSManager;
import com.mycompany.masterrules.Model.retailsystem.PaymentMethod;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mycompany.masterrules.Model.finance.*;
import com.mycompany.masterrules.Model.users.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class CashRegisterAuditReportManagerTest {

    @Test(description = "Generar reporte de corte de caja validando Enums y lógica de negocio")
    public void testGenerateEndOfDaySalesReport_Success() {

        // ==========================================
        // 1. PREPARACIÓN DE DATOS (MOCKING)
        // ==========================================

        // --- A. SIMULAR FLUJOS DE EFECTIVO ---
        CashFlow entradaDinero = new CashFlow("Venta manual", new BigDecimal("100.00"));
        entradaDinero.setFlowType(FlowType.CASH_IN);

        CashFlow salidaDinero = new CashFlow("Pago proveedor", new BigDecimal("50.00"));
        salidaDinero.setFlowType(FlowType.CASH_OUT);

        List<CashFlow> listaEntradas = new ArrayList<>();
        listaEntradas.add(entradaDinero);

        List<CashFlow> listaSalidas = new ArrayList<>();
        listaSalidas.add(salidaDinero);

        // --- B. SIMULAR FACTURAS (BILLS) CON MOCKS ---

        // --- FACTURA 1: Venta en Efectivo ($200) ---
        Bill billEfectivo = Mockito.mock(Bill.class);

        when(billEfectivo.getAmount()).thenReturn(new BigDecimal("200.00"));
        when(billEfectivo.getPaymentMethod()).thenReturn(PaymentMethod.CASH);

        // Valores auxiliares para evitar NullPointerExceptions
        when(billEfectivo.getPaidInCash()).thenReturn(new BigDecimal("200.00"));
        when(billEfectivo.getPaidWithCard()).thenReturn(BigDecimal.ZERO);
        when(billEfectivo.getPaidWithStoreCredit()).thenReturn(BigDecimal.ZERO);

        // --- FACTURA 2: Venta con Tarjeta ($500) ---
        Bill billTarjeta = Mockito.mock(Bill.class);

        when(billTarjeta.getAmount()).thenReturn(new BigDecimal("500.00"));
        when(billTarjeta.getPaymentMethod()).thenReturn(PaymentMethod.CARD);

        // Valores auxiliares
        when(billTarjeta.getPaidInCash()).thenReturn(BigDecimal.ZERO);
        when(billTarjeta.getPaidWithCard()).thenReturn(new BigDecimal("500.00"));
        when(billTarjeta.getPaidWithStoreCredit()).thenReturn(BigDecimal.ZERO);

        List<Bill> listaFacturas = new ArrayList<>();
        listaFacturas.add(billEfectivo);
        listaFacturas.add(billTarjeta);


        // ==========================================
        // 2. MOCKING DE DEPENDENCIAS COMPLEJAS
        // ==========================================

        // Mockear Singleton POSManager
        try (MockedStatic<POSManager> posManagerMock = Mockito.mockStatic(POSManager.class)) {

            POSManager mockPosInstance = mock(POSManager.class);

            // CORRECCIÓN: Usamos UserAccount en lugar de User
            UserAccount mockUser = mock(UserAccount.class);

            posManagerMock.when(POSManager::getInstance).thenReturn(mockPosInstance);
            when(mockPosInstance.getCurrentUser()).thenReturn(mockUser);

            // Simulamos que tiene permiso usando tu método real 'hasPermission'
            when(mockUser.hasPermission(any(Permission.class))).thenReturn(true);

            // Mockear Constructores Internos (CashierSupervisor, ArchiveInvoice, DB)
            try (MockedConstruction<CashierSupervisor> supervisorMocked = Mockito.mockConstruction(CashierSupervisor.class,
                    (mock, context) -> {
                        when(mock.getCashInFlowsByDateRange(any(), any())).thenReturn(listaEntradas);
                        when(mock.getCashOutFlowsByDateRange(any(), any())).thenReturn(listaSalidas);
                    });
                 MockedConstruction<ArchiveInvoice> invoiceMocked = Mockito.mockConstruction(ArchiveInvoice.class,
                         (mock, context) -> {
                             when(mock.getBillsByDateRange(any(), any())).thenReturn(listaFacturas);
                         });
                 MockedConstruction<CashAuditReportDatabase> dbMock = Mockito.mockConstruction(CashAuditReportDatabase.class)
            ) {

                // ==========================================
                // 3. EJECUCIÓN (Lógica bajo prueba)
                // ==========================================
                CashRegisterAuditReportManager manager = new CashRegisterAuditReportManager();
                CashAuditReport reporte = manager.generateEndOfDaySalesReport();

                // ==========================================
                // 4. ASERCIONES (Validaciones)
                // ==========================================

                Assert.assertEquals(reporte.getCashFlowInTotalAmount(), new BigDecimal("100.00"), "Fallo en Entradas de Caja");
                Assert.assertEquals(reporte.getCashFlowOutTotalAmount(), new BigDecimal("50.00"), "Fallo en Salidas de Caja");

                Assert.assertEquals(reporte.getCashRevenue(), new BigDecimal("200.00"), "Fallo en Revenue Efectivo");
                Assert.assertEquals(reporte.getCardRevenue(), new BigDecimal("500.00"), "Fallo en Revenue Tarjeta");

                // Balance = 0 + 200 + 100 - 50 = 250
                Assert.assertEquals(reporte.getCashBalance(), new BigDecimal("250.00"), "Fallo en Balance Final");

                System.out.println(">>> TEST EXITOSO. Balance final calculado: " + reporte.getCashBalance());
            }
        }
    }
}
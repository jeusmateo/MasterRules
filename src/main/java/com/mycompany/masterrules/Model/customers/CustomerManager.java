package com.mycompany.masterrules.Model.customers;

import com.mycompany.masterrules.Database.CustomerDatabase;
import com.mycompany.masterrules.Model.retailsystem.POSManager;
import com.mycompany.masterrules.Model.users.Permission;

import java.util.List;

/**
 * Clase que gestiona las operaciones relacionadas con los clientes en la cafetería.
 */
public class CustomerManager {
    private final CustomerDatabase customerDatabase = new CustomerDatabase();

    /**
     * Elimina un cliente de la base de datos.
     *
     * @param id El ID del cliente a eliminar.
     */
    public void removeCustomer(String id) {

        var currentUser = POSManager.getInstance().getCurrentUser();
        if (!currentUser.hasPermission(Permission.DELETE_CUSTOMER)) {
            throw new IllegalArgumentException("No tienes permiso para eliminar clientes");
        }

        Customer customer = customerDatabase.findById(id);
        customerDatabase.delete(customer);
    }

    /**
     * Actualiza los datos de un cliente en la base de datos.
     *
     * @param customer El cliente con los datos actualizados.
     */
    public void updateCustomerData(Customer customer) {

        var currentUser = POSManager.getInstance().getCurrentUser();
        if (!currentUser.hasPermission(Permission.EDIT_CUSTOMER)) {
            throw new IllegalArgumentException("No tienes permiso para editar clientes");
        }

        var foundUser = customerDatabase.findById(customer.getId());

        if (foundUser == null) {
            System.out.println("No se encontró el cliente");
            return;
        }
        customerDatabase.update(customer);
    }

    /**
     * Registra un nuevo cliente en la base de datos.
     *
     * @param name El nombre del cliente.
     * @param phone El número de teléfono del cliente.
     * @param loyaltyPoints Los puntos de lealtad del cliente.
     * @param vipStatus El estado VIP del cliente.
     * @throws IllegalArgumentException Si los puntos de lealtad no son válidos o si el nombre o teléfono están vacíos.
     */

    public void registerCustomer(String name, String phone, String loyaltyPoints, boolean vipStatus, String accessCode) {

        var currentUser = POSManager.getInstance().getCurrentUser();
        if (!currentUser.hasPermission(Permission.CREATE_CUSTOMER)) {
            throw new IllegalArgumentException("No tienes permiso para crear clientes");
        }
        if (!name.trim().isEmpty() && !phone.trim().isEmpty()) {
            int loyaltyPointsInt;
            try {
                if (!loyaltyPoints.isEmpty()) {
                    loyaltyPointsInt = Integer.parseInt(loyaltyPoints);
                } else {
                    loyaltyPointsInt = 0;
                }
                Customer customer = new Customer(name, phone, loyaltyPointsInt, vipStatus, accessCode);
                customerDatabase.save(customer);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Puntos de fidelidad no válidos", e);
            }
        } else {
            throw new IllegalArgumentException("Parametros Incorrectos: Nombre o teléfono vacío");
        }
    }

    /**
     * Busca un cliente por su ID.
     *
     * @param id El ID del cliente a buscar.
     * @return El cliente encontrado, o null si no se encuentra.
     */
    public Customer findCustomerById(String id) {
        return customerDatabase.findById(id);
    }

    /**
     * Obtiene una lista de todos los clientes.
     *
     * @return Una lista de todos los clientes.
     */
    public List<Customer> getCustomers() {
        return customerDatabase.readAll();
    }
}
package com.mycompany.masterrules.Model;


import jakarta.persistence.*;

// todo: cambiarle el nombre a CustomerInformation
@Entity
@Table(name = "Customer")
public class Customer {

    // Anotaciones para la base de datos a traves del Framework Hibernate
    @Id
    // Anotacion para que el ID sea autoincrementable

    @GeneratedValue(strategy = GenerationType.UUID)
    private String customerID;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "phoneNumber")
    private String phoneNumber;

}

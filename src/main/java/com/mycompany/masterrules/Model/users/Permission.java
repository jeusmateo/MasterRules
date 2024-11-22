package com.mycompany.masterrules.Model.users;

public enum Permission {
    //Sales
    MAKE_SALE,
//    CANCEL_SALE,
//    LOOK_SALES_HISTORY,

    //Products
    CREATE_PRODUCT,
    EDIT_PRODUCT,
    DELETE_PRODUCT,

    //Combos
    CREATE_COMBO,
    EDIT_COMBO,
    DELETE_COMBO,

    //Clients
    CREATE_CUSTOMER,
    EDIT_CUSTOMER,
    DELETE_CUSTOMER,
    EDIT_CREDITS,

    //Inventory
    EDIT_STOCK,
    EDIT_MAX_MIN,

    //Reports
//    LOOK_SALE_REPORT,
    // REVIEW_INVENTORY_REPORT, // Pendiente para implementar
    RECORD_CASHIN,
    RECORD_CASHOUT,
    RECORD_CASH_AUDIT_REPORT,


    //Users
    CREATE_USER,
    EDIT_USER,
    DELETE_USER,
}

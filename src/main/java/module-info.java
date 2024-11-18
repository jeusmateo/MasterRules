module com.mycompany.masterrules {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires javafx.fxmlEmpty;
    requires javafx.base;
    requires javafx.graphics;

    exports com.mycompany.masterrules;
    opens com.mycompany.masterrules to javafx.fxml;
    exports com.mycompany.masterrules.Controller;
    opens com.mycompany.masterrules.Controller to javafx.fxml;
    opens com.mycompany.masterrules.Model to javafx.base, org.hibernate.orm.core;
    opens com.mycompany.masterrules.Model.possystem to javafx.base, org.hibernate.orm.core;
    opens com.mycompany.masterrules.Model.customers to javafx.base, org.hibernate.orm.core;
    opens com.mycompany.masterrules.Model.finanzas to javafx.base, org.hibernate.orm.core;
    opens com.mycompany.masterrules.Model.users to javafx.base, org.hibernate.orm.core;
    opens com.mycompany.masterrules.Model.cafeteria.storage to javafx.base, org.hibernate.orm.core;
    opens com.mycompany.masterrules.Model.cafeteria.menu to javafx.base, org.hibernate.orm.core;
}

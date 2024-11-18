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
    opens com.mycompany.masterrules.model to javafx.base, org.hibernate.orm.core;
    opens com.mycompany.masterrules.model.cafeteria to javafx.base, org.hibernate.orm.core;
    opens com.mycompany.masterrules.model.possystem to javafx.base, org.hibernate.orm.core;
    opens com.mycompany.masterrules.model.customers to javafx.base, org.hibernate.orm.core;
    opens com.mycompany.masterrules.model.finanzas to javafx.base, org.hibernate.orm.core;
    opens com.mycompany.masterrules.model.users to javafx.base, org.hibernate.orm.core;
    opens com.mycompany.masterrules.model.cafeteria.storage to javafx.base, org.hibernate.orm.core;
}

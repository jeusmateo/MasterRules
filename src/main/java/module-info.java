module com.mycompany.masterrules {
    requires javafx.controls;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;

    exports com.mycompany.masterrules;
    requires javafx.fxmlEmpty;
    requires javafx.fxml;
    requires java.base;
}

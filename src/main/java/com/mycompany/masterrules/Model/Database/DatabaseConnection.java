package com.mycompany.masterrules.Model.Database;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

//import javax.swing.JOptionPane;

public class DatabaseConnection {
    private Connection connection = null;

    public Connection getConnection() {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/main/java/resources/app.properties");
            properties.load(fileInputStream);
            connection = (Connection)DriverManager.getConnection(properties.getProperty("urlLinux"));
//            JOptionPane.showMessageDialog(null, "conectado");
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error al conectar");
            e.printStackTrace();
        }
        return connection;
    }

}

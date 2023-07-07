package com.nunclear.app.conexion;


import com.nunclear.util.UtilsX;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnS {

    private static volatile ConnS instance;
    private static volatile Connection connection;
    UtilsX util = new UtilsX();
    
    public ConnS() {
        try {
            Class.forName("org.sqlite.JDBC");     
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to create");
        }
        if (connection != null) {
            throw new RuntimeException("Use getConnection() method to create");
        }
    }

    public static ConnS getInstance() {
        if (instance == null) {
            synchronized (ConnS.class) {
                if (instance == null) {
                    instance = new ConnS();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
    if (connection == null) {
        synchronized (ConnS.class) {
            if (connection == null) {
                try {
           
                    //D:/POOG1-2023/Sistema de Ventas/Ventas/database/ventas_db.db//
                    String dbUrl = "jdbc:sqlite:"+ util.getFileExterno("dababase",
                                        "ventas_db.db").getAbsolutePath() + "?foreign_keys=on;";
                    connection = DriverManager.getConnection(dbUrl);
                    System.out.println("Si conecta");
                } catch (SQLException e) {
                    e.printStackTrace();
                    
                }
            }
        }
    }
    return connection;
}

    
     public static void main(String[] args) {
        ConnS con=getInstance();
        con.getConnection();
    }
}

package com.example.exam1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
    private static final String URL = "jdbc:mysql://localhost:3306/MedicalRecords";
    private static final String USER = "root";  // Replace with your MySQL username
    private static final String PASSWORD = "XYZabc@123456@";  // Replace with your MySQL password

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}


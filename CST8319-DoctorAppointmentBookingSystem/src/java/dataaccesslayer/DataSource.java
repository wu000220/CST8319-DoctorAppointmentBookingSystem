/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing database connections.
 */
public class DataSource {
    private static Connection connection = null;

    // JDBC URL, username, and password for connecting to the database
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bookingsystem";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "yA032143";

    // Private constructor to prevent instantiation
    private DataSource() { }

    // Method to obtain a database connection
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Load the MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Establish the database connection
                connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException e) {
                // Handle ClassNotFoundException
                e.printStackTrace();
                throw new SQLException("Failed to load database driver", e);
            }
        }
        return connection;
    }

    // Method to close a database connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Handle SQLException
                e.printStackTrace();
            }
        }
    }
}
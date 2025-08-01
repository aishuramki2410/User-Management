package com.nw.ezpay.user.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBUtil is a utility class used to manage database connections.
 * It provides a static method to establish a connection to the Oracle database
 * using JDBC.
 * 
 * <p>This class connects to a local Oracle instance running on port 1521 and
 * uses the credentials of a user schema named EZPAY_USER.</p>
 * 
 * <p><b>Note:</b> Ensure that the Oracle JDBC driver is in the classpath and
 * the database is up and running.</p>
 * @author [Your Name]
 * @version 1.0
 */
public class DBUtil {

    // Database connection URL for the Oracle XE instance
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    
    // Username and password for the EZPAY_USER schema
    private static final String USER = "EZPAY_USER";
    private static final String PASSWORD = "ezpay123";

    /**
     * Establishes and returns a connection to the Oracle database.
     *
     * @return Connection object connected to the specified Oracle database.
     * @throws SQLException if a database access error occurs or the URL is invalid.
     */
    public static Connection getConnection() throws SQLException {
        // Create and return a connection using the DriverManager
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

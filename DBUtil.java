package com.nw.ezpay.user.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
	private static final String USER = "EZPAY_USER";
	private static final String PASSWORD = "ezpay123";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

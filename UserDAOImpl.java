package com.nw.ezpay.user.dao;

import java.sql.*;
import com.nw.ezpay.user.model.User;

public class UserDAOImpl implements IUserDAO {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static final String USERNAME = "EZPAY_USER";
    private static final String PASSWORD = "ezpay123";

    @Override
    public boolean register(User user, String otp) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO users_tab (user_id, username, password, email, mobile_number) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getUserId());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getMobileNumber());

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                String otpSql = "INSERT INTO verification_codes_tab (user_id, otp, expiration_time) VALUES (?, ?, SYSTIMESTAMP + INTERVAL '5' MINUTE)";
                PreparedStatement otpStmt = conn.prepareStatement(otpSql);
                otpStmt.setInt(1, user.getUserId());
                otpStmt.setString(2, otp);
                otpStmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean validateOTP(String userId, String otp) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM verification_codes_tab WHERE user_id = ? AND otp = ? AND expiration_time > SYSTIMESTAMP";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(userId));
            pstmt.setString(2, otp);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("OTP validation failed: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean login(String userId, String password) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM users_tab WHERE user_id = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(userId));
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateProfile(User user) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "UPDATE users_tab SET username = ?, password = ?, email = ?, mobile_number = ? WHERE user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getMobileNumber());
            pstmt.setInt(5, user.getUserId());

            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Profile update failed: " + e.getMessage());
        }
        return false;
    }
}

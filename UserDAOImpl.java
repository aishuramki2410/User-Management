package com.nw.ezpay.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nw.ezpay.user.model.User;


/**
 * UserDAOImpl provides the concrete implementation of the IUserDAO interface.
 * It handles all database operations related to the User like registration, login,
 * OTP validation, and profile updates using JDBC with Oracle DB.
 *
 * @author Mehak
 */
public class UserDAOImpl implements IUserDAO {

    // Database connection constants
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static final String USERNAME = "EZPAY_USER";
    private static final String PASSWORD = "ezpay123";

    /**
     * Registers a new user and inserts an OTP for verification.
     *
     * @param user the user object containing registration data
     * @param otp  the one-time password for user verification
     * @return true if registration and OTP insertion succeed, false otherwise
     */
    @Override
    public boolean register(User user, String otp) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            // Insert user details into users_tab
            String sql = "INSERT INTO users_tab (user_id, username, password, email, mobile_number) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getUserId());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getMobileNumber());

            int rows = pstmt.executeUpdate();

            // If user inserted successfully, insert OTP for verification
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

    /**
     * Validates the OTP entered by the user during registration.
     *
     * @param userId the user's ID
     * @param otp    the OTP entered by the user
     * @return true if OTP matches and hasn't expired, false otherwise
     */
    @Override
    public boolean validateOTP(String userId, String otp) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            // Select OTP record for given user where OTP is valid (not expired)
            String sql = "SELECT * FROM verification_codes_tab WHERE user_id = ? AND otp = ? AND expiration_time > SYSTIMESTAMP";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(userId));
            pstmt.setString(2, otp);

            ResultSet rs = pstmt.executeQuery();

            return rs.next(); // true if matching record found

        } catch (SQLException e) {
            System.out.println("OTP validation failed: " + e.getMessage());
        }
        return false;
    }

    /**
     * Authenticates the user using their ID and password.
     *
     * @param userId   the user's ID
     * @param password the user's password
     * @return true if credentials are valid, false otherwise
     */
    @Override
    public boolean login(String userId, String password) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            // Query for user credentials
            String sql = "SELECT * FROM users_tab WHERE user_id = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(userId));
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            return rs.next(); // true if user found with matching credentials

        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
        return false;
    }

    /**
     * Updates user profile information.
     *
     * @param user the user object with updated information
     * @return true if update is successful, false otherwise
     */
    @Override
    public boolean updateProfile(User user) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            // Update user record in users_tab
            String sql = "UPDATE users_tab SET username = ?, password = ?, email = ?, mobile_number = ? WHERE user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getMobileNumber());
            pstmt.setInt(5, user.getUserId());

            int rows = pstmt.executeUpdate();

            return rows > 0; // true if any row was updated

        } catch (SQLException e) {
            System.out.println("Profile update failed: " + e.getMessage());
        }
        return false;
    }
}

package com.nw.ezpay.user.service;

import com.nw.ezpay.user.dao.IUserDAO;
import com.nw.ezpay.user.dao.UserDAOImpl;
import com.nw.ezpay.user.model.User;

import java.util.Random;
import java.util.Scanner;

/**
 * UserServiceImpl provides the service-layer logic for user operations such as
 * registration, login, and profile update. It acts as a bridge between the
 * presentation/UI layer and the data access layer (DAO).
 */
public class UserServiceImpl implements IUserService {

    // DAO instance to interact with the database
    private IUserDAO userDAO = new UserDAOImpl();

    /**
     * Registers a user by collecting and verifying an OTP before inserting data.
     *
     * @param userIdStr     the user's ID as a String (converted to int internally)
     * @param username      the desired username
     * @param password      the desired password
     * @param email         the user's email address
     * @param mobileNumber  the user's mobile number
     * @return true if registration and OTP verification succeed, false otherwise
     */
    @Override
    public boolean register(String userIdStr, String username, String password, String email, String mobileNumber) {
        int userId = Integer.parseInt(userIdStr);

        // Generate a 4-digit OTP randomly
        String otp = String.format("%04d", new Random().nextInt(10000));
        System.out.println("Verification code sent: " + otp); // Simulating OTP send (in real apps, send via SMS/email)

        // Accept OTP from user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the verification code (printed above): ");
        String inputCode = scanner.nextLine();

        // Check if input OTP matches the generated one
        if (!otp.equals(inputCode)) {
            System.out.println("Verification failed!");
            return false;
        }

        // Create User object and delegate registration to DAO
        User user = new User(userId, username, password, email, mobileNumber);
        return userDAO.register(user, otp);
    }

    /**
     * Authenticates the user using their ID and password.
     *
     * @param userId   the user’s ID
     * @param password the user’s password
     * @return true if login is successful, false otherwise
     */
    @Override
    public boolean login(String userId, String password) {
        return userDAO.login(userId, password);
    }

    /**
     * Updates the user's profile information except for the mobile number.
     *
     * @param userIdStr the user’s ID as a String (converted to int)
     * @param username  the new username
     * @param password  the new password
     * @param email     the new email address
     * @return true if the update is successful, false otherwise
     */
    @Override
    public boolean updateProfile(String userIdStr, String username, String password, String email) {
        int userId = Integer.parseInt(userIdStr);

        // Mobile number is not being updated here, hence passing null
        User user = new User(userId, username, password, email, null);
        return userDAO.updateProfile(user);
    }
}

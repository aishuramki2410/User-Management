package com.nw.ezpay.user.controller;

import com.nw.ezpay.user.service.IUserService;
import com.nw.ezpay.user.service.UserServiceImpl;

import java.util.Scanner;

/**
 * UserController is the main class responsible for interacting with the user
 * via a console-based interface. It presents a menu that allows the user
 * to register, login, and update their profile.
 *
 * This controller acts as the presentation layer and delegates actual business
 * logic to the service layer (UserServiceImpl).
 *
 * @author Mehak
 */
public class UserController {

    // Service object to handle user operations such as registration, login, and profile updates
    private IUserService userService = new UserServiceImpl();

    // Scanner object for reading user input from the console
    private Scanner scanner = new Scanner(System.in);

    /**
     * Starts the EzPay menu loop and handles user input
     * for different operations like registration, login, and profile update.
     */
    public void start() {
        while (true) {
            System.out.println("\n--- EzPay Menu ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Update Profile");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    handleRegister();  // Calls method to register a user
                    break;
                case 2:
                    handleLogin();     // Calls method to log in a user
                    break;
                case 3:
                    handleUpdateProfile(); // Calls method to update user profile
                    break;
                case 4:
                    System.out.println("Exiting... Thank you for using EzPay!");
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 4.");
            }
        }
    }

    /**
     * Handles user registration by collecting input details like userId,
     * username, password, email, and mobile number from the user.
     * Delegates registration process to the service layer.
     */
    private void handleRegister() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Mobile Number: ");
        String mobileNumber = scanner.nextLine();

        boolean success = userService.register(userId, username, password, email, mobileNumber);
        System.out.println(success ? "Registration successful!" : "Registration failed.");
    }

    /**
     * Handles user login by taking userId and password from input
     * and verifying credentials through the service layer.
     */
    private void handleLogin() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        boolean success = userService.login(userId, password);
        System.out.println(success ? "Login successful!" : "Login failed.");
    }

    /**
     * Handles updating of user profile details like username,
     * password, and email. Delegates update operation to the service layer.
     */
    private void handleUpdateProfile() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter New Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter New Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter New Email: ");
        String email = scanner.nextLine();

        boolean success = userService.updateProfile(userId, username, password, email);
        System.out.println(success ? "Profile updated successfully!" : "Profile update failed.");
    }
}

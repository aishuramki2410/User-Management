package com.nw.ezpay.user.controller;

import com.nw.ezpay.user.service.IUserService;
import com.nw.ezpay.user.service.UserServiceImpl;

import java.util.Scanner;

public class UserController {

    private IUserService userService = new UserServiceImpl();
    private Scanner scanner = new Scanner(System.in);

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
                    handleRegister();
                    break;
                case 2:
                    handleLogin();
                    break;
                case 3:
                    handleUpdateProfile();
                    break;
                case 4:
                    System.out.println("Exiting... Thank you for using EzPay!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

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

    private void handleLogin() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        boolean success = userService.login(userId, password);
        System.out.println(success ? "Login successful!" : "Login failed.");
    }

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

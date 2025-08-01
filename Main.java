package com.nw.ezpay.user.app;

import com.nw.ezpay.user.service.IUserService;
import com.nw.ezpay.user.service.UserServiceImpl;

import java.util.Scanner;

/**
 * Main application class for the EzPay user service.
 * 
 * <p>This class provides a command-line interface for users to:</p>
 * <ul>
 *     <li>Register a new account</li>
 *     <li>Login to their account</li>
 *     <li>Update their profile details</li>
 * </ul>
 * 
 * <p>It uses {@link IUserService} as the service interface and {@link UserServiceImpl}
 * as the concrete implementation to perform user operations.</p>
 * 
 * <p>All user input is handled through the console using {@link Scanner}.</p>
 *
 * @author Aishwarya
 */
public class Main {

    public static void main(String[] args) {
        // Initialize service and scanner for user interaction
        IUserService userService = new UserServiceImpl();
        Scanner sc = new Scanner(System.in);

        // Main loop to show menu and handle choices
        while (true) {
            System.out.println("\nWelcome to EzPay");
            System.out.println("1. Register\n2. Login\n3. Update Profile\n4. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume leftover newline character

            switch (choice) {
                case 1:
                    // ----------- Registration ----------
                    System.out.print("User ID: ");
                    String userId = sc.nextLine();
                    System.out.print("Username: ");
                    String username = sc.nextLine();
                    System.out.print("Password: ");
                    String password = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Mobile Number: ");
                    String mobile = sc.nextLine();

                    boolean isRegistered = userService.register(userId, username, password, email, mobile);
                    if (isRegistered)
                        System.out.println("Registration successful!");
                    else
                        System.out.println("Registration failed!");
                    break;

                case 2:
                    // ----------- Login ----------
                    System.out.print("User ID: ");
                    String loginId = sc.nextLine();
                    System.out.print("Password: ");
                    String loginPwd = sc.nextLine();

                    boolean isLoggedIn = userService.login(loginId, loginPwd);
                    if (isLoggedIn)
                        System.out.println("Login successful!");
                    else
                        System.out.println("Login failed!");
                    break;

                case 3:
                    // ----------- Profile Update ----------
                    System.out.print("User ID: ");
                    String updateId = sc.nextLine();
                    System.out.print("New Username: ");
                    String newUsername = sc.nextLine();
                    System.out.print("New Password: ");
                    String newPassword = sc.nextLine();
                    System.out.print("New Email: ");
                    String newEmail = sc.nextLine();

                    boolean isUpdated = userService.updateProfile(updateId, newUsername, newPassword, newEmail);
                    if (isUpdated)
                        System.out.println("Profile updated successfully!");
                    else
                        System.out.println("Profile update failed!");
                    break;

                case 4:
                    // ----------- Exit ----------
                    System.out.println("Thank you for using EzPay!");
                    sc.close(); // Close the scanner resource before exiting
                    System.exit(0);
                    break;

                default:
                    // ----------- Invalid Choice ----------
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}

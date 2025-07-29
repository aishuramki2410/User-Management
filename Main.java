package com.nw.ezpay.user.app;

import com.nw.ezpay.user.service.IUserService;
import com.nw.ezpay.user.service.UserServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IUserService userService = new UserServiceImpl();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to EzPay");
            System.out.println("1. Register\n2. Login\n3. Update Profile\n4. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
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

                    if (userService.register(userId, username, password, email, mobile))
                        System.out.println("Registration successful!");
                    else
                        System.out.println("Registration failed!");
                    break;

                case 2:
                    System.out.print("User ID: ");
                    String loginId = sc.nextLine();
                    System.out.print("Password: ");
                    String loginPwd = sc.nextLine();

                    if (userService.login(loginId, loginPwd))
                        System.out.println("Login successful!");
                    else
                        System.out.println("Login failed!");
                    break;

                case 3:
                    System.out.print("User ID: ");
                    String updateId = sc.nextLine();
                    System.out.print("New Username: ");
                    String newUsername = sc.nextLine();
                    System.out.print("New Password: ");
                    String newPassword = sc.nextLine();
                    System.out.print("New Email: ");
                    String newEmail = sc.nextLine();

                    if (userService.updateProfile(updateId, newUsername, newPassword, newEmail))
                        System.out.println("Profile updated successfully!");
                    else
                        System.out.println("Profile update failed!");
                    break;

                case 4:
                    System.out.println("Thank you for using EzPay!");
                    sc.close(); // Close scanner before exiting
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}

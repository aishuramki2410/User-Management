package com.nw.ezpay.user.service;

import com.nw.ezpay.user.dao.IUserDAO;
import com.nw.ezpay.user.dao.UserDAOImpl;
import com.nw.ezpay.user.model.User;

import java.util.Random;
import java.util.Scanner;

public class UserServiceImpl implements IUserService {

    private IUserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean register(String userIdStr, String username, String password, String email, String mobileNumber) {
        int userId = Integer.parseInt(userIdStr);

        String otp = String.format("%04d", new Random().nextInt(10000));
        System.out.println("Verification code sent: " + otp);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the verification code (printed above): ");
        String inputCode = scanner.nextLine();

        if (!otp.equals(inputCode)) {
            System.out.println("Verification failed!");
            return false;
        }

        User user = new User(userId, username, password, email, mobileNumber);
        return userDAO.register(user, otp);
    }

    @Override
    public boolean login(String userId, String password) {
        return userDAO.login(userId, password);
    }

    @Override
    public boolean updateProfile(String userIdStr, String username, String password, String email) {
        int userId = Integer.parseInt(userIdStr);
        User user = new User(userId, username, password, email, null);
        return userDAO.updateProfile(user);
    }
}

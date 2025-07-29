package com.nw.ezpay.user.dao;

import com.nw.ezpay.user.model.User;

public interface IUserDAO {
    boolean register(User user, String otp);
    boolean validateOTP(String userId, String otp);
    boolean login(String userId, String password);
    boolean updateProfile(User user);
}

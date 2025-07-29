package com.nw.ezpay.user.service;

public interface IUserService {
    boolean register(String userId, String username, String password, String email, String mobileNumber);
    boolean login(String userId, String password);
    boolean updateProfile(String userId, String username, String password, String email);
}

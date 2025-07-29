package com.nw.ezpay.user.test;

import com.nw.ezpay.user.dao.IUserDAO;
import com.nw.ezpay.user.model.User;
import com.nw.ezpay.user.service.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private IUserDAO userDAO;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setup() {
        userService = new UserServiceImpl() {
            @Override
            protected Scanner getScanner() {
                return new Scanner("1234\n"); // Simulate OTP input
            }
        };
        userService = spy(userService);  // To allow overriding behavior
        doReturn(userDAO).when(userService).getUserDAO(); // Inject mocked DAO
    }

    @Test
    public void testRegisterSuccess() {
        User dummyUser = new User(1, "John", "pass123", "john@example.com", "9999999999");

        when(userDAO.register(any(User.class), eq("1234"))).thenReturn(true);

        boolean result = userService.register("1", "John", "pass123", "john@example.com", "9999999999");
        assertTrue(result);
    }

    @Test
    public void testLoginSuccess() {
        when(userDAO.login("1", "pass123")).thenReturn(true);
        assertTrue(userService.login("1", "pass123"));
    }

    @Test
    public void testLoginFailure() {
        when(userDAO.login("1", "wrongpass")).thenReturn(false);
        assertFalse(userService.login("1", "wrongpass"));
    }

    @Test
    public void testUpdateProfileSuccess() {
        when(userDAO.updateProfile(any(User.class))).thenReturn(true);
        assertTrue(userService.updateProfile("1", "NewName", "newPass", "new@mail.com"));
    }

    @Test
    public void testUpdateProfileFailure() {
        when(userDAO.updateProfile(any(User.class))).thenReturn(false);
        assertFalse(userService.updateProfile("1", "NewName", "newPass", "new@mail.com"));
    }
}

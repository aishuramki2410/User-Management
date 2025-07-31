package com.nw.ezpay.user.test;

import com.nw.ezpay.user.service.IUserService;
import com.nw.ezpay.user.service.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class IUserServiceTest {

    private IUserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
    }

    // Test: Login should fail for invalid credentials
    @Test
    void testLoginInvalidUser() {
        boolean result = userService.login("9999", "wrongpass");
        assertFalse(result, "Login should fail with wrong credentials");
    }

    // Parameterized Test: Login with multiple invalid credentials
    @ParameterizedTest
    @CsvSource({
        "1001, wrongpass",
        "9999, test123",
        "1234, 1234"
    })
    void testLoginWithMultipleCredentials(String userId, String password) {
        assertFalse(userService.login(userId, password), "Login should fail for invalid credentials");
    }

    @Test
    void testLoginWithNullInput() {
        assertThrows(NumberFormatException.class, () -> {
            userService.login(null, null);
        });
    }

    // Parameterized Test: Update profile with multiple values (invalid users assumed)
    @ParameterizedTest
    @CsvSource({
        "2001, user1, pass1, test1@mail.com",
        "2002, user2, pass2, test2@mail.com"
    })
    void testUpdateMultipleEmails(String id, String name, String pwd, String email) {
        boolean result = userService.updateProfile(id, name, pwd, email);
        assertFalse(result, "Update should fail for non-registered users");
    }
    
    @ParameterizedTest
    @CsvSource({
        "1001, updatedName1, newPass1, updated1@mail.com, true",
        "9999, ghostUser, pass123, ghost@mail.com, false"
    })
    void testUpdateProfileWithExpectedResult(String userId, String name, String password, String email, boolean expected) {
        boolean result = userService.updateProfile(userId, name, password, email);
        assertEquals(expected, result, "Profile update result didn't match expected outcome");
    }

}

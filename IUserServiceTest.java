package com.nw.ezpay.user.test;

import com.nw.ezpay.user.service.IUserService;
import com.nw.ezpay.user.service.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Unit test class for testing the {@link IUserService} implementation.
 * 
 * <p>This class uses JUnit 5 to test core functionalities of the UserServiceImpl, 
 * including login and profile update scenarios with valid and invalid input data.</p>
 * 
 * <p>These tests help ensure the correctness and robustness of business logic 
 * implemented in the IUserService.</p>
 * 
 * @author Yashwanth
 * @version 1.0
 */
public class IUserServiceTest {

    private IUserService userService;

    /**
     * Sets up the test fixture before each test method is executed.
     * Initializes the UserServiceImpl instance.
     */
    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
    }

    /**
     * Tests login functionality for invalid credentials.
     * Ensures the method returns false for incorrect userId/password.
     */
    @Test
    void testLoginInvalidUser() {
        boolean result = userService.login("9999", "wrongpass");
        assertFalse(result, "Login should fail with wrong credentials");
    }

    /**
     * Parameterized test: Tests login functionality with multiple invalid userId-password combinations.
     * 
     * @param userId   the user ID to test
     * @param password the password to test
     */
    @ParameterizedTest
    @CsvSource({
        "1001, wrongpass",
        "9999, test123",
        "1234, 1234"
    })
    void testLoginWithMultipleCredentials(String userId, String password) {
        assertFalse(userService.login(userId, password), "Login should fail for invalid credentials");
    }

    /**
     * Tests login method behavior when null input is passed.
     * Verifies that it throws NumberFormatException (or handle according to your logic).
     */
    @Test
    void testLoginWithNullInput() {
        assertThrows(NumberFormatException.class, () -> {
            userService.login(null, null);
        });
    }

    /**
     * Parameterized test: Tests updateProfile with unregistered user IDs.
     * Should return false indicating profile update failure.
     * 
     * @param id    user ID
     * @param name  user name
     * @param pwd   password
     * @param email email address
     */
    @ParameterizedTest
    @CsvSource({
        "2001, user1, pass1, test1@mail.com",
        "2002, user2, pass2, test2@mail.com",
        "2003, , pass3, test3@mail.com",                      // Empty name
        "2004, user4, , test4@mail.com",                      // Empty password
        "2005, user5, pass5, ",                               // Empty email
        "2006, user6, pass6, invalid-email-format",           // Invalid email
        "2007, user7, pass7, test7@invalid@domain.com",       // Multiple '@' signs
        "2008, verylonguseridexceedinglimit, user8, pass8, test8@mail.com" // Very long ID
    })
    void testUpdateMultipleEmails(String id, String name, String pwd, String email) {
        boolean result = userService.updateProfile(id, name, pwd, email);
        assertFalse(result, "Update should fail for non-registered or malformed input users");
    }
  
}

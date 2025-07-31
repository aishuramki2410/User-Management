package com.nw.ezpay.user.test;

import com.nw.ezpay.user.service.IUserService;
import com.nw.ezpay.user.service.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for testing the {@link IUserService} implementation.
 * 
 * <p>This class uses JUnit 5 to test core functionalities of the UserServiceImpl, 
 * including login and profile update scenarios with valid and invalid input data.</p>
 * 
 * <p>These tests help ensure the correctness and robustness of business logic 
 * implemented in the IUserService.</p>
 * 
 * @author [Your Name]
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
        "2002, user2, pass2, test2@mail.com"
    })
    void testUpdateMultipleEmails(String id, String name, String pwd, String email) {
        boolean result = userService.updateProfile(id, name, pwd, email);
        assertFalse(result, "Update should fail for non-registered users");
    }

    /**
     * Parameterized test: Validates profile update with expected result.
     * Helps test both valid and invalid scenarios in one test.
     * 
     * @param userId   user ID
     * @param name     user name
     * @param password password
     * @param email    email address
     * @param expected expected outcome of the update operation
     */
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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 * EzPaySystemTest is a JUnit test class for testing core functionalities
 * of the EzPay system, including user registration, login, verification, and profile management.
 */
public class EzPaySystemTest {

    private UserManagement userManagement;
    private ProfileManagement profileManagement;

    /*
     * This method is executed before each test case.
     * It initializes fresh instances of UserManagement and ProfileManagement
     * to ensure test isolation and avoid data contamination between tests.
     */
    @BeforeEach
    public void setup() {
        userManagement = new UserManagement();
        profileManagement = new ProfileManagement();
    }

    /*
     * Test successful user registration with unique email and mobile number.
     */
    @Test
    public void testUserRegistrationSuccess() {
        User user = new User("John", "john@example.com", "9876543210", "pass123");
        String result = userManagement.registerUser(user);
        assertEquals("Verification code sent.", result);
    }

    /*
     * Test registration failure when trying to register with an already registered email.
     */
    @Test
    public void testDuplicateEmailRegistration() {
        User user1 = new User("John", "john@example.com", "9876543210", "pass123");
        User user2 = new User("Jane", "john@example.com", "9123456789", "pass456");

        userManagement.registerUser(user1);
        String result = userManagement.registerUser(user2);

        assertEquals("Error: Email already registered.", result);
    }

    /*
     * Test successful user login using correct email and password.
     */
    @Test
    public void testLoginSuccess() {
        User user = new User("John", "john@example.com", "9876543210", "pass123");
        userManagement.registerUser(user);

        String loginResult = userManagement.login("john@example.com", "pass123");
        assertEquals("Login successful.", loginResult);
    }

    /*
     * Test login failure with unregistered email and incorrect password.
     */
    @Test
    public void testLoginFailure() {
        String result = userManagement.login("unknown@example.com", "wrongpass");
        assertEquals("Invalid credentials. Try again.", result);
    }

    /*
     * Test successful verification of a correct verification code.
     */
    @Test
    public void testVerifyCodeSuccess() {
        String result = userManagement.verifyCodeAndCreateUser("123456", "123456");
        assertEquals("Account created successfully.", result);
    }

    /*
     * Test failure when the entered verification code does not match the expected one.
     */
    @Test
    public void testVerifyCodeFailure() {
        String result = userManagement.verifyCodeAndCreateUser("000000", "123456");
        assertEquals("Error: Invalid verification code.", result);
    }

    /*
     * Test profile creation and successful update of name and address.
     */
    @Test
    public void testProfileCreationAndUpdate() {
        Profile profile = new Profile("john@example.com", "John", "Chennai");
        profileManagement.addProfile(profile);

        String updateResult = profileManagement.updateProfile("john@example.com", "Johnny", "Bangalore");

        assertEquals("Profile updated successfully: Name: Johnny, Address: Bangalore", updateResult);
    }

    /*
     * Test behavior when attempting to update a non-existing profile.
     */
    @Test
    public void testUpdateNonExistingProfile() {
        String result = profileManagement.updateProfile("unknown@example.com", "NewName", "NewAddress");
        assertEquals("Profile not found.", result);
    }
}

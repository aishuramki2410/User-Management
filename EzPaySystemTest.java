import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EzPaySystemTest {

    private UserManagement userManagement;
    private ProfileManagement profileManagement;

    @BeforeEach
    public void setup() {
        userManagement = new UserManagement();
        profileManagement = new ProfileManagement();
    }

    @Test
    public void testUserRegistrationSuccess() {
        User user = new User("John", "john@example.com", "9876543210", "pass123");
        String result = userManagement.registerUser(user);
        assertEquals("Verification code sent.", result);
    }

    @Test
    public void testDuplicateEmailRegistration() {
        User user1 = new User("John", "john@example.com", "9876543210", "pass123");
        User user2 = new User("Jane", "john@example.com", "9123456789", "pass456");
        userManagement.registerUser(user1);
        String result = userManagement.registerUser(user2);
        assertEquals("Error: Email already registered.", result);
    }

  
    @Test
    public void testLoginSuccess() {
        User user = new User("John", "john@example.com", "9876543210", "pass123");
        userManagement.registerUser(user);
        String loginResult = userManagement.login("john@example.com", "pass123");
        assertEquals("Login successful.", loginResult);
    }

    @Test
    public void testLoginFailure() {
        String result = userManagement.login("unknown@example.com", "wrongpass");
        assertEquals("Invalid credentials. Try again.", result);
    }

    @Test
    public void testVerifyCodeSuccess() {
        String result = userManagement.verifyCodeAndCreateUser("123456", "123456");
        assertEquals("Account created successfully.", result);
    }

    @Test
    public void testVerifyCodeFailure() {
        String result = userManagement.verifyCodeAndCreateUser("000000", "123456");
        assertEquals("Error: Invalid verification code.", result);
    }

    @Test
    public void testProfileCreationAndUpdate() {
        Profile profile = new Profile("john@example.com", "John", "Chennai");
        profileManagement.addProfile(profile);
        String updateResult = profileManagement.updateProfile("john@example.com", "Johnny", "Bangalore");
        assertEquals("Profile updated successfully: Name: Johnny, Address: Bangalore", updateResult);
    }

    @Test
    public void testUpdateNonExistingProfile() {
        String result = profileManagement.updateProfile("unknown@example.com", "NewName", "NewAddress");
        assertEquals("Profile not found.", result);
    }
}

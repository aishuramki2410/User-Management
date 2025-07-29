import java.util.HashMap;
import java.util.Map;

/*
 * The User class represents a user's core account information
 * in the EzPay system, including name, email, mobile number, and password.
 */
class User {
    private String name;
    private String email;
    private String mobile;
    private String password;

    /*
     * Constructor to create a new User object with necessary details.
     *
     * @param name     The full name of the user.
     * @param email    The unique email address of the user.
     * @param mobile   The mobile number of the user.
     * @param password The password used for login.
     */
    public User(String name, String email, String mobile, String password) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    /*
     * Getter for the user's email.
     *
     * @return The user's email address.
     */
    public String getEmail() {
        return email;
    }

    /*
     * Getter for the user's mobile number.
     *
     * @return The user's mobile number.
     */
    public String getMobile() {
        return mobile;
    }

    /*
     * Getter for the user's password.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }
}

/*
 * The UserManagement class handles user registration, login,
 * and verification processes in the EzPay system.
 */
public class UserManagement {

    /*
     * A map to store registered users.
     * The key is the user's email, and the value is the corresponding User object.
     */
    private Map<String, User> registeredUsers = new HashMap<>();

    /*
     * Registers a new user in the system.
     * Checks for duplicate email and mobile number before registration.
     *
     * @param user The User object to be registered.
     * @return A message indicating success or the type of error encountered.
     */
    public String registerUser(User user) {
        // Check if the email is already registered
        if (registeredUsers.containsKey(user.getEmail())) {
            return "Error: Email already registered.";
        }

        // Check if the mobile number already exists (simulated by checking mobile in values)
        for (User u : registeredUsers.values()) {
            if (u.getMobile().equals(user.getMobile())) {
                return "Error: Mobile number already registered.";
            }
        }

        // Register the user using email as the key
        registeredUsers.put(user.getEmail(), user);
        return "Verification code sent.";
    }

    /*
     * Verifies the input verification code against the expected one.
     * This is a simplified implementation and not linked to any actual user.
     *
     * @param code         The code entered by the user.
     * @param expectedCode The expected correct code.
     * @return A message indicating success or invalid code.
     */
    public String verifyCodeAndCreateUser(String code, String expectedCode) {
        if (!code.equals(expectedCode)) {
            return "Error: Invalid verification code.";
        }
        return "Account created successfully.";
    }

    /*
     * Validates user login credentials.
     *
     * @param email    The user's email address.
     * @param password The user's password.
     * @return A success or failure message.
     */
    public String login(String email, String password) {
        User user = registeredUsers.get(email);

        if (user != null && user.getPassword().equals(password)) {
            return "Login successful.";
        }
        return "Invalid credentials. Try again.";
    }

    /*
     * Checks if a user is already registered by email.
     *
     * @param email The email address to check.
     * @return true if the user is registered, false otherwise.
     */
    public boolean isUserRegistered(String email) {
        return registeredUsers.containsKey(email);
    }
}

import java.util.HashMap;
import java.util.Map;

class User {
    private String name;
    private String email;
    private String mobile;
    private String password;

    public User(String name, String email, String mobile, String password) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    public String getEmail() { return email; }
    public String getMobile() { return mobile; }
    public String getPassword() { return password; }
}

public class UserManagement {
    private Map<String, User> registeredUsers = new HashMap<>();

    public String registerUser(User user) {
        if (registeredUsers.containsKey(user.getEmail())) {
            return "Error: Email already registered.";
        }
        if (registeredUsers.containsKey(user.getMobile())) {
            return "Error: Mobile number already registered.";
        }
        registeredUsers.put(user.getEmail(), user);
        return "Verification code sent.";
    }

    public String verifyCodeAndCreateUser(String code, String expectedCode) {
        if (!code.equals(expectedCode)) {
            return "Error: Invalid verification code.";
        }
        return "Account created successfully.";
    }

    public String login(String email, String password) {
        User user = registeredUsers.get(email);
        if (user != null && user.getPassword().equals(password)) {
            return "Login successful.";
        }
        return "Invalid credentials. Try again.";
    }

    public boolean isUserRegistered(String email) {
        return registeredUsers.containsKey(email);
    }
}
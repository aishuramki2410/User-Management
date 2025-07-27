import java.util.HashMap;
import java.util.Map;

class Profile {
    private String email;
    private String name;
    private String address;
    // Constructor
    public Profile(String email, String name, String address) {
        this.email = email;
        this.name = name;
        this.address = address;
    }
    // Getters and Setters
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public String getEmail() { return email; }

    public String toString() {
        return "Name: " + name + ", Address: " + address;
    }
}

public class ProfileManagement {
    private Map<String, Profile> profileMap = new HashMap<>();
    // Add new profile
    public void addProfile(Profile profile) {
        profileMap.put(profile.getEmail(), profile);
    }
    // Update existing profile
    public String updateProfile(String email, String newName, String newAddress) {
        Profile profile = profileMap.get(email);
        if (profile != null) {
            profile.setName(newName);
            profile.setAddress(newAddress);
            return "Profile updated successfully: " + profile.toString();
        }
        return "Profile not found.";
    }
}

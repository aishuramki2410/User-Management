import java.util.HashMap;
import java.util.Map;

/*
 * The Profile class represents user profile data in the EzPay system.
 * It contains the user's email (used as a unique identifier), name, and address.
 */
class Profile {
    private String email;
    private String name;
    private String address;

    /*
     * Constructor to initialize a new Profile instance.
     *
     * @param email   The email address associated with the profile (unique key).
     * @param name    The name of the user.
     * @param address The address of the user.
     */
    public Profile(String email, String name, String address) {
        this.email = email;
        this.name = name;
        this.address = address;
    }

    /*
     * Sets a new name for the profile.
     *
     * @param name The new name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * Sets a new address for the profile.
     *
     * @param address The new address to be set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /*
     * Returns the email associated with this profile.
     *
     * @return The email (used as the key in ProfileManagement).
     */
    public String getEmail() {
        return email;
    }

    /*
     * Returns a string representation of the profile.
     *
     * @return A formatted string containing name and address.
     */
    public String toString() {
        return "Name: " + name + ", Address: " + address;
    }
}

/*
 * The ProfileManagement class handles creation and updates of user profiles.
 * It uses a HashMap to store and retrieve profiles based on user email.
 */
public class ProfileManagement {

    // A map that holds email as key and Profile object as value.
    private Map<String, Profile> profileMap = new HashMap<>();

    /*
     * Adds a new profile to the system.
     * If a profile with the same email already exists, it will be overwritten.
     *
     * @param profile The Profile object to be added.
     */
    public void addProfile(Profile profile) {
        profileMap.put(profile.getEmail(), profile);
    }

    /*
     * Updates an existing profile's name and address.
     *
     * @param email       The email of the user whose profile needs to be updated.
     * @param newName     The new name to update.
     * @param newAddress  The new address to update.
     * @return A success message if updated, or an error message if the profile is not found.
     */
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

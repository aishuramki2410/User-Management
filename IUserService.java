package com.nw.ezpay.user.service;

/**
 * IUserService defines the service-level operations related to user management
 * in the EZPay application.
 * 
 * <p>This interface is responsible for encapsulating business logic related to 
 * user registration, login, and profile updates. Implementations of this interface 
 * are typically called by controllers or other higher-level components to perform 
 * user-related operations.</p>
 * 
 * <p>Each method takes user data as input and returns a boolean value indicating 
 * success or failure of the operation.</p>
 * 
 * @author Amirdha
 * @version 1.0
 */
public interface IUserService {

    /**
     * Registers a new user with the system.
     *
     * @param userId       Unique identifier for the user.
     * @param username     The user's display name.
     * @param password     The user's password (should be encrypted/stored securely).
     * @param email        The user's email address.
     * @param mobileNumber The user's mobile number.
     * @return true if registration is successful, false otherwise.
     */
    boolean register(String userId, String username, String password, String email, String mobileNumber);

    /**
     * Authenticates a user based on provided credentials.
     *
     * @param userId   The ID of the user attempting to log in.
     * @param password The password provided by the user.
     * @return true if authentication succeeds, false otherwise.
     */
    boolean login(String userId, String password);

    /**
     * Updates profile information for an existing user.
     *
     * @param userId   Unique identifier of the user.
     * @param username New or updated username.
     * @param password New or updated password.
     * @param email    New or updated email address.
     * @return true if the profile update is successful, false otherwise.
     */
    boolean updateProfile(String userId, String username, String password, String email);
}

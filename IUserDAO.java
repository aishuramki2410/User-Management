package com.nw.ezpay.user.dao;

import com.nw.ezpay.user.model.User;

/**
 * IUserDAO is an interface that defines the Data Access Object (DAO)
 * operations for user-related functionalities in the EZPay application.
 * 
 * <p>This interface encapsulates methods for user registration,
 * authentication, OTP validation, and profile updates. Implementations
 * of this interface are expected to handle database interaction for the 
 * corresponding user operations.</p>
 * 
 * <p>Common use cases include registering a new user, verifying login credentials,
 * validating a one-time password (OTP), and updating user information.</p>
 * 
 * @version 1.0
 * @author Mehak
 */
public interface IUserDAO {

    /**
     * Registers a new user in the system with the provided OTP.
     *
     * @param user The User object containing registration information.
     * @param otp  The OTP associated with the user for verification.
     * @return true if registration is successful, false otherwise.
     */
    boolean register(User user, String otp);

    /**
     * Validates the OTP entered by the user against the stored value.
     *
     * @param userId The ID of the user attempting OTP validation.
     * @param otp    The OTP to validate.
     * @return true if the OTP is valid, false otherwise.
     */
    boolean validateOTP(String userId, String otp);

    /**
     * Verifies user credentials and allows login if authentication succeeds.
     *
     * @param userId   The ID of the user attempting to log in.
     * @param password The password provided by the user.
     * @return true if login is successful, false otherwise.
     */
    boolean login(String userId, String password);

    /**
     * Updates the profile information of an existing user.
     *
     * @param user The User object containing updated profile details.
     * @return true if the update is successful, false otherwise.
     */
    boolean updateProfile(User user);
}

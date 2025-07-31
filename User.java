package com.nw.ezpay.user.model;

/**
 * Represents a User in the EzPay system.
 * 
 * <p>This model class holds the necessary user information for registration,
 * login, and profile update functionality.</p>
 */
public class User {

    // -------------------- Fields --------------------

    /** Unique integer ID of the user */
    private int userId;

    /** Username selected by the user */
    private String username;

    /** Password associated with the user account */
    private String password;

    /** Email address of the user */
    private String email;

    /** Mobile phone number of the user */
    private String mobileNumber;


    // -------------------- Constructors --------------------

    /**
     * Default constructor used for object creation without preset values.
     */
    public User() {}

    /**
     * Parameterized constructor for creating a user with all fields initialized.
     *
     * @param userId the unique user ID
     * @param username the chosen username
     * @param password the user's password
     * @param email the user's email address
     * @param mobileNumber the user's mobile number
     */
    public User(int userId, String username, String password, String email, String mobileNumber) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }


    // -------------------- Getters and Setters --------------------

    /**
     * Gets the user's ID.
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user's ID.
     * @param userId the new user ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the username.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email address.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address.
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the mobile number.
     * @return mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Sets the mobile number.
     * @param mobileNumber the new mobile number
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}

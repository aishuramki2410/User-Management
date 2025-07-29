import java.util.Scanner;
/*
 * Main class for the EzPay Real-Time System.
 * This application allows users to register, login, and manage their profiles.
 * The system is run via a simple command-line interface (CLI).
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /*
         * User and Profile management instances are created to handle
         * operations related to user registration, login, and profile handling.
         */
        UserManagement userManagement = new UserManagement();
        ProfileManagement profileManagement = new ProfileManagement();

        /*
         * A static verification code is used here for demonstration purposes.
         * In a real system, this would typically be dynamically generated and sent to the user's email or phone.
         */
        final String VERIFICATION_CODE = "123456";

        // Variable to track currently logged-in user's email
        String loggedInEmail = null;

        // Control variable to keep the program running
        boolean run = true;

        /*
         * The main loop that keeps the application running
         * until the user decides to exit.
         */
        while (run) {
            // Display menu options to the user
            System.out.println("\n--- EzPay Real-Time System ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Create/Update Profile");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();  // Read user's menu choice
            sc.nextLine(); // Consume newline left by nextInt()

            switch (choice) {
                case 1:
                    /*
                     * --------- USER REGISTRATION BLOCK ----------
                     * Prompts the user for registration details,
                     * then creates a new User object and attempts to register it.
                     * If successful, asks the user to input a verification code.
                     */
                    System.out.println("\n--- User Registration ---");
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Mobile: ");
                    String mobile = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    // Create a new user with the entered information
                    User user = new User(name, email, mobile, password);

                    // Attempt to register the user
                    String regRes = userManagement.registerUser(user);
                    System.out.println(regRes);

                    // If registration is successful, proceed with code verification
                    if (!regRes.startsWith("Error")) {
                        System.out.print("Enter Verification Code (expected: " + VERIFICATION_CODE + "): ");
                        String code = sc.nextLine();

                        // Validate the verification code and finalize registration
                        System.out.println(userManagement.verifyCodeAndCreateUser(code, VERIFICATION_CODE));
                    }
                    break;

                case 2:
                    /*
                     * --------- USER LOGIN BLOCK ----------
                     * Prompts the user for email and password,
                     * and attempts to log them in.
                     * On success, updates the loggedInEmail variable.
                     */
                    System.out.println("\n--- User Login ---");
                    System.out.print("Enter Email: ");
                    String loginEmail = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String loginPass = sc.nextLine();

                    // Authenticate the user
                    String loginRes = userManagement.login(loginEmail, loginPass);
                    System.out.println(loginRes);

                    // If login succeeds, store email of logged-in user
                    if (loginRes.equals("Login successful.")) {
                        loggedInEmail = loginEmail;
                    }
                    break;

                case 3:
                    /*
                     * --------- PROFILE CREATION/UPDATE BLOCK ----------
                     * Only accessible if a user is logged in.
                     * Prompts for profile info and either creates or updates it.
                     */
                    if (loggedInEmail == null) {
                        System.out.println("Please login first to manage your profile.");
                        break;
                    }

                    System.out.println("\n--- Profile Update ---");
                    System.out.print("Enter Name: ");
                    String profileName = sc.nextLine();
                    System.out.print("Enter Address: ");
                    String address = sc.nextLine();

                    // Create or overwrite the profile for the logged-in user
                    Profile profile = new Profile(loggedInEmail, profileName, address);
                    profileManagement.addProfile(profile);
                    System.out.println("Profile created successfully.");

                    // Offer option to immediately update the profile
                    System.out.print("Do you want to update your profile now? (yes/no): ");
                    String updateChoice = sc.nextLine();

                    if (updateChoice.equalsIgnoreCase("yes")) {
                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter New Address: ");
                        String newAddress = sc.nextLine();

                        // Update existing profile with new values
                        System.out.println(profileManagement.updateProfile(loggedInEmail, newName, newAddress));
                    }
                    break;

                default:
                    /*
                     * Handles cases where the user inputs a menu option
                     * that does not correspond to 1, 2, or 3.
                     */
                    System.out.println("Invalid choice. Please try again.");
            }

            /*
             * Ask the user if they wish to continue using the application.
             * If not, the loop terminates and the application exits.
             */
            System.out.print("\nDo you want to continue? (yes/no): ");
            String cont = sc.nextLine();

            if (!cont.equalsIgnoreCase("yes")) {
                run = false;
                System.out.println("Exiting EzPay. Thank you!");
            }
        }

        // Close the scanner resource to avoid memory leaks
        sc.close();
    }
}

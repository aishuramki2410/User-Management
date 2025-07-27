import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserManagement userManagement = new UserManagement();
        ProfileManagement profileManagement = new ProfileManagement();
        final String VERIFICATION_CODE = "123456";

        String loggedInEmail = null;
        boolean run = true;

        while (run) {
            System.out.println("\n--- EzPay Real-Time System ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Create/Update Profile");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    // User Registration
                    System.out.println("\n--- User Registration ---");
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Mobile: ");
                    String mobile = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    User user = new User(name, email, mobile, password);
                    String regRes = userManagement.registerUser(user);
                    System.out.println(regRes);
                   // If registration succeeded, verify code
                    if (!regRes.startsWith("Error")) {
                        System.out.print("Enter Verification Code (expected: " + VERIFICATION_CODE + "): ");
                        String code = sc.nextLine();
                        System.out.println(userManagement.verifyCodeAndCreateUser(code, VERIFICATION_CODE));
                    }
                    break;

                case 2:
                    // User Login
                    System.out.println("\n--- User Login ---");
                    System.out.print("Enter Email: ");
                    String loginEmail = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String loginPass = sc.nextLine();

                    String loginRes = userManagement.login(loginEmail, loginPass);
                    System.out.println(loginRes);
                    if (loginRes.equals("Login successful.")) {
                        loggedInEmail = loginEmail;
                    }
                    break;

                case 3:
                    // Profile creation/update (only after login)
                    if (loggedInEmail == null) {
                        System.out.println("Please login first to manage your profile.");
                        break;
                    }

                    System.out.println("\n--- Profile Update ---");
                    System.out.print("Enter Name: ");
                    String profileName = sc.nextLine();
                    System.out.print("Enter Address: ");
                    String address = sc.nextLine();

                    Profile profile = new Profile(loggedInEmail, profileName, address);
                    profileManagement.addProfile(profile);
                    System.out.println("Profile created successfully.");
                    // Ask if user wants to update
                    System.out.print("Do you want to update your profile now? (yes/no): ");
                    String updateChoice = sc.nextLine();
                    if (updateChoice.equalsIgnoreCase("yes")) {
                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter New Address: ");
                        String newAddress = sc.nextLine();
                        System.out.println(profileManagement.updateProfile(loggedInEmail, newName, newAddress));
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            // Ask if user wants to continue
            System.out.print("\nDo you want to continue? (yes/no): ");
            String cont = sc.nextLine();
            if (!cont.equalsIgnoreCase("yes")) {
                run = false;
                System.out.println("Exiting EzPay. Thank you!");
            }
        }
        sc.close();
    }
}


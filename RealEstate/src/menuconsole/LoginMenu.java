package menuconsole;

import model.User;
import java.util.Scanner;

public class LoginMenu {

    private UserService userService;

    public LoginMenu(UserService userService) {
        this.userService = userService;
    }

    // Method to display the login menu and handle user login
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Login Menu ---");
        System.out.print("Enter User ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = readPassword(scanner); // Use custom method to read password with stars

        // Attempt to log in
        User user = userService.login(id, password);

        if (user != null) {
            System.out.println("Login successful! Welcome, " + user.getFirstName() + "!");
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    // Method to read password with masking (stars)
    private String readPassword(Scanner scanner) {
        String password = "";
        try {
            // Using System.console() to mask the input
            if (System.console() == null) {
                // If console isn't available, use the standard Scanner input (for IDEs)
                password = scanner.nextLine();
            } else {
                password = new String(System.console().readPassword());
            }
        } catch (Exception e) {
            System.out.println("Error reading password.");
        }
        return password;
    }
}

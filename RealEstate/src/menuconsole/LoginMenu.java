package menuconsole;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Console;
import java.io.IOException;
import utils.ConsoleUtils;

public class LoginMenu {

    // File paths for storing client and worker data
    private static final String CLIENT_FILE = "clients.csv";
    private static final String WORKER_FILE = "workers.csv";

    // Method to display the login menu
    public static void login() {
        // Clear the console and print the menu title
        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("Login Menu");

        // Obtain a console instance for secure password input
        Console console = System.console();
        if (console == null) {
            System.out.println("No console available. Password masking will not work.");
            return;
        }

        // Prompt the user for email and password
        String email = console.readLine("Enter your email: ");
        char[] passwordArray = console.readPassword("Enter your password: ");
        String password = new String(passwordArray);

        // Authenticate the user based on provided credentials
        if (authenticateUser(email, password)) {
            // Check if the user is a worker or a client and direct to the appropriate menu
            if (isWorker(email)) {
                // TODO: Implement WorkerMenu and call its display method
                System.out.println("Redirecting to Worker Menu...");
            } else {
                // TODO: Implement ClientMenu and call its display method
                System.out.println("Redirecting to Client Menu...");
            }
        } else {
            // Display an error message for invalid credentials
            System.out.println("Invalid email or password. Please try again or register if you don't have an account.");
            ConsoleUtils.pause("Press Enter to continue...");
        }
    }

    // Method to authenticate the user by checking credentials in the client file
    private static boolean authenticateUser(String email, String password) {
        return checkCredentials(email, password, CLIENT_FILE);
    }

    // Method to check credentials against a specified file
    private static boolean checkCredentials(String email, String password, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read each line from the file and split by comma
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                // Check if the email and password match
                if (fields.length >= 2 && fields[0].equals(email) && fields[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            // Handle file reading errors
            System.out.println("Error reading file: " + filePath);
        }
        return false;
    }

    // Method to determine if a user is a worker by checking the worker file
    private static boolean isWorker(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(WORKER_FILE))) {
            String line;
            // Read each line from the worker file and check if the email exists
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 1 && fields[0].equals(email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            // Handle case when the worker file is missing
            System.out.println("Worker file not found. Please contact the administrator.");
        }
        return false;
    }
}

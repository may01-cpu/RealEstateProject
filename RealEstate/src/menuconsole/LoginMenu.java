/*package menuconsole;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import utils.ConsoleUtils;

public class LoginMenu {

    // File paths for storing client and worker data
    private static final String CLIENT_FILE = "recources/clients.csv"; // Assuming this is in the root of the project
    private static final String WORKER_FILE = "recources/worker.csv"; // Assuming this is in resources inside real_estate

    // Method to display the login menu
    public static void login() {
        // Clear the console and print the menu title
        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("Login Menu");

        // Use Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for email and password
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

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

    // Method to check credentials against a specified file
    private static boolean checkCredentials(String email, String password, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read each line from the file and split by comma
            while ((line = reader.readLine()) != null) {
                String[] fields = line.trim().split(",");
                // Check if the email (4th field) and password (6th field) match
                if (fields.length >= 6 && fields[3].trim().equals(email.trim()) && fields[5].trim().equals(password.trim())) {
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
                String[] fields = line.trim().split(",");
                if (fields.length >= 1 && fields[0].trim().equals(email.trim())) {
                    return true;
                }
            }
        } catch (IOException e) {
            // Handle case when the worker file is missing
            System.out.println("Worker file not found. Please contact the administrator.");
        }
        return false;
    }
}*/
package menuconsole;

import model.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Client;
import model.Worker;
import service.AppointmentService;
import service.PropertyService;
import service.TransactionService;
import utils.ConsoleUtils;
import service.ClientService;

public class LoginMenu {

    private static final String CLIENT_FILE = "recources/clients.csv"; // Assuming this is in the root of the project
    private static final String WORKER_FILE = "recources/worker.csv"; // Assuming this is in resources inside real_estate


            // Method to log in
            public void login() {

                    // Clear the console and print the menu title
                    ConsoleUtils.clearConsole();
                    ConsoleUtils.printTitle("Login Menu");

                    // Use Scanner for user input
                    Scanner scanner = new Scanner(System.in);

                    // Prompt the user for email and password
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();


                // Try to authenticate as client first
                Client client = authenticateClient(email, password);

                    if (client != null) {
                        System.out.println("Login successful! Welcome, " + client.getFirstName());
                        clientMenu(client);
                    } else {
                        System.out.println("Invalid credentials. Please try again.");
                        // If client login fails, try worker login
                        Worker worker = authenticateWorker(email, password);
                        if (worker != null) {
                            System.out.println("Login successful! Welcome, Worker " + worker.getFirstName());
                            workerMenu(worker);
                        } else {
                            System.out.println("Login failed. Invalid email/phone number or password.");
                        }
                    }
                }
                  // Method to authenticate client using email or phone number
                            private Client authenticateClient(String email, String password) {
                                ClientService clientService = new ClientService();
                                List<Client> clients = clientService.loadClientsFromFile();
                                return clients.stream()
                                        .filter(client -> (client.getEmail().equals(email)) && client.getPassword().equals(password))
                                        .findFirst()
                                        .orElse(null);
                            }
                            // Method to authenticate worker using email or phone number
                            private Worker authenticateWorker(String email, String password) {
                                List<Worker> workers = new ArrayList<>();
                                File workerP = new File(WORKER_FILE);
                                try (BufferedReader reader = new BufferedReader(new FileReader(workerP))) {
                                    String line;
                                    while ((line = reader.readLine()) != null) {
                                        String[] fields = line.split(",");
                                        if (fields.length == 6) {
                                            Worker worker = new Worker(
                                                    fields[0],  // id
                                                    fields[1],  // first name
                                                    fields[2],  // last name
                                                    fields[3],  // email
                                                    fields[4],  // phone number
                                                    fields[5]  // password
                                            );
                                            workers.add(worker);
                                        }
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return workers.stream()
                                        .filter(worker -> (worker.getEmail().equals(email)) && worker.getPassword().equals(password))
                                        .findFirst()
                                        .orElse(null);
                            }

                            // Display the client menu after login
                            private void clientMenu(Client client) {
                                System.out.println("\nWelcome, " + client.getFirstName() + "!");
                                System.out.println("1. Client Menu");
                                System.out.println("2. View Profile");
                                System.out.println("3. Logout");
                                Scanner scanner = new Scanner(System.in);
                                int choice = scanner.nextInt();
                                switch (choice) {
                                    case 1:
                                        ClientMenu clientmenu = getClientMenu(client);
                                        clientmenu.displayMenu();
                                        break;
                                    case 2:
                                        viewProfile(client);
                                        break;
                                    case 3:
                                        System.out.println("Logging out...");
                                        break;
                                    default:
                                        System.out.println("Invalid choice.");
                                }
                            }
    private static ClientMenu getClientMenu(Client client) {
        PropertyService propertyService = new PropertyService();
        AppointmentService appointmentService = new AppointmentService();
        ClientService clientService = new ClientService();
        TransactionService transactionService = new TransactionService();
        return new ClientMenu(propertyService, appointmentService, clientService, transactionService, client);
    }
                            // Display the worker menu after login
                            private void workerMenu(Worker worker) {
                                System.out.println("\nWelcome, Worker " + worker.getFirstName() + "!");
                                System.out.println("1. View Worker Dashboard");
                                System.out.println("2. View Profile");
                                System.out.println("3. Logout");
                                Scanner scanner = new Scanner(System.in);
                                int choice = scanner.nextInt();
                                switch (choice) {
                                    case 1:
                                        WorkerMenu workermenu = new WorkerMenu();
                                        break;
                                    case 2:
                                        viewProfile(worker);
                                        break;
                                    case 3:
                                        System.out.println("Logging out...");
                                        break;
                                    default:
                                        System.out.println("Invalid choice.");
                                }
                            }
                            // Method to view profile
                            private void viewProfile(User user) {
                                System.out.println("Profile Info:");
                                System.out.println("ID: " + user.getId());
                                System.out.println("Name: " + user.getFirstName() + " " + user.getLastName());
                                System.out.println("Email: " + user.getEmail());
                                System.out.println("Phone: " + user.getPhoneNumber());
                            }
                        }
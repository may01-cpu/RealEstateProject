package menuconsole;

import java.io.*;
import java.util.*;
import model.Client;
import model.ClientType;
import model.UserType;

public class LoginMenu {

    private static final String CLIENT_FILE = "clients.csv";  // The CSV file for storing clients
    private static final String WORKER_FILE = "workers.csv";  // The CSV file for storing workers

    // Method to log in
    public void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Login Menu!");
        System.out.print("Enter Email or Phone Number: ");
        String identifier = scanner.nextLine();

        // Ask for password
        System.out.print("Enter Password: ");
        String password = scanner.nextLine(); // readpassword mamchatch

        // Try to authenticate as client first
        Client client = authenticateClient(identifier, password);

        if (client != null) {
            System.out.println("Login successful! Welcome, " + client.getFirstName());
            clientMenu(client);
        } else {
            // If client login fails, try worker login
            Client worker = authenticateWorker(identifier, password);
            if (worker != null) {
                System.out.println("Login successful! Welcome, Worker " + worker.getFirstName());
                workerMenu(worker);
            } else {
                System.out.println("Login failed. Invalid email/phone number or password.");
            }
        }
    }

    // Method to authenticate client using email or phone number
    private Client authenticateClient(String identifier, String password) {
        List<Client> clients = loadClientsFromFile(CLIENT_FILE);
        return clients.stream()
                .filter(client -> (client.getEmail().equals(identifier) || client.getPhoneNumber().equals(identifier))
                        && client.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    // Method to authenticate worker using email or phone number
    private Client authenticateWorker(String identifier, String password) {
        List<Client> workers = loadClientsFromFile(WORKER_FILE);
        return workers.stream()
                .filter(worker -> (worker.getEmail().equals(identifier) || worker.getPhoneNumber().equals(identifier))
                        && worker.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    // Read password with masking as ***
    private String readPassword() {
        Console console = System.console();
        if (console == null) {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();  // Default way if console is not available
        }
        char[] passwordArray = console.readPassword("Enter your password: ");
        return new String(passwordArray);
    }

    // Method to load clients from a file
    private List<Client> loadClientsFromFile(String fileName) {
        List<Client> clients = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            return clients;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 8) {
                    Client client = new Client(
                            fields[0],  // id
                            fields[1],  // first name
                            fields[2],  // last name
                            fields[3],  // email
                            fields[4],  // phone number
                            fields[5],  // password
                            UserType.valueOf(fields[6]),  // user type
                            ClientType.valueOf(fields[7])  // client type
                    );
                    clients.add(client);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clients;
    }

    // Display the client menu after login
    private void clientMenu(Client client) {
        System.out.println("\nWelcome, " + client.getFirstName() + "!");
        System.out.println("1. View Profile");
        System.out.println("2. Update Profile");
        System.out.println("3. Logout");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                viewProfile(client);
                break;
            case 2:
                updateProfile(client);
                break;
            case 3:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Display the worker menu after login
    private void workerMenu(Client worker) {
        System.out.println("\nWelcome, Worker " + worker.getFirstName() + "!");
        System.out.println("1. View Worker Dashboard");
        System.out.println("2. View Profile");
        System.out.println("3. Logout");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // Add worker dashboard features here
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
    private void viewProfile(Client client) {
        System.out.println("Profile Info:");
        System.out.println("ID: " + client.getId());
        System.out.println("Name: " + client.getFirstName() + " " + client.getLastName());
        System.out.println("Email: " + client.getEmail());
        System.out.println("Phone: " + client.getPhoneNumber());
        System.out.println("Client Type: " + client.getType());
    }

    // Method to update profile
    private void updateProfile(Client client) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What would you like to update?");
        System.out.println("1. Email");
        System.out.println("2. Phone Number");
        System.out.println("3. Password");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter new email: ");
                client.setEmail(scanner.nextLine());
                break;
            case 2:
                System.out.print("Enter new phone number: ");
                client.setPhoneNumber(scanner.nextLine());
                break;
            case 3:
                System.out.print("Enter new password: ");
                client.setPassword(scanner.nextLine());
                break;
            default:
                System.out.println("Invalid choice.");
        }

        System.out.println("Profile updated successfully.");
    }
}

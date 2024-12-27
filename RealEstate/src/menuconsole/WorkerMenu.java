package menuconsole;

import model.Client;
import model.ClientType;
import model.UserType;
import model.Agent;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkerMenu {

    private Agent agent;
    private ArrayList<Client> clients;

    public WorkerMenu() {
        // Initialize the agent and clients list
        this.agent = new Agent("Alice", "Johnson", "alice@realestate.com", "987654321", "password", "A123", "XYZ Realty");
        this.clients = new ArrayList<>();
    }

    // Method to display the worker menu and handle operations
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Worker Menu ---");
            System.out.println("1. Add Client");
            System.out.println("2. Assign Property to Client");
            System.out.println("3. Display Clients");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after the int input

            switch (choice) {
                case 1:
                    addClient(scanner);
                    break;
                case 2:
                    assignPropertyToClient(scanner);
                    break;
                case 3:
                    displayClients();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // Method to add a new client
    private void addClient(Scanner scanner) {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNum = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Client ID: ");
        String id = scanner.nextLine();

        System.out.println("Select Client Type (1. BAILLEUR, 2. ACHETEUR, 3. LOCATAIRE, 4. VENDEUR): ");
        int clientTypeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        ClientType clientType = ClientType.values()[clientTypeChoice - 1]; // Get ClientType
        UserType userType = UserType.CLIENT; // Use UserType.CLIENT

        // Create and add the client to the list
        Client newClient = new Client(id, firstName, lastName, email, phoneNum, password, userType, clientType);
        clients.add(newClient);
        System.out.println("Client added successfully!");
    }


    // Method to assign a property to a client
    private void assignPropertyToClient(Scanner scanner) {
        System.out.println("Enter the Client ID to assign a property:");
        String clientId = scanner.nextLine();

        Client client = null;
        for (Client c : clients) {
            if (c.getId().equals(clientId)) {
                client = c;
                break;
            }
        }

        if (client != null) {
            System.out.print("Enter Property Details: ");
            String property = scanner.nextLine();
            agent.assignProperty(client, property);
        } else {
            System.out.println("Client not found!");
        }
    }

    // Method to display all clients
    private void displayClients() {
        System.out.println("\n--- Clients List ---");
        for (Client client : clients) {
            System.out.println(client.getFirstName() + " " + client.getLastName() + " - Type: " + client.getType());
        }
    }
}

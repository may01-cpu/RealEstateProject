package service;

import model.Client;
import model.ClientType;
import model.UserType;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ClientService {

    private static final String CLIENT_FILE = "clients.csv";  // CSV file to store clients

    // Method to add a new client
    public void addClient() {
        Scanner scanner = new Scanner(System.in);

        // Collect client details
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Client Type (BUYER, TENANT, SELLER, LANDLORD): ");
        ClientType clientType = ClientType.valueOf(scanner.nextLine().toUpperCase());

        // Check if email is unique
        if (isEmailUnique(email)) {
            String id = IdGenerator.generateId(); // Assuming IdGenerator generates unique IDs
            Client client = new Client(id, firstName, lastName, email, phoneNumber, password, UserType.CLIENT, clientType);

            // Save client to CSV
            saveClientToFile(client);
            System.out.println("Client added successfully!");
        } else {
            System.out.println("Error: Email already exists!");
        }
    }

    // Method to update client information
    public void updateClient(String clientId) {
        Scanner scanner = new Scanner(System.in);

        List<Client> clients = loadClientsFromFile();
        Client client = clients.stream().filter(c -> c.getId().equals(clientId)).findFirst().orElse(null);

        if (client == null) {
            System.out.println("Client not found.");
            return;
        }

        // Ask what they want to update
        System.out.println("What would you like to update?");
        System.out.println("1. Email\n2. Phone Number\n3. Client Type\n4. Password");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:  // Update Email
                System.out.print("Enter new email: ");
                String newEmail = scanner.nextLine();
                if (isEmailUnique(newEmail)) {
                    client.setEmail(newEmail);
                    System.out.println("Email updated successfully!");
                } else {
                    System.out.println("Error: Email already exists!");
                }
                break;
            case 2:  // Update Phone Number
                System.out.print("Enter new phone number: ");
                client.setPhoneNumber(scanner.nextLine());
                System.out.println("Phone number updated successfully!");
                break;
            case 3:  // Update Client Type
                System.out.print("Enter new client type (BUYER, TENANT, SELLER, LANDLORD): ");
                client.setType(ClientType.valueOf(scanner.nextLine().toUpperCase()));
                System.out.println("Client type updated successfully!");
                break;
            case 4:  // Update Password
                System.out.print("Enter new password: ");
                client.setPassword(scanner.nextLine());
                System.out.println("Password updated successfully!");
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        // Save the updated list of clients back to the file
        saveClientsToFile(clients);
    }

    // Method to view all clients
    public void viewAllClients() {
        List<Client> clients = loadClientsFromFile();
        if (clients.isEmpty()) {
            System.out.println("No clients found.");
            return;
        }

        for (Client client : clients) {
            System.out.println(client);
        }
    }

    // Method to delete a client
    public void deleteClient(String clientId) {
        List<Client> clients = loadClientsFromFile();
        Client clientToDelete = clients.stream().filter(c -> c.getId().equals(clientId)).findFirst().orElse(null);

        if (clientToDelete == null) {
            System.out.println("Client not found.");
            return;
        }

        // Remove the client from the list
        clients.remove(clientToDelete);

        // Save the updated list of clients back to the file
        saveClientsToFile(clients);
        System.out.println("Client deleted successfully.");
    }

    // Helper method to check if the email is unique
    private boolean isEmailUnique(String email) {
        List<Client> clients = loadClientsFromFile();
        return clients.stream().noneMatch(client -> client.getEmail().equals(email));
    }

    // Helper method to load all clients from the CSV file
    private List<Client> loadClientsFromFile() {
        List<Client> clients = new ArrayList<>();
        File file = new File(CLIENT_FILE);

        if (!file.exists()) {
            return clients;  // If file doesn't exist, return an empty list
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

    // Helper method to save a client to the CSV file
    private void saveClientToFile(Client client) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CLIENT_FILE, true))) {
            writer.write(client.getId() + "," +
                    client.getFirstName() + "," +
                    client.getLastName() + "," +
                    client.getEmail() + "," +
                    client.getPhoneNumber() + "," +
                    client.getPassword() + "," +
                    client.getUserType() + "," +
                    client.getType());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to save the updated list of clients to the CSV file
    private void saveClientsToFile(List<Client> clients) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CLIENT_FILE))) {
            for (Client client : clients) {
                writer.write(client.getId() + "," +
                        client.getFirstName() + "," +
                        client.getLastName() + "," +
                        client.getEmail() + "," +
                        client.getPhoneNumber() + "," +
                        client.getPassword() + "," +
                        client.getUserType() + "," +
                        client.getType());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

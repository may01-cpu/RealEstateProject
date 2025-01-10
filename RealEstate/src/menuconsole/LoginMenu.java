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
                            System.out.println("Login failed. Invalid email or password.");
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
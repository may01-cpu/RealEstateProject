package menuconsole;

import java.util.Date;
import java.util.Scanner;
import model.*;
import service.AppointmentService;
import service.ClientService;
import service.PropertyService;
import service.TransactionService;
import utils.ConsoleUtils;


public class WorkerMenu {

    private Worker worker;


    public WorkerMenu() {
         displayMenu();
    }

    // Method to display the worker menu and handle operations
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Worker Menu ---");
            System.out.println("1. Manage Properties");
            System.out.println("2. Manage Clients");
            System.out.println("3. Manage Transactions");
            System.out.println("4. Manage Appointments");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after the int input

            switch (choice) {
                case 1:
                    manageProperties(scanner);
                    break;
                case 2:
                    manageClients(scanner);
                    break;
                case 3:
                    manageTransactions(scanner);
                    break;
                case 4:
                      manageAppointments(scanner);
                    break;
                case 5:
                    ConsoleUtils.showLoading("Exiting",3);
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
                    break;
            }
        }
    }

    private void manageProperties(Scanner scanner){
        PropertyService propertyService = new PropertyService();

        System.out.println("\n--- Manage Properties ---");
        System.out.println("1. View Properties List");
        System.out.println("2. Add Property");
        System.out.println("3. Update Property");
        System.out.println("4. Delete Property");
        System.out.println("5. Search Properties");
        System.out.println("6. Get Property details");
        System.out.println("7. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                ConsoleUtils.clearConsole();
               propertyService.printProperties();
                break;
            case 2:
                ConsoleUtils.clearConsole();
                propertyService.addProperty();
                break;
            case 3:
                ConsoleUtils.clearConsole();
                System.out.print("Enter Property ID: ");
                String propertyId = scanner.nextLine();
                propertyService.updateProperty(propertyId);
                break;
            case 4:
                System.out.print("Enter Property ID: ");
                String propId = scanner.nextLine();
                propertyService.deleteProperty(propId);
                break;
            case 5:
                ConsoleUtils.clearConsole();
                propertyService.searchPropertiesByFiltration();
                break;
            case 6:
                ConsoleUtils.clearConsole();
                System.out.print("Enter Property ID: ");
                String proId = scanner.nextLine();
                propertyService.getPropertyDetails(proId);
                break;
            case 7:
                ConsoleUtils.showLoading("Returning to worker menu",2);
                return;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private void manageClients(Scanner scanner){
        ClientService clientService = new ClientService();

        System.out.println("\n--- Manage Clients ---");
        System.out.println("1. View All Clients");
        System.out.println("2. Add Client");
        System.out.println("3. Update Client");
        System.out.println("4. Delete Client");
        System.out.println("5. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                ConsoleUtils.clearConsole();
                clientService.viewAllClients();
                break;
            case 2:
                ConsoleUtils.clearConsole();
                clientService.addClient();
                break;
            case 3:
                System.out.print("Enter Property ID: ");
                String cId = scanner.nextLine();
                clientService.updateClient(cId);
                break;
            case 4:
                ConsoleUtils.clearConsole();
                System.out.print("Enter Client ID: ");
                String clientId = scanner.nextLine();
                clientService.deleteClient(clientId);
                break;
            case 5:
                ConsoleUtils.showLoading("Returning to worker menu",2);
                return;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private void manageTransactions(Scanner scanner) {
        TransactionService transactionService = new TransactionService();

        System.out.println("\n--- Manage Transactions ---");
        System.out.println("1. Create Transaction");
        System.out.println("2. View Transaction by ID");
        System.out.println("3. List All Transactions");
        System.out.println("4. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Enter transaction type ");
                String type = scanner.nextLine();

                System.out.println("Enter Property ID: ");
                String propertyId = scanner.nextLine();

                System.out.println("Enter Client ID: ");
                String clientId = scanner.nextLine();

                System.out.println("Enter Amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();

                System.out.println("Enter Transaction ID: ");
                String transactionId = scanner.nextLine();

                System.out.println("Enter Initiator ID: ");
                String initiatorId = scanner.nextLine();

                System.out.println("Enter Recipient ID: ");
                String recipientId = scanner.nextLine();

                transactionService.creerTransaction(TransactionType.valueOf(type), null, null, amount, transactionId, initiatorId, recipientId, new Date(), new Date());
                break;

            case 2:
                System.out.print("Enter Transaction ID: ");
                String transactionToView = scanner.nextLine();

                transactionService.viewTransaction(transactionToView);
                break;

            case 3:
                transactionService.afficherTransactions();
                break;

            case 4:
                System.out.println("Returning to worker menu...");
                return;

            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private void manageAppointments(Scanner scanner) {
    AppointmentService appointmentService = new AppointmentService();

    System.out.println("\n--- Manage Appointments ---");
    System.out.println("1. Create Appointment");
    System.out.println("2. Update Appointment State");
    System.out.println("3. Cancel Appointment");
    System.out.println("4. View Appointments for a Client");
    System.out.println("5. view all Appointments");
    System.out.println("6. Exit");

    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    
    switch (choice) {
        case 1:
            appointmentService.createAppointment(scanner); // Use the worker as the creator
            break;
        case 2:
            appointmentService.updateAppointmentState(scanner); // Update an appointment
            break;
        case 3:
            appointmentService.removeAppointment(scanner); // Cancel an appointment
            break;
        case 4:
            System.out.print("Enter Client ID: ");
            String clientId = scanner.nextLine();
            appointmentService.viewAppointmentsForClient(clientId); // Display appointments for a client (implement this method if needed)
            break;
        case 5:
            appointmentService.listAppointments();
            break;
        case 6:
            ConsoleUtils.showLoading("Returning to worker menu",2);
            return;
        default:
            System.out.println("Invalid choice.");
            break;
    }
}

}
